// 線形探索

import java.util.Scanner;

class line {
  static int homura(int[] a, int n, int key){
    int i = 0;
    while(true){
      if(i==n){// not found
        return -1;
      }
      if(a[i] == key){// found
        return i;
      }
      //次の要素へ
      i++;
    }
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.print("要素数 : ");
    int num = in.nextInt();
    // 配列の作成
    int [] x = new int[num];
    for(int i=0;i<num;i++){
      System.out.print("x[" + i + "] : ");
      x[i] = in.nextInt();
    }

    System.out.print("探す値 : ");
    int madoka = in.nextInt();

    int flg = homura(x,num,madoka);

    if(flg == -1){
      System.out.println("No match");
    } else {
      System.out.println("Match!!");
    }
  }
}
