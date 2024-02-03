/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *
 *  # THUE-MORSE SEQUENCE
 *  *********************
 *  The Thue-Morse Sequence is an infinite sequence of 0s and 1s 
 *  that is constructed by starting with 0 and successively appending 
 *  the bitwise negation of the existing sequence.
 *  *************************************************************************** */

public class ThueMorse {
    
    // For visualizing this sequence, create an n-by-n pattern 
    // printing black for 0 and white for 1.
    
    public static void main(String[] args) {
        
        int n = Integer.parseInt(args[0]);
        if (n >= 0) {
            int[] a = new int[n];
            
            for (int i = 1; i < n; i++) {
                if (i % 2 == 0) a[i] = a[i / 2];
                else a[i] = 1 - a[i - 1];
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i] == a[j])
                        System.out.print("+  ");
                    else System.out.print("-  ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("n must to be no negative");
        }
    }
}
