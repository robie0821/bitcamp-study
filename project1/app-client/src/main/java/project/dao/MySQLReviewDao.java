package project.dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.app.dao.ReviewDao;
import project.app.vo.Review;

public class MySQLReviewDao implements ReviewDao {

  Connection con;

  public MySQLReviewDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Review Review) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into myapp_Review(title,content,writer,password,category)"
            + " values(?,?,?,sha1(?),?)")) {

      stmt.setString(1, Review.getTitle());
      stmt.setString(2, Review.getContent());
      stmt.setInt(3, Review.getWriter().getNo());
      stmt.setString(4, Review.getPassword());
      stmt.setInt(5, this.category);

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Review> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
            "  b.Review_no, " +
            "  b.title, " +
            "  b.view_count, " +
            "  b.created_date, " +
            "  m.member_no, " +
            "  m.name " +
            " from " +
            "  myapp_Review b inner join myapp_member m on b.writer=m.member_no" +
            " where " +
            "  category=?" +
            " order by " +
            "  Review_no desc"
        )) {

      stmt.setInt(1, this.category);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Review> list = new ArrayList<>();
        while (rs.next()) {
          Review b = new Review();
          b.setNo(rs.getInt("Review_no"));
          b.setTitle(rs.getString("title"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);

          list.add(b);
        }
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public Review findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
            "  b.Review_no, " +
            "  b.title, " +
            "  b.content," +
            "  b.view_count, " +
            "  b.created_date, " +
            "  m.member_no, " +
            "  m.name " +
            " from " +
            "  myapp_Review b inner join myapp_member m on b.writer=m.member_no" +
            " where " +
            "  category=?" +
            "  and Review_no=?"
        )) {

      stmt.setInt(2, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Review b = new Review();
          b.setNo(rs.getInt("Review_no"));
          b.setTitle(rs.getString("title"));
          b.setContent(rs.getString("content"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);

          stmt.executeUpdate("update myapp_Review set"
              + " view_count=view_count + 1"
              + " where Review_no=" + no);

          return b;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Review Review) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update myapp_Review set"
            + " title=?,"
            + " content=?"
            + " where category=? and Review_no=? and writer=?")) {

      stmt.setString(1, Review.getTitle());
      stmt.setString(2, Review.getContent());
      stmt.setInt(3, this.category);
      stmt.setInt(4, Review.getNo());
      stmt.setInt(5, Review.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Review Review) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from myapp_Review"
            + " where category=? and Review_no=? and writer=?")) {

      stmt.setInt(2, Review.getNo());
      stmt.setInt(3, Review.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
