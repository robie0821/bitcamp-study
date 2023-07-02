package project1.system;

import java.util.HashMap;
import project1.util.List;
import project1.util.Prompt;
import project1.vo.User;

public class Login {

  private List list;
  private Prompt prompt;

  private HashMap<String,Integer> idCheck = new HashMap<>();
  private HashMap<Integer,Integer> idNumCheck = new HashMap<>();

  public Login(Prompt prompt, List list) {
    this.prompt = prompt;
    this.list = list;
  }

  public boolean execute() {
    while (true) {
      printMenu();
      switch(prompt.inputString("> ")) {
        case "1":
          signUp();
          break;
        case "2":
          if (signIn()) {
            return true;
          }
          break;
        case "3":
          searchId();
          break;
        case "4":
          resetPassword();
          break;
        case "0":
          return false;
        default:
          System.out.println("잘못된 번호입니다.");
          break;
      }
    }
  }

  public void signUp() {
    User user = new User();
    user.setName(prompt.inputString("이름? "));
    user.setIdNum(idNumInput("아이디? "));
    user.setId(idInput("주민번호? "));
    user.setPassword(prompt.inputString("비밀번호? "));

    list.add(user);
  }

  public boolean signIn() {
    String id = prompt.inputString("아이디 : ");
    if(!idCheck.containsKey(id)) {
      System.out.println("존재하지 않는 ID입니다.");
      return false;
    }

    String password = prompt.inputString("비밀번호 : ");

    Object[] arr = list.toArray();
    for(Object obj : arr) {
      User user = (User)obj;
      if (password.equals(user.getPassword())) {
        return true;
      }
    }
    System.out.println("잘못된 비밀번호 입니다.");
    return false;
  }

  private void printMenu() {
    System.out.println("**은행 시스템");
    System.out.println("--------------------");
    System.out.println("1. 회원가입");
    System.out.println("2. 로그인");
    System.out.println("3. 아이디 찾기");
    System.out.println("4. 비밀번호 재설정");
    System.out.println("0. 종료");
  }

  private void searchId() {
    int idNum = prompt.inputInt("주민번호: ");
    Object[] arr = list.toArray();
    for(Object obj : arr) {
      User user = (User)obj;
      if (idNum == user.getIdNum()) {
        System.out.println("아이디: " + user.getId());
        prompt.inputString("계속하려면 아무키나 입력하세요");
        return;
      }
    }
    System.out.println("해당 정보의 아이디가 존재하지 않습니다.");
    return;
  }

  private void resetPassword() {
    int idNum = prompt.inputInt("주민번호: ");
    Object[] arr = list.toArray();
    for(Object obj : arr) {
      User user = (User)obj;
      if (idNum == user.getIdNum()) {
        user.setPassword(prompt.inputString("비밀번호? "));
        return;
      }
    }
    System.out.println("해당 정보의 아이디가 존재하지 않습니다.");
    return;
  }

  private String idInput(String title) {
    while (true) {
      String str = prompt.inputString(title);
      if(!idCheck.containsKey(str)) {
        idCheck.put(str, 1);
        return str;
      } else {
        System.out.println("중복된 아이디 입니다.");
      }
    }
  }

  private int idNumInput(String title) {
    while (true) {
      int num = prompt.inputInt(title);
      if(!idNumCheck.containsKey(num)) {
        idNumCheck.put(num, 1);
        return num;
      } else {
        System.out.println("이미 가입된 주민번호 입니다.");
      }
    }
  }

}
