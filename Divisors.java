/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Divisors {

    public static int gcd(int a, int b) {
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

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) 
            return 0;
        else {
            return (Math.abs(a) / gcd(a, b) * Math.abs(b));
        }
    }

    public static boolean areRelativelyPrime(int a, int b) {
        int g = gcd(a, b);
        boolean rp = (g == 1);
       
        return rp;
    }

    public static int totient(int n) {
        int t = 0;
        int m = 1;
        
        while (m <= n) {
            if (areRelativelyPrime(m, n)) t++;
            m++;
        }
        
        return t;
    }

    public static void main(String[] args) {
       
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        
        System.out.println("gcd(" + x + "," + y + ") = " + gcd(x, y));
        System.out.println("lcm(" + x + "," + y + ") = " + lcm(x, y));
        System.out.println("areRelativelyPrime(" + x + "," + y + ") = " + areRelativelyPrime(x, y));
        System.out.println("totient(" + x + ") = " + totient(x));
        System.out.println("totient(" + y + ") = " + totient(y));
    }
}

