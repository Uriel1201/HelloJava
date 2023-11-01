/*****************************************
* Writing a class that computes methods 
* related with the Binomial Distribution.
*****************************************/

import java.math.BigInteger;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;

public class BinomialDist {

  private final int trials;
  private final double param;
  private final double[] weights;
  private final double[] dist;

  /************************************************/
  private static BigInteger[][] pascal(int n) {

    BigInteger[][] triangle = new BigInteger[n + 1][n + 2];
    
    for (int i = 0; i < n + 1; i++)
      for (int j = 0; j < n+ 2; j++)
        triangle[i][j] = BigInteger.ZERO;
    
    triangle[0][1] = BigInteger.ONE;
    
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 2; j++) {
        triangle[i][j] = triangle[i - 1][j - 1].add(triangle[i - 1][j]);
      }
    }
    
    return triangle;
  }

  /************************************************/
  public BinomialDist(int n, double p) {
    trials = n;
    param = p;
    weights = new double[n + 1];
    dist = new double[n + 1];
    
    BigInteger[][] triangle = pascal(n);
    
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
      double comb = triangle[n][x + 1].doubleValue();
      double w = _p * _q * comb;
      if (w < 1.0E-6) {
        weights[x] = 0.0;
      } else {
        weights[x] = w;
      }
      if (x == 0) {
        dist[x] = w;
      } else {
        dist[x] = dist[x - 1] + w;
      }
    }
  }

  /************************************************/
  public double getProbability(int x) {
    // x: number of successes '0<= x <=n'
    double pX = weights[x];
    return pX;
  }

  /************************************************/
  private void chart(String Title, String xLabel, String Parameters) {
    
    ArrayList<Integer> success = new ArrayList<Integer>();
    ArrayList<Double> values = new ArrayList<Double>();

    Color cTitle =  Color.decode("#000000");
    Color cxLabel = Color.decode("#06400a");
    Color cParam = Color.decode("#ae0001");
    Color dots = Color.decode("#ae0001");
    Color lines = Color.decode("#d3a625");

    for (int i = 0; i < trials + 1; i++) {
      success.add(i);
      values.add(weights[i]);
    }
    
    // Set the scale 
    StdDraw.setXscale(-0.01 * trials, 1.2 * trials);
    StdDraw.setYscale(-0.01 * yMax, 1.25 * yMax); 

    // Draw title 
    StdDraw.setPenColor(cTitle);
    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
    StdDraw.text(0.6 * trials, 1.2, Title);

    // Draw y-axis label
    StdDraw.setPenColor(cyLabel);
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
    StdDraw.textLeft(0.0, 1.1, yLabel); 

    // Draw parameters 
    StdDraw.setPenColor(cParam);
    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
    StdDraw.textRight(1.14 * trials, 0.7, Parameters);

    // Draw axes
    StdDraw.setPenColor(cxLabel);
    StdDraw.setFont(new Font("", Font.PLAIN, 12));
    for (int s : success) {
      StdDraw.text(s, 1.02, String.format("%,d", s));
    } 
  }

  /************************************************/
  public void plotMass() {
    String Title = "Probability Mass Function";
    String xLabel = "Success (units):";
    String pToS = String.valueOf(param);
    String tToS = String.valueOf(trials);
    String Parameters = "n: " + tToS + ",   p: " + pToS + ".";
    chart(Title, xLabel, Parameters);
  }  

  /************************************************/
  public double getDistribution(int x) {
    double d = dist[x];
    return d;
  } 
  
  /************************************************/
  public static void main(String[] args) {
  
    int n = Integer.parseInt(args[0]);
    int x = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[2]);

    BinomialDist np = new BinomialDist(n, p);

    double cumm = 0.0;
    System.out.println("prob of x: " + np.getProbability(x));

    for (int i = 0; i < n + 1; i++) {
      double sProb = np.getProbability(i);
      cumm += sProb;
      // System.out.println(sProb);
    }
    
    System.out.println("The cummulative value of all weights is: " + cumm);
    np.plotMass();
    double distX = np.getDistribution(x);
    System.out.println("Distribution Function in " + x + ": " + distX);
  }
}
