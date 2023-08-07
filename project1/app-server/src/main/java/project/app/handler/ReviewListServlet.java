package project.app.handler;

import java.io.PrintWriter;
import java.util.List;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/review/list")
public class ReviewListServlet implements Servlet {

  ReviewDao reviewDao;
  String[] sub = {"C++", "Java", "Python", "Linux"};

  public ReviewListServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    out.println("<tr><th>과목이름</th></tr>");
    out.println("</thead>");

    List<Review> list = reviewDao.findAll();

    out.println("<tbody>");
    for (Review rev : list) {
      out.printf("<tr>"
          + "<td><a href='/review/search?subject=%d'>%s</a></td></tr>\n",
          rev.getSubjectId(),
          sub[rev.getSubjectId() - 1]);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}