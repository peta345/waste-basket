import java.util.Scanner;

class henkan {
  static int hoge(int x, int r, char[] d){
    int digits = 0;
    String dchar = "0123456789abcdefghijklmnopqestuvwxy";

    do {
      d[digits++] = dchar.charAt(x%r);
      x /= r;
    } while(x != 0);
    return digits;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int no, cd, dno, retry;
    char[] cno = new char[32];

    System.out.println("１０進数を変換します");
    do {
      do {
        System.out.print("変換する非負の数:");
        no = in.nextInt();
      } while(no<0);

      do {
        System.out.print("何進数に変換しますか:");
        cd = in.nextInt();
      } while(cd<2 || cd>36);

      dno  = hoge(no,cd,cno);
      System.out.print(cd + "進数では");
      for (int i=dno-1; i>= 0; i--)
        System.out.print(cno[i]);
      System.out.println("です.");

      System.out.print("もう一度しますか 1 はい　| 0 いいえ");
      retry = in.nextInt();
    } while(retry==1);
  }
}
