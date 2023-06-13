/* *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 **************************************************************************** */

public class AudioCollage {

    public static double[] amplify(double[] a, double alpha) {
        if (alpha < 0.0) alpha = Math.abs(alpha);
        double[] amp = new double[a.length];
        for (int i = 0; i < a.length; i++)
            amp[i] = alpha * a[i];
        return amp;
    }

    public static double[] reverse(double[] a) {
        int n = a.length;
        double[] r = new double[n];
        for (int i = 0; i < n; i++)
            r[i] = a[n - i - 1];
        return r;
    }

    public static double[] merge(double[] a, double[] b) {
        int m = a.length;
        int n = b.length;
        double[] me = new double[m + n];
        int i = 0;
        while (i < m + n) {
            if (i < m) me[i] = a[i];
            else me[i] = b[i - m];
            i++;
        }
        return me;
    }

    public static double[] mix(double[] a, double[] b) {
        int n;
        if (a.length > b.length) n = a.length;
        else n = b.length;
        double[] a1 = new double[n];
        double[] b1 = new double[n];
        for (int i = 0; i < a.length; i++)
            a1[i] = a[i];
        for (int i = 0; i < b.length; i++)
            b1[i] = b[i];
        double[] mx = new double[n];
        for (int i = 0; i < n; i++)
            mx[i] = a1[i] + b1[i];
        return mx;
    }

    public static double[] changeSpeed(double[] a, double alpha) {
        if (alpha < 0.0) alpha = Math.abs(alpha);
        int n = (int) (a.length / alpha);
        double[] ch = new double[n];
        for (int i = 0; i < n; i++) {
            int j = (int) (i * alpha);
            ch[i] = a[j];
        }
        return ch;
    }

    public static void main(String[] args) {
        double[] broken = StdAudio.read("broken.wav");
        double[] chimes = StdAudio.read("chimes.wav");
        double[] naruto = StdAudio.read("naruto.wav");
        double[] piano = StdAudio.read("piano.wav");
        double[] singer = StdAudio.read("singer.wav");
        int brn = 30 * 44100;
        int nrn = 14 * 44100;
        double[] brok = new double[brn];
        double[] naru = new double[nrn];
        for (int i = 0; i < brn; i++)
            brok[i] = broken[i];
        for (int i = 0; i < nrn; i++)
            naru[i] = naruto[i];
        int n = 10 * 44100;
        double[] silence1 = new double[n];
        double[] a1 = merge(chimes, singer);
        double[] a2 = amplify(merge(silence1, a1), 0.7);
        double[] mix1 = mix(naru, a2);
        double[] b1 = changeSpeed(amplify(piano, 0.7), 0.5);
        double[] b2 = reverse(b1);
        int m = 24 * 44100;
        double[] silence2 = new double[m];
        double[] brok2 = merge(silence2, mix(b2, brok));
        double[] collage = mix(mix1, brok2);
        StdAudio.play(collage);
    }
}

