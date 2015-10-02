// 線形探索

import java.util.Scanner;

class banpei {
  static int homura(int[] a, int n, int key){
    int i = 0;
    a[n] = key;
    while(true){
      if(a[i] == key){
        break;
      }
      i++;
    }
    //i==nすなわち、末尾まで探索したということなので、マッチしなかっということです。
    return i == n? -1 : i;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.print("要素数 : ");
    int num = in.nextInt();
    // 配列の作成.番兵分を一つ多めにとります
    int [] x = new int[num + 1];
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
