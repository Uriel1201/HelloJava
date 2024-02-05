/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # THE MINESWEEPER GAME
 *  **********************
 *  Minesweeper is single-player logic-based computer game played 
 *  on rectangular board whose object is to locate 
 *  a predetermined number of randomly-placed "mines" 
 *  in the shortest possible time by clicking on "safe" squares 
 *  while avoiding the squares with mines.
 *
 *  The goal is to deduce which cells contain hidden mines using clues about 
 *  the number of mines in neighboring cells.
 *  *************************************************************************** */

public class Minesweeper {

    // > 1. Generate an m-by-n grid of cells, 
    //      with exactly k of the mn cells containing mines, 
    //      uniformly at random.
    // > 2. For each cell not containing a mine, count the number of neighboring mines 
    //      (above, below, left, right, or diagonal).
    // > 3. '*' for the cells containing a mine.
    
    public static void main(String[] args) {
        
        int m = Integer.parseInt(args[0]); // Number of Rows 
        if (m <= 0) {
            throw new IllegalArgumentException("m must be positive");
        }
        
        int n = Integer.parseInt(args[1]); // Number of Columns 
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        
        int k = Integer.parseInt(args[2]); // Number of Mines 
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        
        int t = m * n; // Maximum Capacity 
        if (k > t) {
            throw new IllegalArgumentException("the number of mines surpassed the max capacity");
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
