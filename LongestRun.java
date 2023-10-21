public class LongestRun {

  public static void main(String[] args) {
    
    int max = 0;
    int rep = 1;
    int x = StdIn.readInt(); 
    int best = x;

    while (!StdIn.isEmpty()) {
      int y = StdIn.readInt();
      if (x == y) {
        rep++;
      } else {
        x = y;
        rep = 1;
      }
      if (max < rep) {
        max = rep;
        best = y;
      }
    }

    System.out.println("Longest run: " + max + "consecutive" + best + "s.");
  }
}
