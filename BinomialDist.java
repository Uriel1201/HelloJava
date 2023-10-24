public class BinomialDist {

  public static void main(String[] args) {
    
    int n = Integer.parseInt(args[0]);
    int x = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[1]);
    int[][] triangle = new int[n + 1][n + 2]; 
    triangle[0][1] = 1;

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 2; j++) {
        triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]);
      }
    }

    double w = Math.pow(p, x);
    double q = 1 - p;
    int m = n - x;
    double v = Math.pow(q, m);
    double weight = w * v;
    System.out.println("P(X = x): " + (weight * triangle[n][x + 1]);
  }
}
