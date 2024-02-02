/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  
 *  # RANDOM WALKER
 *  ***************
 *  This process is known as a two-dimensional random walk. 
 *  This process is a discrete version of a natural phenomenon
 *  known as Brownian motion.
 *  *************************************************************************** */

public class RandomWalker {
    
    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        
        if (r >= 0) {
            
            int i = 0;
            int j = 0;
            System.out.println("(" + i + "," + j + ")");

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
                
                System.out.println("(" + i + "," + j + ")");
                int ai = Math.abs(i);
                int aj = Math.abs(j);
                manhattan = ai + aj;
                steps++;
            }
            System.out.println("Steps = " + steps);
        }
        else {
            System.out.println("r must be no negative");
        }
    }
}
