/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # BAND MATRICES
 *  ***************
 *  Band Matrices are matrices whose non zero entries are restricted to a diagonal 
 *  band. 
 *  They arise in numerical linear algebra.
 *  *************************************************************************** */

public class BandMatrix {

    // A zero (0) for each element whose distance from the main diagonal 
    // is strictly more than width (w), and an asterisk (*) 
    // for each entry that is not.
    
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int w = Integer.parseInt(args[1]);

        if (n >= 0 && w >= 0) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int k = j - i;
                    if (Math.abs(k) > w) {
                        System.out.print("0  ");
                    } else {
                        System.out.print("*  ");
                    }
                }
                System.out.println();
            }

        } else {
            System.out.println("n an w must be no negative.");
        }
    }
}
