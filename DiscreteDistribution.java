/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # DISCRETE DISTRIBUTION SIMULATION 
 *  **********************************
 *  This class presents a simple algorithm for sampling
 *  from a discrete probability distribution p(x) defined over 
 *  a countably finite set.
 *  In probability theory, this is known as sampling from a 
 *  discrete distribution.
 *  First define the indices in the range of a discrete random 
 *  variable, and then assigne to this index a 
 *  weight of frequency.
 *  *************************************************************************** */

public class DiscreteDistribution {

   public static void main(String[] args) {

      int n = args.length;
      int m = Integer.parseInt(args[0]); // Size of the sample 

      if (m < 0) {
         throw new IllegalArgumentException("The sample size must be positive");
      }
      
      int[] a = new int[n - 1];
      for (int i = 0; i < n - 1; i++) {
         
         int d = Integer.parseInt(args[i + 1]);
         if (d < 0) {
            throw new IllegalArgumentException("This value must represent a measure");
         }
         else {
            a[i] = d;
         }
      }

      int[] s = new int[n];
      for (int i = 1; i < n; i++) {
         
         s[i] = s[i - 1] + a[i - 1];
      }

      for (int k = 0; k < m; k++) {
         
         int p = (int) (Math.random() * s[n - 1]);
         for (int j = 1; j < n; j++) {
            
            if (s[j] > p && s[j - 1] <= p) {
               System.out.print(j + "  ");
            }
         }
      }
   }
}
