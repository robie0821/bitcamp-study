package bitcamp.util;

public class MenuGroup extends Menu {

  ArrayList childs;

  public MenuGroup(String title) {
    super(title);
    childs = new ArrayList();
  }

  public void add(Menu menu) {
    childs.add(menu);
  }

  @Override
  public void execute(BreadcrumbPrompt prompt) {

    prompt.appendBreadcrumb(getTitle());

    printMenu();

    while (true) {
      String input = prompt.inputMenu();
      if (input.equals("menu")) {
        printMenu();
        continue;
      }

      int menuNo = Integer.parseInt(input);
      if (menuNo < 0 || menuNo > childs.size()) {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      } else if (menuNo == 0) {
        prompt.removeBreadcrumb();
        return;
      } else {
        Menu menu = (Menu) childs.get(menuNo - 1);
        menu.execute(prompt);
      }
    }
  }

  private void printMenu() {
    for (int i = 0; i < childs.size(); i++) {
      Menu menu = (Menu) childs.get(i);
      System.out.printf("%d. %s\n", i + 1, menu.getTitle());
    }
    System.out.println("0. 이전/종료");
  }
}
