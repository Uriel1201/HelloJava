/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

import java.awt.Color;

public class KernelFilter {

    private static Picture filter(Picture picture, double[][] weights) {

        int m = weights.length;
        int n = (m - 1) / 2;
        int width = picture.width();
        int height = picture.height();
        Picture f = new Picture(width, height);

        int row = 0;
        while (row < height) {
            int col = 0;
            while (col < width) {
                double r2 = 0.0;
                double g2 = 0.0;
                double b2 = 0.0;
                for (int i = -n; i < n + 1; i++) {
                    for (int j = -n; j < n + 1; j++) {
                        Color color = picture.get((width + j + col) % width,
                                                  (height + i + row) % height);
                        int r = color.getRed();
                        int g = color.getGreen();
                        int b = color.getBlue();
                        double w = weights[i + n][j + n];
                        r2 += r * w;
                        g2 += g * w;
                        b2 += b * w;
                    }
                }

                int rR = (int) Math.round(r2);
                int gG = (int) Math.round(g2);
                int bB = (int) Math.round(b2);
                if (rR > 255) rR = 255;
                if (gG > 255) gG = 255;
                if (bB > 255) bB = 255;
                if (rR < 0) rR = 0;
                if (gG < 0) gG = 0;
                if (bB < 0) bB = 0;
                Color newcolor = new Color(rR, gG, bB);
                f.set(col, row, newcolor);
                col++;
            }
            row++;
        }
        return f;
    }

    public static Picture identity(Picture picture) {

        double[][] id = new double[3][3];
        id[1][1] = 1.0;

        return filter(picture, id);
    }

    public static Picture gaussian(Picture picture) {

        double g = 1.0 / 16.0;
        double[][] gauss = {
                { g, 2.0 * g, g },
                { 2.0 * g, 4.0 * g, 2.0 * g },
                { g, 2.0 * g, g }
        };

        return filter(picture, gauss);
    }

    public static Picture sharpen(Picture picture) {

        double[][] sharp = {
                { 0.0, -1.0, 0.0 },
                { -1.0, 5.0, -1.0 },
                { 0.0, -1.0, 0.0 }
        };

        return filter(picture, sharp);
    }

    public static Picture laplacian(Picture picture) {

        double[][] lap = {
                { -1.0, -1.0, -1.0 },
                { -1.0, 8.0, -1.0 },
                { -1.0, -1.0, -1.0 }
        };

        return filter(picture, lap);
    }

    public static Picture emboss(Picture picture) {

        double[][] emb = {
                { -2.0, -1.0, 0.0 },
                { -1.0, 1.0, 1.0 },
                { 0.0, 1.0, 2.0 }
        };

        return filter(picture, emb);
    }

    public static Picture motionBlur(Picture picture) {

        double[][] motion = new double[9][9];
        for (int i = 0; i < 9; i++)
            motion[i][i] = 1.0 / 9.0;

        return filter(picture, motion);
    }

    public static void main(String[] args) {

        Picture picture = new Picture(args[0]);

        Picture f1 = identity(picture);
        Picture f2 = gaussian(picture);
        Picture f3 = sharpen(picture);
        Picture f4 = laplacian(picture);
        Picture f5 = emboss(picture);
        Picture f6 = motionBlur(picture);

        f1.show();
        f2.show();
        f3.show();
        f4.show();
        f5.show();
        f6.show();
    }
}
