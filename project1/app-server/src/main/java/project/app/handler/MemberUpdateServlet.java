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

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

//    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
//    if (loginUser == null) {
//      out.println("<p>로그인이 필요합니다.</p>");
//      out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
//      return;
//    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/member/list?'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 정보 변경</h1>");

    try {
      Member member = new Member();
      member.setMemberType(Integer.parseInt(request.getParameter("memberType")));
      member.setMemberName(request.getParameter("memberName"));
      member.setDept(new Department(Integer.parseInt(request.getParameter("deptNo"))));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));

      if (InitServlet.memberDao.update(member) == 0) {
        out.println("<p>회원 정보 변경 오류</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}