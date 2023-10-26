/*************************************
* Writing a program that computes 
* the Binomial Mass Function.
**************************************/

public class BinomialDist {

  public static double[] weights(int n, double p) {

    long[][] triangle = new long[n + 1][n + 2]; 
    double[] w = new double[n + 1];
    triangle[0][1] = 1;

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 2; j++) {
        triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]);
      }
    }

    for (int x = 0; x < n + 1; x++) {
      
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

      w[x] = _p * _q * triangle[n][x + 1];
    } 

    return w;
  } 

  public static void main(String[] args) {
    
    int n = Integer.parseInt(args[0]); 
    double p = Double.parseDouble(args[1]);
    double[] w = weights(n, p);
    
    for (int i = 0; i < n + 1; i++)
      System.out.println(w[i]);
  }
}
