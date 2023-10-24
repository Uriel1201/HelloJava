public class BinomialDist {

  public static void main(String[] args) {
    
    int n = Integer.parseInt(args[0]);
    int x = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[2]);
    int[][] triangle = new int[n + 1][n + 2]; 
    triangle[0][1] = 1;

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 2; j++) {
        triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]);
        // System.out.print(triangle[i][j] + " ");
      }
      // System.out.println();
    }
    
    int i = 0;
    double _p = 1;
    while (i < x) {
      _p = _p * p;
      i++;
    }

    int y = n - x;
    int j = 0;
    double q = 1 - p;
    double _q = 1;
    while (j < y) {
      _q  = _q * q;
      j++;
    }

    double weight = _p * _q * triangle[n][x + 1];
    System.out.println("P(X =" + x + "): " + weight);
  }
}
