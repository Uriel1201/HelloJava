/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # RANDOM WALKERS
 *  ****************
 *
 *  ## The Weak Law of Large Numbers
 *  ********************************
 *  The weak law of large numbers states that the average of 
 *  the results obtained from a large number of independent 
 *  and identical random samples converges in probability to 
 *  the constant mu, where mu also represents the theoretical 
 *  mean from each sample.

 *  ## Monte Carlo Simulation
 *  *************************
 *  Estimating an unknown quantity by generating random samples 
 *  and aggregating the results is an example of a 
 *  Monte Carlo Simulation.
 *  *************************************************************************** */

public class RandomWalkers {

    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        if (r >= 0) {
           
            int sum = 0;
            
            for (int t = 0; t < trials; t++) {
               
                int i = 0;
                int j = 0;
                int manhattan = 0;
                int steps = 0;
                
                while (manhattan < r) {
                   
                    double p = Math.random();
                    if (p < 0.25) {
                        i++;
                    }
                    else if (p < 0.5) {
                        i--;
                    }
                    else if (p < 0.75) {
                        j++;
                    }
                    else {
                        j--;
                    }
                    
                    int ai = Math.abs(i);
                    int aj = Math.abs(j);
                    manhattan = ai + aj;
                    steps++;
                }
                sum += steps;
            }
            System.out.println("average number of steps = " + 1.0 * sum / trials);
        }
        else {
            System.out.println("r must to be no negative");
        }
    }
}
