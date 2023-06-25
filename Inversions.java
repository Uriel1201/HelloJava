
public class Inversions  {
    
    public static long count (int[] a) {
        int n = a.length;
        long c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (a[i] > a[j]) c++;
            }
        }
        return c;
    }
    
    public static int[] generate (int n, long k) {
        int[] a = new int[n];
        int i = 0;
        while  (i < n) {
            long j = count (a);
          if (k == j && a[n - 1] != 0) {
                    break;
                }
            int p = n - 1 - i;
            if (k - j >= p) {
                a[i] = p;
                i++;
            } else {
                for (int h = i; h < n; h++){
                    a[h] = h - i;
                }
                int m = (int) (k - j);
                int[] b = new int[m];
                for (int h = 0; h < m; h++) {
                    b[h] = a[n - m - 1 + h];
                }
                int q = a[n - 1];
                a [n - m - 1] = q;
                for (int h = n - m; h < n; h++) {
                    a [h] = b[m - n + h];
                }
                i++; 
            }
            
    }
        return a;
    }
    
    public static void main (String[] args) {
        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);
        int[] a = generate(n, k);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
