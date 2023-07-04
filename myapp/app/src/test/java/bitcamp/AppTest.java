package bitcamp;

class AppTest {
  public static void main(String[] args) {
    long num = 17;
    String str = convert(num);
    System.out.println(str);
    System.out.println(reconvert(str));
  }

  public static String convert(long num) {
    String str = "";
    while(num >= 1) {
      long temp = num % 2;
      num = num / 2;
      str = String.valueOf(temp) + str;
    }
    return str;
  }

  public static long reconvert(String str) {
    System.out.println(str);
    long sum = 0;
    for(int i = 0; i < str.length(); i++) {
      if (str.substring(i, i+1).equals("1")) {
        sum += (long)Math.pow(2, str.length() - i - 1);
      }
    }
    return sum;
  }
}
