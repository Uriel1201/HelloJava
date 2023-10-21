/************************************
 * Writing a program that reads in a sequence of integers and
 * prints both the integer that appears in a longest consecutive 
 * run and  the length of that run.
 ***********************************/

public class LongestRun {

  public static void main(String[] args) {
    
    int max = 0;
    int rep = 0;
    int x = 0; 
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

    System.out.println("Longest run: " + max + " consecutive " + best + "s.");
  }
}
