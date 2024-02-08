/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  
 *  # THE DISCRETE SHANNON ENTROPY
 *  ******************************
 *  The qualities of this formula "H" play a central role in information theory as 
 *  measure of information, 
 *  choice and uncertainty. 
 *  This measure for example is the H in Boltzmann's famous H Theorem.
 *
 *  This quantity has some interesting properties:
 *  > 1. When we are certain of the outcomes in a set of observations 
 *    does H vanish, otherwise H is positive.
 *  > 2. For a total of m possible results in a set of observations, 
 *    H is maximum when all proportions of this possibilities are equal. 
 *    This is also the most uncertain situation.
 *  > 3. The uncertainty is never increased by experience. 
 *    It will be decreased unless this experience is 
 *    irrelevant (independent), in which case uncertainty is not changed
 *  *************************************************************************** */

public class DiscreteShannon {
  
  /****************************************************************************/
  public static double[] distBer(double p) {
     
    /*
       Generates a Bernoulli Distribution 
       return: A Probability Distribution Array 
       args: p -> Success Probability parameter of a Bernoulli Model */

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

    /*
       An index sampled from a set Ran(X) = {0, 1, 2, ..., dist.length - 1}
       args: 
             double[] dist -> An array representing a distribution
                              of a discrete random variable X.
                              dist[i] represents F_X(i) or 
                              in other wordsP(X <= i)
      return:
              An index following the distribution of a discrete random variable X*/

    double u = Math.random();
    if (u <= dist[0]) {

      return 0;
    }

    for (int i = 1; i < dist.length; i++) {

      if (u <= dist[i] && u > dist[i - 1]) return i;
    }
  }


  /**********************************************************************/
  public static double discreteShannon(int[] sample, int m) {
    
    /*
       The Shannon Entropy contained in a set of discrete data
       args:
             int[] sample -> An array containing random integers
             int m -> The total possibilities integers can take 
       return: 
             The grade of uncertainty the data presents*/

    if (m < 0) {

      throw new IllegalArgumentException("m must represent the cardinal of the sample set");
    }

    int[] freq = new int[m];
    int n = sample.length;
    for (int i = 0; i < n; i++) {

      if (sample[i] < 0) {

        throw new IllegalArgumentException("For practical reasons the sample must contain no negative integers");
      } else {

        freq[sample[i]]++;
      }
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

    /*
       A set of values in an numerical interval (start, end)
       args:
             double start ->
             double end ->
             int numPoints -> */

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
      System.out.println("success probability: " + points[i] + "-> uncertainty: " + shannon);
    }
  }
}
