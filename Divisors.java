/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  
 *  #DIVISORS METHODS
 *  *****************
 *  Euler's Totient function plays a crucial role in various areas of mathematics 
 *  including number theory, cryptography, and algorithms. 
 *  For instance the security of RSA encryption relies on the difficulty of 
 *  factoring large numbers, which is facilitated by the use of this function 
 *  in generating public and private keys.
 *  *************************************************************************** */

public class Divisors {

    
    /************************************************/
    public static int gcd(int a, int b) {
        
        /**
          *  The greatest common divisor of two positive integers a and b
          *  @param a first integer 
          *  @param b second integer
          *  @return The largest divisor common to a and b, this method implements the 
          *          Euclidean algorithm.
          */
        
        if (b < 0) b = -b;
        if (a < 0) a = -a;
        
        if (b > a) {
            int s = b;
            b = a;
            a = s;
        }
        
        if (b == 0) {
            return a;
        }
            
        else {
            while (b > 0) {
                int t = a;
                a = b;
                b = t % a;
            }
            
            return a;
        }
    }


    /************************************************/
    public static int lcm(int a, int b) {
        
        /**
          *  The least common multiple m of two integers a and b
          *  @param a The first integer
          *  @param b The second integer
          *  @return The smallest positive number for which
          *          there exist positive integers n_a and n_b
          *          such that n_a*a = n_b*b = m
          */
        
        if (a == 0 || b == 0) 
            return 0;
        else {
            return (Math.abs(a) / gcd(a, b) * Math.abs(b));
        }
    }


    /************************************************/
    public static boolean areRelativelyPrime(int a, int b) {
        
        int g = gcd(a, b);
        boolean rp = (g == 1);
       
        return rp;
    }


    /************************************************/
    private static int[] dynamicPhi(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        
        int[] phi = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            phi[i] = i;
        }
        
        for (int i = 2; i < n + 1; i++) {
            
            if (phi[i] == i) {
                
                for (int j = i; j < n + 1; j += i) {
                    
                    phi[j] -= phi[j] / i;
                }
            }
        }

        return phi;
    }


    /************************************************/
    public static int totient(int n) {

        int[] phi = dynamicPhi(n);
        
        return phi[n];
    }


    /************************************************/
    public static void main(String[] args) {
       
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Math.max(x, y);

        int[] totients = dynamicPhi(z);
        System.out.println("gcd(" + x + "," + y + ") = " + gcd(x, y));
        System.out.println("lcm(" + x + "," + y + ") = " + lcm(x, y));
        System.out.println("areRelativelyPrime(" + x + "," + y + ") = " + areRelativelyPrime(x, y));
        System.out.println("totient(" + x + ") = " + totients[x]);
        System.out.println("totient(" + y + ") = " + totients[y]);
    }
}
