package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/score/list")
public class ScoreListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    Student loginUser = (Student) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인이 필요합니다.</p>");
      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
      return;
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학점</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학점 목록</h1>");
    out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/score/form.html'>학점 등록</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th style='width:50px;'>번호</th> <th style='width:80px;'>이름</th> <th style='width:50px;'>학점</th> <th style='width:50px;'>장학금</th></tr>");
    out.println("</thead>");

    List<Score> list = InitServlet.scoreDao.findAll();

    out.println("<tbody>");
    for (Score s : list) {
      out.printf("<tr>"
          + " <td><center>%d</center></td>"
          + " <td style='padding:0 0 0 5px'><a href='/score/detail?no=%d'>%s</a></td>"
          + " <td><center>%.2f</center></td>"
          + " <td><center>%s</center></td></tr>\n",
          s.getStudent().getNo(),
          s.getNo(),
          (s.getStudent().getName().length() > 0 ? s.getStudent().getName() : "이름없음"),
          s.getGrade(),
          (s.isScholar() ? "O" : "X"));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}