/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */


public class ColorHSB {

    private final int hue, sat, bri;

    public ColorHSB(int h, int s, int b) {

        if (h < 0 || h >= 360) throw new IllegalArgumentException("the range for HUE is 0-359");
        if (s < 0 || s > 100)
            throw new IllegalArgumentException("the range for SATURATION is 0-100");
        if (b < 0 || b > 100)
            throw new IllegalArgumentException("the range for BRIGHTNESS is 0-100");

        hue = h;
        sat = s;
        bri = b;
    }

    public String toString() {

        return "(" + hue + ", " + sat + ", " + bri + ")";
    }

    public boolean isGrayscale() {

        return (sat == 0 || bri == 0);
    }

    public int distanceSquaredTo(ColorHSB that) {

        if (that == null) throw new IllegalArgumentException(
                "there is not an object referenced to the argument's method");

        int hH = hue - that.hue;
        if (hH < 0) hH = -hH;
        int sS = sat - that.sat;
        int bB = bri - that.bri;
        int a1 = hH * hH;
        int a2 = (360 - hH) * (360 - hH);
        int distance = Math.min(a1, a2) + (sS * sS) + (bB * bB);

        return distance;
    }

    public static void main(String[] args) {
        
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB color = new ColorHSB(h, s, b);

        int inf = (360 * 360) + (2 * 100 * 100);
        String c = "";
        ColorHSB bestMatch = new ColorHSB(h, s, b);

        while (!StdIn.isEmpty()) {

            String c2 = StdIn.readString();
            int h2 = StdIn.readInt();
            int s2 = StdIn.readInt();
            int b2 = StdIn.readInt();
            ColorHSB color2 = new ColorHSB(h2, s2, b2);

            int distance = color.distanceSquaredTo(color2);

            if (distance < inf) {

                inf = distance;
                c = c2;
                bestMatch = color2;
            }
        }

        StdOut.println(c + " " + bestMatch);
    }
}
