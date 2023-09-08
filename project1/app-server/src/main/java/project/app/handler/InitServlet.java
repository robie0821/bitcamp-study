package project.app.handler;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.app.dao.*;
import project.util.NcpConfig;
import project.util.NcpObjectStorageService;
import project.util.SqlSessionFactoryProxy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    value="/init",
    loadOnStartup = 1
    )
public class InitServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static SqlSessionFactory sqlSessionFactory;
  public static MemberDao memberDao;
  public static SubjectDao subjectDao;
  public static LectureDao lectureDao;
  public static DepartmentDao departmentDao;
  public static NcpObjectStorageService ncpObjectStorageService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    try {
      sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("project/app/config/mybatis-config.xml")));

      memberDao = new MySQLMemberDao(sqlSessionFactory);
      subjectDao = new MySQLSubjectDao(sqlSessionFactory);
      lectureDao = new MySQLLectureDao(sqlSessionFactory);
      departmentDao = new MySQLDepartmentDao(sqlSessionFactory);


      ncpObjectStorageService = new NcpObjectStorageService(new NcpConfig());


    } catch (Exception e) {
      System.out.println("InitServlet.init() 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>준비</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>애플리케이션 준비</h1>");
    out.println("<p>애플리케이션을 실행할 준비를 완료했습니다!</p>");
    out.println("</body>");
    out.println("</html>");
  }
}