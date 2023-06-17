/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class RevesPuzzle {

    private static void reves(int n, String f, String t1, String t2, String to) {
        if (n <= 0) return;
        else {
            int k = (int) Math.round(n + 1 - Math.sqrt(2 * n + 1));
            reves(k, f, to, t1, t2);
            hanoi(n, k, f, t1, to);
            reves(k, t2, f, t1, to);
        }
    }

    private static void hanoi(int n, int k, String f, String t, String to) {
        if (n <= k) return;
        else {
            hanoi(n - 1, k, f, to, t);
            System.out.println("Move disc " + n + " from " + f + " to " + to);
            hanoi(n - 1, k, t, f, to);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        reves(n, "A", "B", "C", "D");
    }
}
