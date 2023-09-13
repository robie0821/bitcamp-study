package project.app.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studlect/delete")
public class StudLectDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      if (InitServlet.studLectDao.delete(
              Integer.parseInt(request.getParameter("memberNo")),
              Integer.parseInt(request.getParameter("lectNo"))) == 0) {
        throw new ServletException("삭제 오류");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}