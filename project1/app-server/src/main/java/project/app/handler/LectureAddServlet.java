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

@WebServlet("/lecture/add")
public class LectureAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인이 필요합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
      return;
    } else if (loginUser.getMemberType() != 2) {
      out.println("<p>권한이 없습니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=list'>");
      return;
    }


    Lecture lecture = new Lecture();
    try {
    lecture.setLectName(request.getParameter("lectName"));
    lecture.setRoom(Integer.parseInt(request.getParameter("room")));
    lecture.setSubject(new Subject(Integer.parseInt(request.getParameter("subjNo"))));

      InitServlet.lectureDao.insert(lecture);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", "강의 등록 오류!");
      request.setAttribute("refresh", "2;url=list");

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}