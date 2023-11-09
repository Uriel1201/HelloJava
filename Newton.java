
 /******************************************************************************/

public class Newton {

    // find root x such that f(x) = 0 using Newton's method, starting at x0
    public static double findRoot(DifferentiableFunction f, double x0) {
        double EPSILON = 1e-15;
        double x = x0;
        double delta = f.evaluate(x) / f.differentiate(x);
        while (Math.abs(delta) > (EPSILON / x)) {
            x = x - delta;
            delta = f.evaluate(x) / f.differentiate(x);
            StdOut.println(x);
        }
        return x;
    }

    // sample client program
    public static void main(String[] args) {
        DifferentiableFunction f = new Sqrt(2.0);
        StdOut.println(findRoot(f, 2.0));
    }
}
