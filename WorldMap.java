/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class WorldMap {

    public static void main(String[] args) {

        StdDraw.enableDoubleBuffering();
        int xi = StdIn.readInt();
        int yi = StdIn.readInt();
        StdDraw.setCanvasSize(xi, yi);
        StdDraw.setXscale(0, xi);
        StdDraw.setYscale(0, yi);

        while (!StdIn.isEmpty()) {
            StdIn.readString();
            int m = StdIn.readInt();
            double[] x = new double[m];
            double[] y = new double[m];
            for (int i = 0; i < m; i++) {
                x[i] = StdIn.readDouble();
                y[i] = StdIn.readDouble();
            }
            StdDraw.polygon(x, y);
        }
        StdDraw.show();
    }
}
