package project1;

import project1.system.Login;
import project1.util.ArrayList;
import project1.util.LinkedList;
import project1.util.Prompt;

public class App {

  public static void main(String[] args) {

    Prompt prompt = new Prompt();
    ArrayList arr = new ArrayList();
    LinkedList link = new LinkedList();

    Login login = new Login(prompt, new ArrayList());

    loop: while (true) {
      if(!login.execute()) {
        break;
      } else {
        System.out.println("로그인 성공");

        printMenu();

        switch(prompt.inputString("> ")) {
          case "1":
            // 예금
            break;
          case "2":
            // 대출
            break;
          case "3":
            // 증권
            break;
          case "0":
            continue loop;
          default:
            System.out.println("잘못된 번호입니다.");
            break;
        }
      }
    }
    prompt.close();
  }

  private static void printMenu() {
    System.out.println("**은행 시스템");
    System.out.println("--------------------");
    System.out.println("1. 은행업무");
    System.out.println("2. 대출업무");
    System.out.println("3. 증권업무");
    System.out.println("0. 로그아웃");
    System.out.print("> ");
  }
}