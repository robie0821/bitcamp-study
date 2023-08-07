package project.app.handler;

import java.io.PrintWriter;
import project.app.dao.ScoreDao;
import project.app.vo.Score;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;

@Component("/score/detail")
public class ScoreDetailServlet implements Servlet {

  ScoreDao scoreDao;

  public ScoreDetailServlet(ScoreDao scoreDao) {
    this.scoreDao = scoreDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Score score = scoreDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학점</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학점 상세</h1>");

    if (score == null) {
      out.println("<p>해당 학생이 없습니다!</p>");
    } else {
      out.println("<form action='/score/update'>");
      out.println("<table border='1'>");
      out.printf("<input type='hidden' name='no' value='%d'>\n", score.getNo());
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='studentNo' value='%d' readonly></td></tr>\n", score.getStudent().getNo());
      out.printf("<tr><th>C++</th>"
          + " <td><select name='sub1'>\n"
          + " <option value='A+' %s>A+</option>\n"
          + " <option value='A' %s>A</option>\n"
          + " <option value='B+' %s>B+</option>\n"
          + " <option value='B' %s>B</option>\n"
          + " <option value='C+' %s>C+</option>\n"
          + " <option value='C' %s>C</option>\n"
          + " <option value='D+' %s>D+</option>\n"
          + " <option value='D' %s>D</option>\n"
          + " <option value='F' %s>F</option></select></td></tr>\n",
          (score.getSub1().equals("A+") ? "selected" : ""),
          (score.getSub1().equals("A") ? "selected" : ""),
          (score.getSub1().equals("B+") ? "selected" : ""),
          (score.getSub1().equals("B") ? "selected" : ""),
          (score.getSub1().equals("C+") ? "selected" : ""),
          (score.getSub1().equals("C") ? "selected" : ""),
          (score.getSub1().equals("D+") ? "selected" : ""),
          (score.getSub1().equals("D") ? "selected" : ""),
          (score.getSub1().equals("F") ? "selected" : ""));

      out.printf("<tr><th>Java</th>"
          + " <td><select name='sub2'>\n"
          + " <option value='A+' %s>A+</option>\n"
          + " <option value='A' %s>A</option>\n"
          + " <option value='B+' %s>B+</option>\n"
          + " <option value='B' %s>B</option>\n"
          + " <option value='C+' %s>C+</option>\n"
          + " <option value='C' %s>C</option>\n"
          + " <option value='D+' %s>D+</option>\n"
          + " <option value='D' %s>D</option>\n"
          + " <option value='F' %s>F</option></select></td></tr>\n",
          (score.getSub2().equals("A+") ? "selected" : ""),
          (score.getSub2().equals("A") ? "selected" : ""),
          (score.getSub2().equals("B+") ? "selected" : ""),
          (score.getSub2().equals("B") ? "selected" : ""),
          (score.getSub2().equals("C+") ? "selected" : ""),
          (score.getSub2().equals("C") ? "selected" : ""),
          (score.getSub2().equals("D+") ? "selected" : ""),
          (score.getSub2().equals("D") ? "selected" : ""),
          (score.getSub2().equals("F") ? "selected" : ""));

      out.printf("<tr><th>Python</th>"
          + " <td><select name='sub3'>\n"
          + " <option value='A+' %s>A+</option>\n"
          + " <option value='A' %s>A</option>\n"
          + " <option value='B+' %s>B+</option>\n"
          + " <option value='B' %s>B</option>\n"
          + " <option value='C+' %s>C+</option>\n"
          + " <option value='C' %s>C</option>\n"
          + " <option value='D+' %s>D+</option>\n"
          + " <option value='D' %s>D</option>\n"
          + " <option value='F' %s>F</option></select></td></tr>\n",
          (score.getSub3().equals("A+") ? "selected" : ""),
          (score.getSub3().equals("A") ? "selected" : ""),
          (score.getSub3().equals("B+") ? "selected" : ""),
          (score.getSub3().equals("B") ? "selected" : ""),
          (score.getSub3().equals("C+") ? "selected" : ""),
          (score.getSub3().equals("C") ? "selected" : ""),
          (score.getSub3().equals("D+") ? "selected" : ""),
          (score.getSub3().equals("D") ? "selected" : ""),
          (score.getSub3().equals("F") ? "selected" : ""));

      out.printf("<tr><th>Linux</th>"
          + " <td><select name='sub4'>\n"
          + " <option value='A+' %s>A+</option>\n"
          + " <option value='A' %s>A</option>\n"
          + " <option value='B+' %s>B+</option>\n"
          + " <option value='B' %s>B</option>\n"
          + " <option value='C+' %s>C+</option>\n"
          + " <option value='C' %s>C</option>\n"
          + " <option value='D+' %s>D+</option>\n"
          + " <option value='D' %s>D</option>\n"
          + " <option value='F' %s>F</option></select></td></tr>\n",
          (score.getSub4().equals("A+") ? "selected" : ""),
          (score.getSub4().equals("A") ? "selected" : ""),
          (score.getSub4().equals("B+") ? "selected" : ""),
          (score.getSub4().equals("B") ? "selected" : ""),
          (score.getSub4().equals("C+") ? "selected" : ""),
          (score.getSub4().equals("C") ? "selected" : ""),
          (score.getSub4().equals("D+") ? "selected" : ""),
          (score.getSub4().equals("D") ? "selected" : ""),
          (score.getSub4().equals("F") ? "selected" : ""));

      out.printf("<tr><th>학점 평균</th> <td>%s</td></tr>\n", score.getGrade());
      out.printf("<tr><th>장학금</th> <td>%s</td></tr>\n", score.isScholar() ? "O" : "X");
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.println("<a href='/score/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }
  }
}