/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # THE DISCRETE SHANNON ENTROPY
 *  ******************************
 *  The qualities of this formula "H" play a central role 
 *  in information theory as measure of information, 
 *  choice and uncertainty. 
 *  This measure for example is the H in 
 *  Boltzmann's famous H Theorem.
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

public class ShannonEntropy {
  
    public static void main(String[] args) {
       
      int m = Integer.parseInt(args[0]);
        int[] f = new int[m + 1];
        int n = 0;

        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            if (x < m + 1 && x > 0) {
                f[x]++;
                n++;
            }
        }
        double h = 0.0;
        for (int i = 1; i < m + 1; i++) {
            if (f[i] > 0) {
                double p = 1.0 * f[i] / n;
                double arg = -p * Math.log(p) / Math.log(2);
                h += arg;
            }
        }
        StdOut.printf("%.4f", h);
    }
}
