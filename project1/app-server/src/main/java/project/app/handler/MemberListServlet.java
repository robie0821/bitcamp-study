package project.app.handler;

import project.app.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member/list")
public class MemberListServlet extends HeaderServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    int memberType = Integer.parseInt(request.getParameter("memberType"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>멤버</title>");
    out.println("</head>");
    out.println("<body>");
    switch (memberType) {
      case 1:
        out.println("<h1>학생 목록</h1>");
        break;
      case 2:
        out.println("<h1>교수 목록</h1>");
        break;
    }
    out.println("<div style='margin:5px;'/>");
    out.println("<table border='1'>");
    out.println("<thead>");

    out.println("<tr><th/>번호" +
            "<th style='width:80px;'/>이름" +
            "<th style='width:80px;'/>이메일" +
            "<th style='width:50px;'/>학과명</tr>");
    out.println("</thead>");

    List<Member> list = InitServlet.memberDao.findAll(memberType);

    out.println("<tbody>");
    for (Member member : list) {
      out.printf("<tr>" +
                      "<td/><center/>%d" +
                      "<td/><a href='/member/detail?memberNo=%d'/>%s" +
                      "<td/><center/>%s" +
                      "<td/><center/>%s\n",
              member.getMemberNo(),
              member.getMemberNo(),
              member.getMemberName(),
              member.getEmail(),
              member.getDept().getDeptName());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'/>메인");
    out.println("</body>");
    out.println("</html>");
  }
}