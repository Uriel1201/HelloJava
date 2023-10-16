/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class Huntingtons {

    public static int maxRepeats(String dna) {

        int n = dna.length();
        int max = 0;
        int count = 0;
        int i = 0;

        while (i < n - 2) {
            String s = dna.substring(i, i + 3);
            if (s.equals("CAG")) {
                count++;
                i += 3;
                if (count > max) max = count;
            }
            else {
                count = 0;
                i++;
            }
        }
        return max;
    }

    public static String removeWhitespace(String s) {

        String a = s.replace(" ", "");
        String b = a.replace("\n", "");
        String c = b.replace("\t", "");
        return c;
    }

    public static String diagnose(int maxRepeats) {

        if (maxRepeats < 10 || maxRepeats > 180) return "not human";
        else if (maxRepeats < 36) return "normal";
        else if (maxRepeats < 40) return "high risk";
        else return "Huntington's";
    }

    public static void main(String[] args) {

        In input0 = new In(args[0]);
        String s = input0.readAll();
        String dna = removeWhitespace(s);
        int m = maxRepeats(dna);
        StdOut.println("max repeats = " + m);
        StdOut.println(diagnose(m));
    }
}
