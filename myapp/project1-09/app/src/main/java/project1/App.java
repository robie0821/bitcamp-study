package project1;

import project1.io.io;
import project1.prompt;

public class App {


    public static void main(String[] args) {
      
      io.printTitle();
      
      for (int i = 0; i < io.size; i++) {
        
        io.inputGrade(i);

        if (!promptContinue()) {
          break;
        }
      }
      
      io.printGrade();
      
      prompt.close();
    }

    static boolean promptContinue() {
      String response = prompt.inputString("계속 하시겠습니까?(Y/n) ");
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }

}
