package project.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import project.net.NetProtocol;

public class ClientApp {

  String ip;
  int port;

  public ClientApp(String ip, int port) throws Exception {
    this.ip = ip;
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
    ClientApp app = new ClientApp("localhost", 8888);
    app.execute();
  }

  public void execute() {
    try (Scanner keyscan = new Scanner(System.in);
        Socket socket = new Socket(this.ip, this.port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      System.out.println(in.readUTF());

      while (true) {
        String response = in.readUTF();
        if (response.equals(NetProtocol.RESPONSE_END)) {
          continue;
        } else if (response.equals(NetProtocol.PROMPT)) {
          out.writeUTF(keyscan.nextLine());
          continue;
        } else if (response.equals(NetProtocol.NET_END)) {
          break;
        }
        System.out.print(response);
      }

    } catch (Exception e) {
      System.out.println("서버 통신 오류!");
      e.printStackTrace();
    }
  }
}