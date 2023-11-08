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
        w = 0.0;
      }
      if (x == 0) {
        dist[x] = w;
      } else {
        dist[x] = dist[x - 1] + w;
      }
      if (dist[x] > 9999E-4) {
        dist[x] = 1.0;
      }
    }
  }

  /************************************************/
  public double getProbability(int x) {
    
    // x: number of successes '0<= x <=n'
    if (x == 0) {
      return dist[x];
    } else {
      return dist[x] - dist[x - 1];
    }
  }

  /************************************************/
  private void chart(String Title, String yLabel, String Parameter, ArrayList<Double> values) {
    
    StdDraw.title("Binomial Distribution")
    ArrayList<Double> success = new ArrayList<Double>();

    Color cTitle =  Color.decode("#000000");
    Color cyLabel = Color.decode("#06400a");
    Color cParam = Color.decode("#ae0001");
    Color dots = Color.decode("#ae0001");
    Color lines = Color.decode("#d3a625");

    for (int i = 0; i < trials + 1; i++) {
      Double itoD = new Double(i); 
      success.add(itoD);
    }
    
    // Set the scale
    double yMax = Double.NEGATIVE_INFINITY;
    for (double v : values) {
      if (v > yMax) yMax = v;
    }
    StdDraw.setXscale(-0.01 * trials, 1.2 * trials);
    StdDraw.setYscale(-0.01 * yMax, 1.25 * yMax); 

    // Draw title 
    StdDraw.setPenColor(cTitle);
    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
    StdDraw.text(0.6 * trials, 1.2 * yMax, Title);

    // Draw y-axis label
    StdDraw.setPenColor(cyLabel);
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
    StdDraw.textLeft(0.0, 1.1 * yMax, yLabel); 

    // Draw parameters 
    StdDraw.setPenColor(cParam);
    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
    StdDraw.textRight(1.14 * trials, 0.7 * yMax, Parameters);

    // Draw axes
    StdDraw.setPenColor(cyLabel);
    StdDraw.setFont(new Font("", Font.PLAIN, 12));
    StdDraw.textLeft(-0.005 * trials, yMax, String.format("%,d", yMax));
  }

  /************************************************/
  public void plotMass() {
    
    StdDraw.enableDoubleBuffering();
    String Title = "Probability Mass Function";
    String yLabel = "Mass_Value:";
    String pToS = String.valueOf(param);
    String tToS = String.valueOf(trials);
    String Parameters = "n: " + tToS + ",   p: " + pToS + ".";
    
    ArrayList<Double> values = new ArrayList<Double>();
    for (int i = 0; i < trials + 1; i++) {
      double mass = getProbability(i);
      values.add(mass);
    }
    
    chart(Title, yLabel, Parameters, values);
    Color points = Color.decode("#ae0001");
    Color lines = Color.decode("#d3a625");
    
    for (int i = 0; i < values.size(); i++) {
      
      StdDraw.setPenColor(points);
      StdDraw.setPenRadius(0.015);
      StdDraw.point(success.get(i), values.get(i));
      StdDraw.setPenColor(lines);
      StdDraw.setPenRadius(0.005);
      StdDraw.line(success.get(i), 0.0, success.get(i), values.get(i));
    }

    StdDraw.show();
  }  

  /************************************************/
  public double getDistribution(int x) {
    
    return dist[x];
  }

  /************************************************/
  public int sampling() {

    double u = Math.random();
    if (u <= dist[0]) {
      return 0;
    } else {
      for (int i = 1; i < trials + 1; i++) {
        if (u > dist[i - 1] && u <= dist[i]) {
          return i;
        }
      }
    }
  }
  
  /************************************************/
  public static void main(String[] args) {
  
    int n = Integer.parseInt(args[0]);
    int x = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[2]);

    BinomialDist np = new BinomialDist(n, p);

    System.out.println("Mass of x: " + np.getProbability(x));
    System.out.println("Distribution of x: " + np.getDistribution(x));
    
    double cum = np.getDistribution(n);
    System.out.println("The cumulative to n: " + cum);
    
    np.plotMass();

    int s = np.sampling();
    System.out.println("Printing a sample of size 1: " + s + " successes");
  }
}
