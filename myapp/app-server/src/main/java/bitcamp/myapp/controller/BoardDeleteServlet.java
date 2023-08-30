package bitcamp.myapp.controller;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/board/delete")
public class BoardDeleteServlet implements PageController {

  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardDeleteServlet(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";
    }

    try {
      Board board = new Board();
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setWriter(loginUser);
      board.setCategory(Integer.parseInt(request.getParameter("category")));

      boardDao.deleteFiles(board.getNo());

      if (boardDao.delete(board) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list?category=" + request.getParameter("category");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list?category=" + request.getParameter("category"));
      throw e;
    }
  }
}