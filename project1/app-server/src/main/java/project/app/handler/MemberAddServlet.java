package project.app.handler;

import project.app.vo.Department;
import project.app.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Member member = new Member();
    member.setMemberType(Integer.parseInt(request.getParameter("memberType")));
    member.setMemberName(request.getParameter("memberName"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setDept(new Department(Integer.parseInt(request.getParameter("deptNo"))));

    Part photoPart = request.getPart("photoFile");
    if (photoPart.getSize()>0) {
      String uploadFileUrl = InitServlet.ncpObjectStorageService.uploadFile(
              "bitcamp", "member/", photoPart);
    member.setPhoto(uploadFileUrl);
    }
    try {
      InitServlet.memberDao.insert(member);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}