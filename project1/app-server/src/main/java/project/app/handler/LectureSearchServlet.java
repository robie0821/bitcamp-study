package project.app.handler;

import project.app.vo.Lecture;
import project.app.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/lecture/search")
public class LectureSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    out.println("<title>수강신청</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>수업 목록</h1>");
    out.println("<div style='margin:5px;'/>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th/>번호"+
            "<th style='width:100px;'/>이름" +
            "<th style='width:50px;'/>과목" +
            "<th style='width:50px;'/>타입" +
            "<th style='width:50px;'/>강의실</tr>");
    out.println("</thead>");

    List<Lecture> list = InitServlet.lectureDao.findBySubject(Integer.parseInt(request.getParameter("subjNo")));

    out.println("<tbody>");

    for(Lecture lecture : list) {
      out.printf("<tr>" +
              "<td/><center/>%d" +
              "<td/><a href='/lecture/detail?lectNo=%d'/>%s" +
              "<td/><center/>%s" +
              "<td/><center/>%s" +
              "<td/><center/>%d\n",
              lecture.getLectNo(),
              lecture.getLectNo(),
              lecture.getLectName(),
              lecture.getSubject().getSubjName(),
              lecture.getSubject().getSubjType(),
              lecture.getRoom());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/lecture/list'/>뒤로");
    out.println("</body>");
    out.println("</html>");
  }
}