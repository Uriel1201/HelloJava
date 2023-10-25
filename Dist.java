public class Dist {
  public static void main(String[] args) {

    int n = 40
    int[][] triangle = new int[n + 1][n + 2]; 
   // double[] w = new double[n + 1];
    triangle[0][1] = 1;

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 2; j++) {
        triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]);
        System.out.print(triangle[i][j] + " ");
      }
      System.out.println();
    }

   // for (int x = 0; x < n + 1; x++) {
      
    int i = 0;
    double _p = 1;
    while (i < 11) {
      _p = _p * p;
      i++;
    }

    int y = n - 11;
    int j = 0;
    double q = 1 - p;
    double _q = 1;
    while (j < y) {
      _q  = _q * q;
      j++;
    }
    System.out.println("_p: " + _p);
    System.out.println("_q: " + _q);
    double w = _p * _q * triangle[n][12];
  } 
}
