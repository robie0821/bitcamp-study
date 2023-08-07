package project.app.handler;

import java.io.PrintWriter;
import java.util.List;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/score/list")
public class ScoreListServlet implements Servlet {

  ScoreDao scoreDao;

  public ScoreListServlet(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학점</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학점 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/score/form.html'>학점 등록</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th>번호</th> <th>이름</th> <th>학점</th> <th>장학금</th></tr>");
    out.println("</thead>");

    List<Score> list = scoreDao.findAll();

    out.println("<tbody>");
    for (Score s : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/score/detail?no=%d'>%s</a></td>"
          + " <td>%.2f</td>"
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