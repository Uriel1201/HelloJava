/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  #TRINOMIAL COEFFICIENTS 
 *  ***********************
 *  Trinomial coefficients arise in combinatorics. The trinomial coefficient,
 *  denoted as T(n, k), represents the coefficient of x^(n+k) in the expansion
 *  of (1+x+x^2)^n. This script computes the trinomial coefficient T(n,k) 
 *  using dynamic programming. This version should be fast enough to handle 
 *  larger values of n and k.
 *  *************************************************************************** */

public class TrinomialDP {

    public static long trinomial(int n, int k) {
        
        if (k < 0) k = -k;
        if (k > n) return 0;
        
        else {
            long[][] triangle = new long[n + 1][2 * (n + 1)];
            triangle[0][1] = 1;
            
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < 2 * (i + 1); j++) {
                    if (j == 1) triangle[i][j] = 1;
                    else triangle[i][j] = triangle[i - 1][j]
                                        + triangle[i - 1][j - 1]
                                        + triangle[i - 1][j - 2];
                }
            }
            return triangle[n][n + k + 1];
        }

    }

    public static void main(String[] args) {
        
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
       
        if (n < 0)
            System.out.println("Error: n must to be no negative");
        else
            System.out.println(trinomial(n, k));
    }
}
