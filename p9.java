import java.util.Scanner;

class p9 {
  static int med3(int a, int b, int c){
    if(a >= b)
      if(b >= c)
        return b;
      else if(a <= c)
        return a;
      else
        return c;
    else if(a > c)
      return a;
    else if(b > c)
      return c;
    else
      return b;
  }

  /*
  - 選択ソートをするclassです
  - データ列中で一番小さい値を探し、1番目の要素と交換する。
  -次に、2番目以降のデータ列から一番小さい値を探し、2番目の要素と交換する。
  -これを、データ列の最後まで繰り返す
  -（厳密には、データ列の最後より1つ手前までの繰り返しでよい。(wikiから引用)
  */
  static int homura(int mami[]){
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
    return mami[1];
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.println("みっつの中央値を求めます");
    // System.out.print("a > "); int a = in.nextInt();
    // System.out.print("b > "); int b = in.nextInt();
    // System.out.print("c > "); int c = in.nextInt();
    int[] mami = new int[3];//mami[0],mami[1],mami[2]が確保
    for(int i=0;i<3;i++){
      System.out.print(i+1 + "番目 >");
      mami[i] = in.nextInt();
    }
    System.out.println("中央値は" + homura(mami) + "です,");
  }
}
