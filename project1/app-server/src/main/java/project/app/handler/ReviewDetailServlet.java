package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.app.vo.Review;
import project.app.vo.Student;

@WebServlet("/review/detail")
public class ReviewDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  String[] SUB = {"C++", "Java", "Python", "Linux"};
  String[] RATE = {"★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★"};

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

    Review rev = InitServlet.reviewDao.findBy(Integer.parseInt(request.getParameter("no")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의평가</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>%s 강의평가 상세</h1>\n", SUB[rev.getSubjectId()-1]);
    out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());
    out.println("<form action='/review/update' method='post'>");
    out.println("<table border='1'>");
    out.printf("<input type='hidden' name='no' value='%d'>\n", rev.getNo());
    out.printf("<tr><th style='width:120px;'>번호</th>"
        + " <td style='width:300px;'><input type='text' value='%d' readonly></td></tr>\n", rev.getStudent().getNo());
    out.printf("<tr><th>작성자</th> <td>%s</td></tr>\n", rev.getStudent().getName());
    out.printf("<tr><th>과목</th>"
        + " <td><input type='text' value='%s' readonly></td></tr>\n", SUB[rev.getSubjectId()-1]);
    out.printf("<tr><th>평점</th>"
        + " <td><select name='rate'>\n");
    for (int i = 0; i < RATE.length; i++) {
      if (rev.getRate() == i+1) {
        out.printf(" <option value=%d selected>%s</option>\n", i+1, RATE[i]);
      } else {
        out.printf(" <option value=%d>%s</option>\n", i+1, RATE[i]);
      }
    }

    out.printf("<tr><th>내용</th>"
        + " <td><textarea name='content' style='height:200px; width:400px;'>%s</textarea></td></tr>\n", rev.getContent());
    out.println("</table>");

    out.println("<div>");
    out.println("<button>변경</button>");
    out.println("<button type='reset'>초기화</button>");
    out.printf("<a href='/review/delete?no=%d'>삭제</a>\n",rev.getNo());
    out.printf("<a href='/review/search?subject=%d'>뒤로</a>\n", rev.getSubjectId());
    out.println("</div>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}