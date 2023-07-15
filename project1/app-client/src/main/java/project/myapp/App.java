package project.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import project.myapp.handler.GradeAddListener;
import project.myapp.handler.GradeListListener;
import project.myapp.handler.GradeUpdateListener;
import project.myapp.handler.ReviewAddListener;
import project.myapp.handler.ReviewListListener;
import project.myapp.handler.ReviewSearchListener;
import project.myapp.handler.ReviewUpdateListener;
import project.myapp.handler.StudentAddListener;
import project.myapp.handler.StudentDeleteListener;
import project.myapp.handler.StudentListListener;
import project.myapp.handler.StudentSearchListener;
import project.myapp.handler.StudentUpdateListener;
import project.myapp.vo.CsvObject;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;

public class App {

  ArrayList<Student> studentList = new ArrayList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle() {
    System.out.println("**대학교 정보 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();

    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }

  private void loadData() {
    loadCsv("student.csv", studentList, Student.class);
  }

  private void saveData() {
    saveCsv("student.csv", studentList);
  }

  private void prepareMenu() {
    MenuGroup loginMenu = new MenuGroup("학생 등록");
    loginMenu.add(new Menu("학생 등록", new StudentAddListener(studentList)));
    loginMenu.add(new Menu("학생 목록", new StudentListListener(studentList)));
    loginMenu.add(new Menu("비밀번호 조회", new StudentSearchListener(studentList)));
    loginMenu.add(new Menu("정보 변경", new StudentUpdateListener(studentList)));
    loginMenu.add(new Menu("학생 삭제", new StudentDeleteListener(studentList)));
    mainMenu.add(loginMenu);

    MenuGroup gradeMenu = new MenuGroup("학점등록");
    gradeMenu.add(new Menu("등록", new GradeAddListener(studentList)));
    gradeMenu.add(new Menu("목록", new GradeListListener(studentList)));
    gradeMenu.add(new Menu("변경", new GradeUpdateListener(studentList)));
    mainMenu.add(gradeMenu);

    MenuGroup reviewMenu = new MenuGroup("과목평가");
    reviewMenu.add(new Menu("등록", new ReviewAddListener(studentList)));
    reviewMenu.add(new Menu("목록", new ReviewListListener(studentList)));
    reviewMenu.add(new Menu("검색", new ReviewSearchListener(studentList)));
    reviewMenu.add(new Menu("변경", new ReviewUpdateListener(studentList)));
    mainMenu.add(reviewMenu);
  }

  @SuppressWarnings("unchecked")
  private <T extends CsvObject> void loadCsv(String filename, List<T> list, Class<T> clazz) {
    try {
      Method factoryMethod = clazz.getDeclaredMethod("fromCsv", String.class);

      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0);
      String line = null;

      while ((line = in.readLine()) != null) {
        list.add((T)factoryMethod.invoke(null, line));
      }

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveCsv(String filename, List<? extends CsvObject> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0);
      PrintWriter out = new PrintWriter(out1);

      for (CsvObject obj : list) {
        out.println(obj.toCsvString());

      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}
