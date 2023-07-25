package project.dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.app.dao.ScoreDao;
import project.app.vo.Review;
import project.app.vo.Score;
import project.app.vo.Student;

public class MySQLScoreDao implements ScoreDao {

  Connection con;

  public MySQLScoreDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Score score) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into myapp_Review(student_id,sub1,sub2,sub3,sub4,grade,scholar)"
            + " values(?,?,?,?,?,?,?)")) {

      stmt.setInt(1, score.getStudent().getNo());
      stmt.setString(1, score.getSub1());
      stmt.setString(2, score.getSub2());
      stmt.setString(3, score.getSub3());
      stmt.setString(4, score.getSub4());
      stmt.setDouble(5, score.getGrade());
      stmt.setBoolean(6, score.isScholar());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Score> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
            "  std.student_no, " +
            "  std.name, " +
            "  s.sub1, " +
            "  s.sub2, " +
            "  s.sub3, " +
            "  s.sub4, " +
            "  s.grade, " +
            "  s.scholar, " +
            " from " +
            "  score s inner join student std"
            + " on s.student_id=std.student_no");
        ResultSet rs = stmt.executeQuery()) {

      List<Score> list = new ArrayList<>();

      while (rs.next()) {
        Score s = new Score();
        s.setSub1(rs.getString("sub1"));
        s.setSub2(rs.getString("sub2"));
        s.setSub3(rs.getString("sub3"));
        s.setSub4(rs.getString("sub4"));
        s.setGrade(rs.getDouble("grade"));
        s.setScholar(rs.getBoolean("scholar"));

        Student std = new Student();
        std.setNo(rs.getInt("student_no"));
        std.setName(rs.getString("name"));
        s.setStudent(std);

        list.add(s);
      }
      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public Score findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select" +
            "  std.student_no, " +
            "  std.name, " +
            "  s.sub1, " +
            "  s.sub2, " +
            "  s.sub3, " +
            "  s.sub4, " +
            "  s.grade, " +
            "  s.scholar, " +
            " from " +
            "  score s inner join student std" +
            " on s.student_id=std.student_no" +
            " where " +
            " student_no=?"
        )) {
      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Score s = new Score();
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
  public int update(Score score) {
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
}
