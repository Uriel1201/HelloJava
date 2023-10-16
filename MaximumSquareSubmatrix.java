/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class MaximumSquareSubmatrix {

    public static int size(int[][] a) {

        int except = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length < a.length || a[i].length > a.length) {
                except++;
                break;
            }
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] > 1 || a[i][j] < 0) {
                    except++;
                    break;
                }
            }
        }

        if (except > 0) return 0;
        else {

            int n = a.length;
            int[][] b = new int[n][n];

            int max = 0;

            for (int i = 0; i < n; i++) {
                b[i][0] = a[i][0];
                if (b[i][0] > max) max = b[i][0];
            }

            for (int j = 1; j < n; j++) {
                b[0][j] = a[0][j];
                if (b[0][j] > max) max = b[0][j];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (a[i][j] == 0) b[i][j] = 0;
                    else {
                        b[i][j] = Math.min(Math.min(b[i - 1][j], b[i][j - 1]), b[i - 1][j - 1]) + 1;
                        if (b[i][j] > max) max = b[i][j];
                    }
                }
            }

            return max;
        }
    }

    public static void main(String[] args) {

        int n = StdIn.readInt();
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readInt();
            }
        }

        StdOut.print(size(a));

        /*
        int[][] a = {
                { 0, 1, 0, 0, 0 },
                { 0, 1, 1, 1, 1 },
                { 0, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1 }
        };
        System.out.println(size(a)); */
    }
}
