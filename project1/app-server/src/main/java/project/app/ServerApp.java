package project.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.app.dao.ReviewDao;
import project.app.dao.ScoreDao;
import project.app.dao.StudentDao;
import project.app.handler.StudentAddListener;
import project.app.handler.StudentDeleteListener;
import project.app.handler.StudentListListener;
import project.app.handler.StudentSearchListener;
import project.app.handler.StudentUpdateListener;
import project.net.NetProtocol;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;
import project.util.SqlSessionFactoryProxy;

public class ServerApp {

  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  SqlSessionFactory sqlSessionFactory;

  StudentDao studentDao;
  ScoreDao scoreDao;
  ReviewDao reviewDao;


  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception{
    this.port = port;

    InputStream mybatisConfigIn = Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    //    this.studentDao = new MySQLStudentDao(sqlSessionFactory);
    //    this.scoreDao = new MySQLScoreDao(sqlSessionFactory);
    //    this.reviewDao = new MySQLReviewDao(sqlSessionFactory);

    prepareMenu();
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("**대학교 정보 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());


      out.writeUTF("[**대학교 정보 관리 시스템]\n"
          + "----------------------------------------");

      //new LoginListener(studentDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      ((SqlSessionFactoryProxy) sqlSessionFactory).clean();
    }
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
