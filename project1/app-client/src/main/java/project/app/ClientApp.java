package project.app;

import java.sql.Connection;
import java.sql.DriverManager;
import project.app.dao.ReviewDao;
import project.app.dao.ScoreDao;
import project.app.dao.StudentDao;
import project.app.handler.StudentAddListener;
import project.app.handler.StudentDeleteListener;
import project.app.handler.StudentListListener;
import project.app.handler.StudentSearchListener;
import project.app.handler.StudentUpdateListener;
import project.app.vo.Student;
import project.dao.MySQLReviewDao;
import project.dao.MySQLStudentDao;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;

public class ClientApp {

  public static Student loginUser;

  StudentDao studentDao;
  ScoreDao scoreDao;
  ReviewDao reviewDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp() throws Exception{
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://study:1111@localhost:3306/project1" // JDBC URL
        );

    this.studentDao = new MySQLStudentDao(con);
    this.scoreDao = null;
    this.reviewDao = new MySQLReviewDao(con);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    ClientApp app = new ClientApp();
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("**대학교 정보 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();

    mainMenu.execute(prompt);
  }


  private void prepareMenu() {
    MenuGroup loginMenu = new MenuGroup("학생 등록");
    loginMenu.add(new Menu("학생 등록", new StudentAddListener(studentDao)));
    loginMenu.add(new Menu("학생 목록", new StudentListListener(studentDao)));
    loginMenu.add(new Menu("비밀번호 조회", new StudentSearchListener(studentDao)));
    loginMenu.add(new Menu("정보 변경", new StudentUpdateListener(studentDao)));
    loginMenu.add(new Menu("학생 삭제", new StudentDeleteListener(studentDao)));
    mainMenu.add(loginMenu);

    //    MenuGroup gradeMenu = new MenuGroup("학점등록");
    //    gradeMenu.add(new Menu("등록", new GradeAddListener(studentList)));
    //    gradeMenu.add(new Menu("목록", new GradeListListener(studentList)));
    //    gradeMenu.add(new Menu("변경", new GradeUpdateListener(studentList)));
    //    mainMenu.add(gradeMenu);
    //
    //    MenuGroup reviewMenu = new MenuGroup("과목평가");
    //    reviewMenu.add(new Menu("등록", new ReviewAddListener(studentList)));
    //    reviewMenu.add(new Menu("목록", new ReviewListListener(studentList)));
    //    reviewMenu.add(new Menu("검색", new ReviewSearchListener(studentList)));
    //    reviewMenu.add(new Menu("변경", new ReviewUpdateListener(studentList)));
    //    mainMenu.add(reviewMenu);
  }
}
