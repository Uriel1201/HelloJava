public class BinomialDist {

  int n = Integer.parseInt(args[0]);
  double[][] weights = new double[n + 1][n + 1]; 
  weights[1][1] = 1.0;

  for (int i = 2; i < n + 1; i++) {
    for (int j = 1; j < i + 1; j++) {
      weights[i][j] = (weights[i - 1][j - 1] + weights[i - 1][j]) * 0.5;
      System.out.print(weights[][] + " *");
    }
    System.out.println();
  }
}
