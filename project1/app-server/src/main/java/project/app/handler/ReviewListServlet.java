package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/list")
public class ReviewListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String[] sub = {"C++", "Java", "Python", "Linux"};

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의평가</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>과목 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/review/form.html'>강의평가 등록</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th style='width:80px;'>과목이름</th><th style='width:50px;'>평점</th><th style='width:50px;'>갯수</th></tr>");
    out.println("</thead>");

    List<Map<String, Object>> list = InitServlet.reviewDao.list();

    out.println("<tbody>");
    for (Map<String, Object> rev : list) {
      out.printf("<tr>"
          + "<td style='padding:0 0 0 5px'><a href='/review/search?subject=%d'>%s</a></td>"
          + "<td><center>%.2f</center></td>"
          + "<td><center>%s</center></td></tr>\n",
          Integer.parseInt(rev.get("sub").toString()),
          sub[Integer.parseInt(rev.get("sub").toString()) - 1],
          Float.parseFloat(rev.get("avg").toString()),
          rev.get("count"));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}