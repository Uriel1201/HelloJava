/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  
 *  # THE DISCRETE SHANNON ENTROPY
 *  ******************************
 *  The qualities of this formula "H" play a central role in information theory as 
 *  measure of information, choice and uncertainty. 
 *  This measure for example is the H in Boltzmann's famous H Theorem.
 *
 *  This quantity has some interesting properties:
 *  > 1. When we are certain of the outcomes in a set of observations 
 *       does H vanish, otherwise H is positive.
 *  > 2. For a total of m possible results in a set of observations, 
 *       H is maximum when all proportions of this possibilities are equal. 
 *       This is also the most uncertain situation.
 *  > 3. The uncertainty is never increased by experience. 
 *       It will be decreased unless this experience is 
 *       irrelevant (independent), in which case uncertainty is not changed
 *  *************************************************************************** */

public class DiscreteShannon {
  
  /****************************************************************************/
  public static double[] distBer(double p) {

    /**
      *  Calculates the Bernoulli distribution for a given success probability parameter
      *  @param p The success probability parameter of a Bernoulli model
      *  @return An array representing the Bernoulli distribution
      *  @throws IllegalArgumentException if p is not a valid probability measure
      */
    
    if (p > 1 || p < 0) {
      throw new IllegalArgumentException("p must represent a probability measure");
    }
    
    double[] ber = new double[2];
    ber[0] = 1.0 - p;
    ber[1] = 1.0;

    return ber;
  }

  
  /**********************************************************************/
  public static int sampling(double[] dist) {

    /**
      *  An index sampled from a set Ran(X) = {0, 1, 2, ..., dist.length - 1}.
      *  @param dist The Cumulative Distribution Function of a discrete variable X.
      *              For example dist[i] represents F_X(i).
      *  @return An index following the distribution of a discrete random variable X.
      *  @throws IllegalArgumentException if dist does not represent a probability distribution
      */
    int n = dist.length;
    if (dist[n - 1] != 1) {
      
      throw new IllegalArgumentException("This array must represent a probability distribution");
    }

    for (int i = 1; i < n; i++) {

      if (dist[i - 1] < 0 || dist[i - 1] > 1) {
        
        throw new IllegalArgumentException("This array must represent a probability distribution");
      }
      if (dist[i] < dist[i - 1]) {
        throw new IllegalArgumentException("This array must represent a probability distribution");
      }
    }

    double u = Math.random();
    for (int i = 0; i < n; i++) {

      if (u <= dist[i]) {

        return i;
      }
    }
  }


  /**********************************************************************/
  public static double discreteShannon(int[] sample, int m) {
    
    /**
      *  The Shannon Entropy measured in a set of discrete data.
      *  @param sample is an array containing random integers.
      *  @param m must represent the total possibilities the integers can take. 
      *  In the same way the method assumes this possibilities are in {0, 1, 2, ...., m - 1}
      *  @return the grade of uncertainty the data presents.
      *  @throws IllegalArgumentException if m does not represent the cardinal of the sample.
      */

    if (m < 0) {

      throw new IllegalArgumentException("m must represent the cardinal of the sample set");
    }

    int[] freq = new int[m];
    int n = sample.length;
    
    for (int i = 0; i < n; i++) {
      
      freq[sample[i]]++;
    }

    double h = 0.0;
    for (int i = 0; i < m; i++) {
      
      if (freq[i] > 0) {

        double p = 1.0 * freq[i] / n;
        double arg = -1.0 * p * Math.log(p) / Math.log(2);
        h += arg;
      }
    }

    return h;
  }


  /**********************************************************************/
  public static double[] linspace(double start, double end, int numPoints) {

    /**
      *  A discrete set of values in an numerical interval (start, end).
      *  @param start is the lower bound of the numerical interval.
      *  @param end is the upper bound of the numerical interval.
      *  @param numPoints  are the elements you want to generate 
      *  @return a set of n = numPoints values
      */

    double[] linArray = new double[numPoints];
    double step = (end - start) / (numPoints - 1);

    for (int i = 0; i < numPoints; i++) {

      linArray[i] = start + i * step;
    }

    return linArray;
  }
    

  /************************************************/
  public static void main(String[] args) {
    
    int numPoints = Integer.parseInt(args[0]);
    int sampleSize = Integer.parseInt(args[1]);
    
    int[] points = linspace(0, 1, numPoints);

    for (int i = 0; i < numPoints; i++) {

      double[] dist = distBer(points[i]);
      int[] sample = new int[sampleSize];
      
      for (int j = 0; j < sampleSize; j++) {

        sample[j] = sampling(dist);
      }

      shannon = discreteShannon(sample, 2);
      System.out.println("success probability: " + points[i] + "; uncertainty: " + shannon);
    }
  }
}
