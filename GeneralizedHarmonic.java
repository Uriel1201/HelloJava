/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class GeneralizedHarmonic {

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int r = Integer.parseInt(args[1]);

        if (n > 0) {
            double s = 1.0 * r;
            double sum = 0.0;
            for (int i = 1; i <= n; i++) {
                double j = 1.0 * i;
                double term = 1.0 / Math.pow(j, s);
                sum += term;
            }
            System.out.println(sum);

        }
        else {
            System.out.println("n must to be positive.");
        }
    }
}
