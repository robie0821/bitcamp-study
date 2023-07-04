package project.myapp.handler;

import java.util.Iterator;
import java.util.List;
import project.myapp.vo.Student;
import project.util.BreadcrumbPrompt;

public class StudentListListener extends AbstractStudentListener {

  public StudentListListener(List<Student> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("학번, 이름");
    System.out.println("---------------------------------------");

    Iterator<Student> iterator = list.iterator();

    while (iterator.hasNext()) {
      Student std = iterator.next();
      System.out.printf("%d, %s\n",
          std.getNo(),
          std.getName());
    }
  }
}











