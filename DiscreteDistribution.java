/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class DiscreteDistribution {
   
    public static void main(String[] args) {
        
        int n = args.length;
        int m = Integer.parseInt(args[0]);
        
        int[] a = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        
        int[] s = new int[n];
        for (int i = 1; i < n; i++) {
            s[i] = s[i - 1] + a[i - 1];
        }
        
        for (int k = 0; k < m; k++) {
            int p = (int) (Math.random() * s[n - 1]);
           
            for (int j = 1; j < n; j++) {
                if (s[j] > p && s[j - 1] <= p)
                    System.out.print(j + "  ");
            }
        }
    }
}
