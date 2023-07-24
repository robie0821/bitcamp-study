package project.app.handler;

import java.util.Iterator;
import java.util.List;
import project.app.vo.Student;
import project.util.BreadcrumbPrompt;

public class GradeListListener extends AbstractStudentListener {

  public GradeListListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("--------------------------------------------------");
    System.out.println("번호, 이름, C++, Java, Python, Linux, 학점, 장학금");
    System.out.println("--------------------------------------------------");

    Iterator<Student> iterator = list.iterator();
    while (iterator.hasNext()) {
      Student std = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, %s, %.2f, %b\n",
          std.getNo(), std.getName(), std.getCpp(), std.getJava(),std.getPython(), std.getLinux(),
          std.getGrade(), std.isScholar());
    }
  }
}
