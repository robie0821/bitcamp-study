package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardListDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MemberListDao;
import bitcamp.myapp.vo.Board;
import bitcamp.net.RequestEntity;
import bitcamp.net.ResponseEntity;

public class ServerApp {

  int port;
  ServerSocket serverSocket;

  MemberDao memberDao = new MemberListDao("member.json");
  BoardDao boardDao = new BoardListDao("board.json");
  BoardDao readingDao = new BoardListDao("reading.json");

  public ServerApp(int port) throws Exception {
    this.port = port;
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }

    ServerApp app = new ServerApp(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    System.out.println("[MyList 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    Gson gson = new Gson();

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);

      ResponseEntity response = new ResponseEntity();

      if (command.equals("quit")) {
        break;
      }

      switch (command) {
        case "board/insert":
          boardDao.insert(request.getObject(Board.class));
          response.status(ResponseEntity.SUCCESS);
          break;

        case "board/list":
          response.status(ResponseEntity.SUCCESS).result(boardDao.list());
          break;

        case "board/findby":
          Board findBoard = boardDao.findBy(request.getObject(Integer.class));
          if (findBoard != null) {
            response.status(ResponseEntity.SUCCESS).result(findBoard);
          } else {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시물이 없습니다.");
          }
          break;


        case "board/update":
          int updateResult = boardDao.update(request.getObject(Board.class));

          if (updateResult != 0) {
            response.status(ResponseEntity.SUCCESS).result(updateResult);
          } else {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시물이 없습니다.");
          }
          break;

        case "board/delete":
          int deleteResult = boardDao.delete(request.getObject(Integer.class));

          if (deleteResult != 0) {
            response.status(ResponseEntity.SUCCESS).result(deleteResult);
          } else {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시물이 없습니다.");
          }
          break;

        default:
          response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다.");
      }

      out.writeUTF(gson.toJson(response));
    }

    in.close();
    out.close();
    socket.close();
  }
}





