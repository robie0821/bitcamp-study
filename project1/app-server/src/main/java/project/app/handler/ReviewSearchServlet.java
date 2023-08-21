package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/search")
public class ReviewSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String[] sub = {"C++", "Java", "Python", "Linux"};
  String[] rate = {"★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★"};

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

    int subject = Integer.parseInt(request.getParameter("subject"));
    List<Review> list = InitServlet.subjectDao.findBySubject(subject);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의평가</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>%s 강의평가 목록</h1>\n", sub[subject-1]);
    out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/review/form.html'>강의평가 등록</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("<tr><th style='width:50px;'>이름</th> <th style='width:100px;'>평점</th> <th style='width:100px;'>내용</th></tr>");
    out.println("</thead>");

    for (Review rev : list) {
      out.printf("<tr>"
          + "<td><center>***</center></td>"
          + "<td><center>%s</center></td>"
          + "<td style='padding:5px 5px 5px 5px;'><a href='/review/detail?no=%d'><center>%s</center></td>",
          rate[rev.getRate()-1],
          rev.getNo(),
          "상세보기");
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/review/list'>뒤로</a>");
    out.println("</body>");
    out.println("</html>");
  }
}