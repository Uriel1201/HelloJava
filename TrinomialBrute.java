/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 * 
 *  #TRINOMIAL COEFFICIENTS
 *  ***********************
 *  Trinomial coefficients arise in combinatorics. The trinomial coefficient, 
 *  denoted as T(n, k), represents the coefficient of x^(n+k) in the expansion 
 *  of (1+x+x^2)^n. This class implements a recursive function in a brute-force manner 
 *  to compute the coefficients by utilizing their corresponding recurrence relation. 
 *  However, this approach may not be the most efficient way to solve this problem.
 *  *************************************************************************** */

public class TrinomialBrute {

    public static long trinomial(int n, int k) {

        /**
          **/
        if (n == 0 && k == 0) return 1;
        else if (k < -n || n < k) return 0;
        else return trinomial(n - 1, k - 1) + trinomial(n - 1, k) + trinomial(n - 1, k + 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        System.out.println(trinomial(n, k));
    }
}
