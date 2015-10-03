// 2分探索

import java.util.Scanner;

class nibun {
  static int[] madoka(int mami[]){
    //minは最小値をいれる変数
    int min = 0;
    for(int i=0;i<mami.length;i++){
      min = i;
      for(int j=i+1;j<mami.length;j++){
        //n番目の要素とn+1番目の要素を比較
        //末尾まで走査するので、mami[min]には最小値が格納されます
        if(mami[min] > mami[j]){
          int flg = mami[min];
          mami[min] = mami[j];
          mami[j] = flg;
        }
      }
    }
    return mami;
  }
  static int homura(int b[], int n, int key){
    int a[] = madoka(b);
    for(int j=0;j<a.length;j++){
      System.out.println(a[j]);
    }
    int pl = 0;
    int pr =  n-1;//末尾
    while(pl <= pr){
      int pc = (pl + pr)/2; //中央要素を代入
      if(a[pc] == key){
        return pc;
      } else if(a[pc] < key){//後半に絞り込む
        pl = pc + 1;
      } else {//前半
        pr = pc - 1;
      }
    }
    return -1;//未発見
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.print("要素数:");
    int num = in.nextInt();
    int[] x = new int[num];

    for(int i=0;i<num;i++){
      System.out.print("[" + i + "] : ");
      x[i] = in.nextInt();
    }
    System.out.print("探す値:");
    int key = in.nextInt();
    int val = homura(x, num, key);
    if(val == -1){
      System.out.println("no mutch");
    } else {
      System.out.println("mutch!");
    }
  }
}
