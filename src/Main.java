import box.SimpleBoxSet;
import box.BoxSet;
/**
 * This is the main class.
 */
public class Main {
  public static void main(String[] args) {

    System.out.println("Hello World!");
    BoxSet x = new SimpleBoxSet();
    x.add();
    x.add(1,1,1,1);
    int[][] b = x.getBoxes();
    for(int i=0;i<b.length;i++){
      for(int j=0;j<b[i].length;j++){
        System.out.print(b[i][j]+" ");
      }
      System.out.println();
    }
  }
}