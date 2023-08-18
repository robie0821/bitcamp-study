package project.app.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/score/detail")
public class ScoreDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static String[] SCORE = {"A+", "A", "B+", "B", "C+", "C", "D+", "D", "F"};

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

    Score score = InitServlet.scoreDao.findBy(Integer.parseInt(request.getParameter("no")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학점</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학점 상세</h1>");
    out.printf("<h4>현재 사용자 : %s</h4>\n", loginUser.getEmail());

    if (score == null) {
      out.println("<p>해당 학생이 없습니다!</p>");
    } else {
      out.println("<form action='/score/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<input type='hidden' name='no' value='%d'>\n", score.getNo());
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='student' value='%d' readonly></td></tr>\n", score.getStudent().getNo());

      out.printf("<tr><th>C++</th>"
          + " <td><select name='sub1'>\n");
      for (int i = 0; i < SCORE.length; i++) {
        if (score.getSub1().equals(SCORE[i])) {
          out.printf(" <option value=%s selected>%s</option>\n", SCORE[i], SCORE[i]);
        } else {
          out.printf(" <option value=%s>%s</option>\n", SCORE[i], SCORE[i]);
        }
      }

      out.printf("<tr><th>Java</th>"
          + " <td><select name='sub2'>\n");
      for (int i = 0; i < SCORE.length; i++) {
        if (score.getSub2().equals(SCORE[i])) {
          out.printf(" <option value=%s selected>%s</option>\n", SCORE[i], SCORE[i]);
        } else {
          out.printf(" <option value=%s>%s</option>\n", SCORE[i], SCORE[i]);
        }
      }

      out.printf("<tr><th>Python</th>"
          + " <td><select name='sub3'>\n");
      for (int i = 0; i < SCORE.length; i++) {
        if (score.getSub3().equals(SCORE[i])) {
          out.printf(" <option value=%s selected>%s</option>\n", SCORE[i], SCORE[i]);
        } else {
          out.printf(" <option value=%s>%s</option>\n", SCORE[i], SCORE[i]);
        }
      }

      out.printf("<tr><th>Linux</th>"
          + " <td><select name='sub4'>\n");
      for (int i = 0; i < SCORE.length; i++) {
        if (score.getSub4().equals(SCORE[i])) {
          out.printf(" <option value=%s selected>%s</option>\n", SCORE[i], SCORE[i]);
        } else {
          out.printf(" <option value=%s>%s</option>\n", SCORE[i], SCORE[i]);
        }
      }

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