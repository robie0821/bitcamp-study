package bitcamp.myapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import bitcamp.util.SqlSessionFactoryProxy;
import org.apache.ibatis.session.SqlSessionFactory;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    SqlSessionFactoryProxy sqlSessionFactoryProxy = (SqlSessionFactoryProxy) sre.getServletContext().getAttribute("sqlSessionFactory");
    sqlSessionFactoryProxy.clean();
  }
}