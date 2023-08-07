package project.app.handler;

import java.io.PrintWriter;
import java.util.List;
import project.app.dao.ReviewDao;
import project.app.vo.Review;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/review/search")
public class ReviewSearchServlet implements Servlet {

  ReviewDao reviewDao;
  String[] sub = {"C++", "Java", "Python", "Linux"};
  String[] rate = {"★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★"};

  public ReviewSearchServlet(ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int subject = Integer.parseInt(request.getParameter("subject"));
    List<Review> list = reviewDao.findBySubject(subject);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의평가</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>%s 강의평가 목록</h1>\n", sub[subject-1]);
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/review/detail.html'>강의평가 등록</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("<tr><th>과목</th> <th>이름</th> <th>평점</th> <th style='width:120px;'>내용</th></tr>");
    out.println("</thead>");

    for (Review rev : list) {
      out.printf("<tr>"
          + "<td><center>%s</center></td>"
          + "<td><center>***</center></td>"
          + "<td><center>%s</center></td>"
          + "<td><a href='/review/detail?no=%d'>%s</td>",
          sub[subject-1],
          rate[rev.getRate()-1],
          rev.getNo(),
          rev.getContent());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/review/list'>뒤로</a>");
    out.println("</body>");
    out.println("</html>");
  }
}











