/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Ramanujan {

    public static boolean isRamanujan(long n) {

        long c = (long) Math.cbrt(n);
        int count = 0;

        for (long a = 1; a <= c && count < 2; a++) {
            long a3 = a * a * a;
            long b = (long) (Math.cbrt(n - a3));
            long b3 = b * b * b;
            if (a3 + b3 == n && b - a > 0) count++;
        }
        return (count == 2);
    }

    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        System.out.println(isRamanujan(n));
    }
}
