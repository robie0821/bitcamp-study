package project.app.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/lecture/list")
public class LectureListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

//    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
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
    out.println("<h1>과목 목록</h1>");
    out.println("<div style='margin:5px;'/>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th/>번호" +
            "<th style='width:80px;'/>과목 이름" +
            "<th style='width:50px;'/>타입" +
            "<th style='width:50px;'/>갯수</tr>");
    out.println("</thead>");

    List<Map<String, Object>> list = InitServlet.lectureDao.findAll();

    out.println("<tbody>");
    for (Map<String, Object> lect : list) {
      out.printf("<tr>" +
                      "<td/><center/>%d" +
                      "<td/><a href='/lecture/search?subjNo=%d'/>%s" +
                      "<td/><center/>%s" +
                      "<td/><center/>%d\n",
              Integer.parseInt(lect.get("no").toString()),
              Integer.parseInt(lect.get("no").toString()),
              lect.get("name").toString(),
              lect.get("type").toString(),
              Integer.parseInt(lect.get("count").toString()));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'/>메인");
    out.println("</body>");
    out.println("</html>");
  }
}