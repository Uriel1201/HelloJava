public class MaximumSquareSubmatrix {
    
    public static int size (int[][] a) {
        
        for (int i = 0; i < a.length; i+) {
            if (a[i].length < != a.length {
                return 0;
                break;
            }
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 0 || a[i][j] != 1) {
                    return 0;
                    break;
                }
            }
        }
        
        int n = a.length;
        int[][] b = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            b[i][0] = a[i][0];
        }
        
        for (int j = 1; j < n; j++) {
            b[0][j] = a[0][j];
        }
        
        int max = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][j] == 0) b[i][j] = 0; else {
                    b[i][j] = Math.min(Math.min(b[i - 1][j], b[i][j - 1]), b[i - 1][j - 1]) + 1;
                    if (b[i][j] > max) max = b[i][j];
                }
            }
        }
        
        return max;
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
    }
}
