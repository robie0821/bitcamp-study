package project.myapp.handler;

import java.util.Iterator;
import java.util.List;
import project.myapp.vo.Member;
import project.util.BreadcrumbPrompt;

public class MemberListListener extends AbstractMemberListener {

  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    Iterator<Member> iterator = list.iterator();
    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(),
          toGenderString(m.getGender()));
    }
  }
}
