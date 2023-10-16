public class RelativelyPrime {

    // Using my Divisors class from functions week
    import Divisors;

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Divisors.areRelativelyPrime(i, j)) {
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
