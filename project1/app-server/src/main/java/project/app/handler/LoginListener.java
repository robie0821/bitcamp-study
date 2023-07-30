package project.app.handler;

import java.io.IOException;
import project.app.dao.StudentDao;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class LoginListener implements StudentActionListener {

  StudentDao studentDao;

  public LoginListener(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    while (true) {
      Student std = new Student();
      std.setEmail(prompt.inputString("아이디? "));
      std.setPassword(prompt.inputString("암호? "));

      Student loginUser = studentDao.findByEmailAndPassword(std);
      if (loginUser == null) {
        prompt.println("회원 정보가 일치하지 않습니다.");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
      prompt.end();
    }
  }
}