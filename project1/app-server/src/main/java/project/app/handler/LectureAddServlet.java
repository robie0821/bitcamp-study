package project.app.handler;

import project.app.vo.Lecture;
import project.app.vo.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LectureAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    Lecture lecture = new Lecture();
    lecture.setLectName(request.getParameter("lectName"));
    lecture.setRoom(Integer.parseInt(request.getParameter("room")));
    lecture.setSubject(new Subject(Integer.parseInt(request.getParameter("subjNo"))));

    try {
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