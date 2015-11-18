//***************************************************
//* 　              List 3-10　                                               *　
//*　       チェイン法によるハッシュの利用例                       *
//****************************************************
import java.util.Scanner;

public class ChainHashTester {
 static Scanner stdIn =  new Scanner(System.in);


 //--- データ（会員番号　＋　氏名）---
 static class Data {
  static final int NO =   1;
  static final int NAME = 2;

  private Integer no;
  private String  name;

  //--- キー値 ---
  Integer keyCode(){
   return no;
  }
  //---　文字列表現を返す　---
  public String toString(){
   return name;
  }
  //--- データの読み込み ---
  void scanData(String guide, int sw){
   System.out.println(guide + "するデータを入力して下さい。");

   if ((sw & NO) == NO) {
    System.out.print("番号　：");
    no = stdIn.nextInt();
   }
   if ((sw & NAME) == NAME) {
    System.out.print("名前　：");
    name = stdIn.next();
   }
  }
 }

 //--- メニュ列挙型 ---
 enum Menu{
  ADD(         "データ追加"),
  REMOVE(      "データ削除"),
  SEARCH(      "データ探索"),
  DUMP(        "全データ表示"),
  TERMINATE(   "終了");

  private final String message;           //表示用文字列

  static Menu MenuAt(int idx) {           //序数がidxである列挙を返す
   for (Menu m : Menu.values())
    if (m.ordinal() == idx)
     return m;
   return null;
  }
  Menu(String string){                      // コンストラクタ
   message = string;
  }

  String getMessage(){                      //表示用文字列を返す
   return message;
  }
 }

 //---メニュ選択ー---
 static Menu SelectMenu(){
  int key;
  do {
   for (Menu m : Menu.values()){ //values()はenum のメンバに総当りする
      // ordinal()はADDならordinal = 1, DUMP = 3
    System.out.printf("(%d) %s  ", m.ordinal(),m.getMessage());
    if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.TERMINATE.ordinal())
    // データ探索　なら改行
     System.out.println();
   }
   System.out.print(":");
   // 番碁を受け取り
   key = stdIn.nextInt();
   // 0未満４越えの場合繰り返す
  }   while ( key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
  // 探索してくる
   return Menu.MenuAt(key);
   }



 public static void main(String[] args) {
  Menu menu;                              // メニュー
  Data data;                              //追加用データ参照
  Data temp = new Data();                //読み込み用データ

  ChainHash<Integer , Data> hash = new ChainHash<Integer, Data>(13);

  do {
   switch (menu = SelectMenu()) {
   case ADD:                          //追加
     data = new Data();
     data.scanData("追加",Data.NO | Data.NAME);
     hash.add(data.keyCode(), data);
     break;

   case REMOVE:                        //削除
    temp.scanData("削除", Data.NO);
    hash.remove(temp.keyCode());
    break;

   case SEARCH:                         //探索
    temp.scanData("探索",Data.NO);
    Data t = hash.search(temp.keyCode());
    if (t != null)
     System.out.println("そのキーを持つでーたは"+ t + "です。");
    else
     System.out.println("該当するデータはありません。");
    break;

   case DUMP:                           //表示
    hash.dump();
    break;

   }
  }   while( menu != Menu.TERMINATE);
 }

}

//*********************************************
//*                   List 3-9[A]    P-103                       *
//*                        チェイン法によるハッシュ            *
//*********************************************


class ChainHash<K, V> {

 // --- ハッシュを構成する要素　----
 //
   class Node<K,V> {
    private K key;                 //キー値
    private V data;                //データ
    private Node<K,V> next;        //後続ノードへの参照

 // --- コンストラクタ ----
      Node(K key,V data, Node<K,V> next) {
       this.key  = key;
       this.data = data;
       this.next = next;
      }
    //--- キー値を返す ---
      K getKey() {
       return key;
      }
    //--- データを返す ---
      V getValue() {
       return data;
      }
 //--- キーのハシュを返す ---
      public int hashCode(){
          return key.hashCode();
      }
   }
//--------------------------------------------------------------------------------------------
      private int size;                //ハシュ表の大きさ
      private Node<K,V>[] table;        //ハシュ表

      //--- コンストラクタ ---//
      public  ChainHash(int capacity){
         try {
          table = new Node[capacity];
          this.size = capacity;
         }catch (OutOfMemoryError e) {
          this.size = 0;
         }
      }

      //---ハシュ値を求める---
      public int hashValue(Object key) {
       return key.hashCode() % size;
      }
//--------------------------------------------------------------------------------------------
     //---キー値keyをもつ要素の探索（データを返却）---
       public V search(K key) {
        int hash = hashValue(key);            //探索するデータのハッシュ値
        Node<K,V> p = table[hash];            //着目ノード

        while (p != null) {
         if (p.getKey().equals(key))
         return p.getValue();             //探索成功
         p = p.next;                       //後続ノードに着目
        }
        return null;                         //探索失敗
         }

     //--- キー値key・データdataを持つ要素の追加　---
       public int add(K key, V data) {
        int hash = hashValue(key);             //追加するデータのハッシュ値
        Node<K,V> p = table[hash];              //着目ノード

        while (p != null) {
         if (p.getKey().equals(key))         //このキー値は登録済
          return 1;
         p = p.next;                         //後続ノードに着目
       }
       Node<K,V> temp = new Node<K,V>(key, data, table[hash]);
       table[hash] = temp;                         //ノードを挿入
       return 0;
      }
//---------------------------------------------------------------------------------------------
    //----キー値keyをもつ要素の削除-------
       public int remove(K key) {
        int hash = hashValue(key);             //削除するデータのハシュ値
        Node<K,V> p = table[hash];             //着目ノード
        Node<K,V> pp = null;                  //前回の着目ノード

        while (p != null){
         if (p.getKey().equals(key)){       //見つけたら
          if (pp == null)
           table[hash] = p.next;
          else
           pp.next = p.next;
          return 0;
         }
         pp = p;
         p = p.next;                        //後続ノードに着目
        }
        return 1;                             //そのキー値は存在しない
             }
     //---- ハッシュ表をダンプ ------
       public void dump(){
        for ( int i = 0; i < size; i++){
         Node<K,V> p = table[i];
         System.out.printf("%02d  ",i);
         while (p != null){
          System.out.printf("→%s (%s)  ", p.getKey(), p.getValue());
          p = p.next;
         }
         System.out.println();

  }
    }

}
