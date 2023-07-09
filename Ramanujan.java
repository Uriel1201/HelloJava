public class Ramanujan {
    
    public static boolean isRamanujan(long n) {
        
        long c = (long) Math.cbrt(n);
        int count = 0;
        
        for (long a = 1; a <= c && count != 2; a++) {
            long a3 = (long) Math.pow(a,3);
            long b = (long) (Math.cbrt(n - a3));
            long b3 = (long) Math.pow (b,3);
            if (a3 + b3 == n && b > 0) count++;
        }
    return (count == 2);
    }
    
    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        System.out.println(isRamanujan(n));
        
    }
    
}
