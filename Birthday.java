/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Birthday {

    public static void main(String[] args) {
       
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int m = n + 2;
        int[] s = new int[m];
        
        for (int i = 0; i < trials; i++) {
            boolean[] birthday = new boolean[n];
            int count = 0;
            
            for (int j = 0; j < n + 1; j++) {
                int p = (int) (Math.random() * n);
                count++;
                if (birthday[p]) break;
                birthday[p] = true;
            }

            s[count]++;
        }
        
        double f = 0.0;
        
        for (int k = 1; k < m; k++) {
            f += 1.0 * s[k] / trials;
            System.out.println(k + "  " + s[k] + "  " + f);
            if (f >= 0.5) break;

        }
    }
}
