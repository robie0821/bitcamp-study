package project.app.handler;

import project.app.vo.Lecture;
import project.app.vo.Member;
import project.app.vo.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lecture/update")
public class LectureUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

//    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
//    if (loginUser == null) {
//      out.println("<p>로그인이 필요합니다.</p>");
//      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
//      return;
//    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/lecture/search?subjNo=%d'>",Integer.parseInt(request.getParameter("subjNo")));
    out.println("<title>수강신청</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의 변경</h1>");

    try {
      Lecture lecture = new Lecture();
      lecture.setLectNo(Integer.parseInt(request.getParameter("lectNo")));
      lecture.setSubject(new Subject(Integer.parseInt(request.getParameter("subjNo"))));
      lecture.setLectName(request.getParameter("lectName"));
      lecture.setRoom(Integer.parseInt(request.getParameter("room")));

      if (InitServlet.lectureDao.update(lecture) == 0) {
        out.println("<p>게시글이 없거나 변경 권한이 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}