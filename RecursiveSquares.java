/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class RecursiveSquares {

    // Drawing the particular square.
    public static void drawSquare(double x, double y, double length) {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(x, y, length / 2.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x, y, length / 2.0);
    }

    // Drawing the recursive square using drawSquare.
    public static void draw(int n, double x, double y, double length) {
        if (n < 1 || length < 0) return;
        else {
            double ln = length / 2.0;
            draw(n - 1, x - ln, y + ln, ln);
            draw(n - 1, x + ln, y + ln, ln);
            drawSquare(x, y, length);
            draw(n - 1, x - ln, y - ln, ln);
            draw(n - 1, x + ln, y - ln, ln);
        }
    }

    public static void main(String[] args) {
        
        // StdDraw.enableDoubleBuffering();
        int n = Integer.parseInt(args[0]);
        
        // The following are the standard measures,
        // but those can be modified.
        double x = 0.5;
        double y = 0.5;
        double length = 0.5;
        
        // Applying a generalized scale for any x and y
        // StdDraw.setXscale(x - length, x + length);
        // StdDraw.setYscale(y - length, y + length);
        draw(n, x, y, length);
        // StdDraw.show();
    }
}
