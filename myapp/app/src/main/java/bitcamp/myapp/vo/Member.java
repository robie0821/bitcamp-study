package bitcamp.myapp.vo;

public class Member {

  private static int userId = 1;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;

  // 생성자는 인스턴스를 생성한 후 필드를 초기화 시키는 일을 한다.
  // 인스턴스를 사용할 때 문제가 없도록 유효한 값으로 초기화시킨다..
  // 기본 생성자(default constructor)는 개발자가 생성자를 정의하지 않을때
  // 컴파일러가 추가해주는 생성자다.
  // 물론 개발자가 직접 추가할 수도 있다.
  public Member() {
    this.no = userId++;
  }

  // 겟터/셋터는 인스턴스 필드의 값을 설정하고 꺼내는 메서드다.
  // 보통 외부에서 직접 필드에 접근하는 것을 막았을 때 사용한다.
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
}