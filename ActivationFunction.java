/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class ActivationFunction {

    public static double heaviside(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        else if (x < 0.0) return 0.0;
        else if (x == 0.0) return 0.5;
        else return 1.0;
    }

    public static double sigmoid(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        x = -x;
        double s = 1.0 / (1.0 + Math.exp(x));
        return s;
    }

    public static double tanh(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        else if (x >= 20.0) return 1.0;
        else if (x <= -20.0) return -1.0;
        else {
            double y = -x;
            double ex = Math.exp(x);
            double ey = Math.exp(y);
            return (ex - ey) / (ex + ey);
        }
    }

    public static double softsign(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        else if (x == Double.POSITIVE_INFINITY) return 1.0;
        else if (x == Double.NEGATIVE_INFINITY) return -1.0;
        else return x / (1.0 + Math.abs(x));
    }

    public static double sqnl(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        else if (x <= -2.0) return -1.0;
        else if (x > -2.0 && x < 0.0) return x + Math.pow(x, 2) / 4.0;
        else if (x >= 0.0 && x < 2.0) return x - (x * x) / 4.0;
        else return 1.0;
    }

    public static void main(String[] args) {
        double act = Double.parseDouble(args[0]);
        System.out.println("heaviside(" + act + ") = " + heaviside(act));
        System.out.println("sigmoid(" + act + ") = " + sigmoid(act));
        System.out.println("tanh(" + act + ") = " + tanh(act));
        System.out.println("softsign(" + act + ") = " + softsign(act));
        System.out.println("sqnl(" + act + ") = " + sqnl(act));
    }
}

