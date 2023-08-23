package bitcamp.myapp.listener;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MySQLBoardDao;
import bitcamp.myapp.dao.MySQLMemberDao;
import bitcamp.util.NcpConfig;
import bitcamp.util.NcpObjectStorageService;
import bitcamp.util.SqlSessionFactoryProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext ctx = sce.getServletContext();

    try {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
              new SqlSessionFactoryBuilder().build(
                      Resources.getResourceAsStream(ctx.getInitParameter("mybatis-config"))));

      BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
      MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
      NcpObjectStorageService ncpObjectStorageService = new NcpObjectStorageService(new NcpConfig());

      ctx.setAttribute("sqlSessionFactory", sqlSessionFactory);
      ctx.setAttribute("boardDao", boardDao);
      ctx.setAttribute("memberDao", memberDao);
      ctx.setAttribute("ncpObjectStorageService", ncpObjectStorageService);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
