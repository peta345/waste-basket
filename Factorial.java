import java.util.Scanner;

class Factorial {
  static int factorial(int n){
    if(n>0)
      return n*factorial(n-1);
    else
      return 1;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    System.out.print("整数を入力>");
    int x = in.nextInt();
    System.out.println(x + "の階上は" + factorial(x) + "です");
  }
}
