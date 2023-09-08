package project.app.handler;

import project.app.vo.Department;
import project.app.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>멤버 정보</h1>");

    Member member = InitServlet.memberDao.findBy(Integer.parseInt(request.getParameter("memberNo")));
    List<Department> departments = InitServlet.departmentDao.findAll();

    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");
    } else {
      out.println("<form action='/member/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>" +
              "<td style='width:200px;'><input type='text' name='memberNo' value='%d' readonly></td></tr>\n"
              , member.getMemberNo());
      out.printf("<tr><th>이름</th>" +
              "<td><input type='text' name='memberName' value='%s'></td></tr>\n"
              , member.getMemberName());
      out.printf("<tr><th>이메일</th>" +
                      "<td><input type='text' name='email' value='%s'></td></tr>\n"
              , member.getEmail());
      out.printf("<tr><th>비밀번호</th>" +
                      "<td><input type='password' name='password'></td></tr>\n");
      out.printf("<tr><th>학과</th>\n" +
              " <td><select name='deptNo'>\n");
      for (Department department : departments){
        out.printf(" <option value='%d' %s>%s</option>\n",
                department.getDeptNo(),
                (department.getDeptName().equals(member.getDept().getDeptName()) ? "selected" : ""),
                department.getDeptName());
      }

    }
  }
}