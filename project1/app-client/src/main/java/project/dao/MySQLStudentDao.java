package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.app.dao.StudentDao;
import project.app.vo.Student;

public class MySQLStudentDao implements StudentDao {

  Connection con;

  public MySQLStudentDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Student student) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into student(name,email,password)"
            + " values(?,?,sha1(?))")) {

      stmt.setString(1, student.getName());
      stmt.setString(2, student.getEmail());
      stmt.setString(3, student.getPassword());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Student> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select student_no, name, email"
            + " from student");
        ResultSet rs = stmt.executeQuery()) {

      List<Student> list = new ArrayList<>();

      while (rs.next()) {
        Student std = new Student();
        std.setNo(rs.getInt("student_no"));
        std.setName(rs.getString("name"));
        std.setEmail(rs.getString("email"));

        list.add(std);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Student findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select student_no, name, email"
            + " from student"
            + " where student_no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Student std = new Student();
          std.setNo(rs.getInt("student_no"));
          std.setName(rs.getString("name"));
          std.setEmail(rs.getString("email"));
          return std;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Student findByEmailAndPassword(Student param) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select student_no, name, email"
            + " from student"
            + " where email=? and password=sha1(?)")) {

      stmt.setString(1, param.getEmail());
      stmt.setString(2, param.getPassword());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Student std = new Student();
          std.setNo(rs.getInt("student_no"));
          std.setName(rs.getString("name"));
          std.setEmail(rs.getString("email"));
          return std;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Student student) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update student set"
            + " name=?,"
            + " email=?,"
            + " password=sha1(?),"
            + " where student_no=?")) {

      stmt.setString(1, student.getName());
      stmt.setString(2, student.getEmail());
      stmt.setString(3, student.getPassword());
      stmt.setInt(4, student.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from student where student_no=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
