/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

// A program Checkerboard.java that takes a command-line integer n and
// plots an n-by-n checkerboard pattern to standard drawing.

public class Checkerboard {

    public static void main(String[] args) {

        StdDraw.enableDoubleBuffering();

        int n = Integer.parseInt(args[0]);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);

        for (int i = 0; i < n; i++) {
            double ci = 1.0 * (2 * i + 1) / 2;
            for (int j = 0; j < n; j++) {
                double cj = 1.0 * (2 * j + 1) / 2;
                if ((i + j) % 2 == 0) {
                    StdDraw.setPenColor(StdDraw.BLUE);

                }
                else {
                    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                }
                StdDraw.filledSquare(cj, ci, 0.5);
            }

        }
        StdDraw.show();
    }
}
