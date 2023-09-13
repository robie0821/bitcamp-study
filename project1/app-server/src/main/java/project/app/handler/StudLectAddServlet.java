package project.app.handler;

import project.app.vo.Lecture;
import project.app.vo.Member;
import project.app.vo.StudLect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/studlecture/add")
public class StudLectAddServlet extends HttpServlet {
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
    } else if (loginUser.getMemberType() != 1) {
      out.println("<p>잘못된 접근입니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/lecture/list'>");
    }

    StudLect studLect = new StudLect();
    studLect.setStudent(loginUser);
    studLect.setLecture(new Lecture(Integer.parseInt(request.getParameter("lectNo"))));

    try {
      InitServlet.studLectDao.insert(studLect);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("/lecture/list");
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("message", "강의 등록 오류!");
      request.setAttribute("refresh", "2;url=/lecture/list");

      request.getRequestDispatcher("/error").forward(request, response);
    }


  }
}