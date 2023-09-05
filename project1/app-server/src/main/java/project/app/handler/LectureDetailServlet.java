package project.app.handler;

import project.app.vo.Lecture;
import project.app.vo.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/lecture/detail")
public class LectureDetailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>수업 정보</h1>");

    Lecture lecture = InitServlet.lectureDao.findByNo(Integer.parseInt(request.getParameter("lectNo")));
    List<Subject> subjects = InitServlet.subjectDao.findAll();


    if (lecture == null) {
      out.println("<p>해당 번호의 수업이 없습니다!</p>");
    } else {
      out.println("<form action='/lecture/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
              + " <td style='width:200px;'><input type='text' name='lectNo' value='%d' readonly></td></tr>\n", lecture.getLectNo());
      out.printf("<tr><th>이름</th>"
              + " <td><input type='text' name='lectName' value='%s'></td></tr>\n", lecture.getLectName());
      out.printf("<tr><th>과목</th>\n" +
              " <td><select name='subjNo'>\n");
      for (Subject subject : subjects) {
        out.printf(" <option value='%d' %s>%s</option>\n",
                subject.getSubjNo(),
                (subject.getSubjName().equals(lecture.getSubject().getSubjName()) ? "selected" : ""),
                subject.getSubjName());
      }
      out.printf("<tr><th>교수</th>"
              + " <td><input type='text' value='교수'></td></tr>\n");
      out.printf("<tr><th>강의실</th>"
              + " <td><input type='text' name='room' value='%s'></td></tr>\n", lecture.getRoom());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/lecture/delete?lectNo=%d'>삭제</a>\n", lecture.getLectNo());
      out.printf("<a href='/lecture/search?subjNo=%d'>뒤로</a>\n", lecture.getSubject().getSubjNo());
      out.println("</div>");
      out.println("</form>");
    }
  }
}