/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Minesweeper {
    
    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]); // Number of rows
        if (m <= 0) {
            throw new IllegalArgumentException("m must be positive'");
        }
        
        int n = Integer.parseInt(args[1]); // Number of columns
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        
        int k = Integer.parseInt(args[2]); // Number of mines
        int t = m * n;
        if (k > t) {
            throw new IllegalArgumentException("The number of mines surpassed the max of " + t);
        }
        
        boolean[] shuffle = new boolean[t];
        for (int h = 0; h < k; h++)
            shuffle[h] = true;
        
        for (int h = 0; h < t; h++) {
            int p = h + (int) (Math.random() * (t - h));
            boolean s = shuffle[p];
            shuffle[p] = shuffle[h];
            shuffle[h] = s;
        }
        
        boolean[][] mines = new boolean[m + 2][n + 2];
        for (int h = 1; h < m + 1; h++) {
            for (int i = 1; i < n + 1; i++) {
                mines[h][i] = shuffle[n * (h - 1) + i - 1];
            }
        }
        
        int[][] count = new int[m + 2][n + 2];
        
        int row = 1;
        while (row < m + 1) {
            int col = 1;
            while (col < n + 1) {
                for (int i = row - 1; i < row + 2; i++)
                    for (int j = col - 1; j < col + 2; j++)
                        if (mines[i][j]) count[row][col]++;
                col++;
            }
            row++;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (mines[i][j]) System.out.print("*  ");
                else System.out.print(count[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
