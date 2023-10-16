public class Checksums {

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int sum = 0;

    for (int i = 2; i < 11; i++) {
      int dig = n % 10;
      sum += dig * i;
      n = n / 10;
    }

    System.out.print("The ISBN for your number is:  " + args[0]);
     
    int r = sum % 11;
    if (r ==0) System.out.print("0");
    else if (r == 1) System.out.print("x");
    else System.out.print(11 - r);
  } 
}
