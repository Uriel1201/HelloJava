/*****************************************
 * Writing a class that computes methods
 * related with the Binomial Distribution.
 *****************************************/

import java.awt.Color;
import java.awt.Font;
import java.math.BigInteger;
import java.util.ArrayList;

public class BinomialDist {

    private final int trials;
    private final double param;
    private final double[] dist;

    /************************************************/
    public BinomialDist(int n, double p) {

        if (n < 0) {
            throw new IllegalArgumentException("Doesn't exist samples with negative size");
        }
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException("p must to be a measure of probability");
        }
        trials = n;
        param = p;
        dist = new double[n + 1];

        BigInteger[][] triangle = pascal(n);

        for (int x = 0; x < n + 1; x++) {

            int i = 0;
            double p2 = 1;
            while (i < x) {
                p2 = p2 * p;
                i++;
            }

            int y = n - x;
            int j = 0;
            double q = 1 - p;
            double q2 = 1;
            while (j < y) {
                q2 = q2 * q;
                j++;
            }
            double comb = triangle[n][x + 1].doubleValue();
            double w = p2 * q2 * comb;
            if (w < 1.0E-7) {
                w = 0.0;
            }
            if (x == 0) {
                dist[x] = w;
            }
            else {
                dist[x] = dist[x - 1] + w;
            }
            if (dist[x] > 99999E-5) {
                dist[x] = 1.0;
            }
        }
    }
    

    /************************************************/
    public double getProbability(int x) {

        // x: number of successes '0<= x <=n'
        if (x == 0) {
            return dist[x];
        }
        else {
            return dist[x] - dist[x - 1];
        }
    }

    /************************************************/
    private void chart(String title, String yLabel, String parameters, ArrayList<Double> values) {

        StdDraw.setTitle("Binomial Distribution");

        Color cTitle = Color.decode("#000000");
        Color cyLabel = Color.decode("#06400a");
        Color cParam = Color.decode("#ae0001");

        // Set the scale
        double yMax = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (v > yMax) yMax = v;
        }
        StdDraw.setXscale(-0.1 * trials, 1.2 * trials);
        StdDraw.setYscale(-0.1 * yMax, 1.25 * yMax);

        // Draw title
        StdDraw.setPenColor(cTitle);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * trials, 1.2 * yMax, title);

        // Draw y-axis label
        StdDraw.setPenColor(cyLabel);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
        StdDraw.textLeft(0.0, 1.08 * yMax, yLabel);

        // Draw parameters
        StdDraw.setPenColor(cParam);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
        StdDraw.textRight(1.14 * trials, 1.08 * yMax, parameters);

        // Draw axes
        StdDraw.setPenColor(cyLabel);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.textLeft(0.0, yMax, Double.toString(yMax));
    } 

    /************************************************/
    public void plotMass() {

        StdDraw.enableDoubleBuffering();

        String title = "Probability Mass Function";
        String yLabel = "Max_Mass_Value:";
        String pToS = String.valueOf(param);
        String tToS = String.valueOf(trials);
        String parameters = "trials:  " + tToS + ",   p:  " + pToS;

        ArrayList<Double> values = new ArrayList<Double>();
        for (int i = 0; i < trials + 1; i++) {
            double mass = getProbability(i);
            values.add(mass);
        }

        chart(title, yLabel, parameters, values);
        ArrayList<Double> success = new ArrayList<Double>();
        for (int i = 0; i < trials + 1; i++) {
            double toD = i;
            success.add(toD);
        }

        Color points = Color.decode("#ae0001");
        Color lines = Color.decode("#d3a625");

        for (int i = 0; i < values.size(); i++) {

            StdDraw.setPenColor(points);
            StdDraw.setPenRadius(0.015);
            StdDraw.point(success.get(i), values.get(i));
            StdDraw.setPenColor(lines);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(success.get(i), 0.0, success.get(i), values.get(i));
        }

        StdDraw.show();
        StdDraw.clear();
    } 

    /************************************************/
    public double getDistribution(int x) {

        return dist[x];
    }

    /************************************************/
    public int sampling() {

        double u = Math.random();
        if (u <= dist[0]) {
            return 0;
        }
        for (int i = 1; i < trials; i++) {
            if (u > dist[i - 1] && u <= dist[i]) {
                return i;
            }
        }
        return trials;
    }

    /************************************************/
    public void plotDist() {

        StdDraw.enableDoubleBuffering();
        String title = "Distribution Function";
        String yLabel = "Max_Value:";
        String pToS = String.valueOf(param);
        String tToS = String.valueOf(trials);
        String parameters = "trials:  " + tToS + ",   p:  " + pToS;

        ArrayList<Double> values = new ArrayList<Double>();
        for (int i = 0; i < trials + 1; i++) {
            double d = getDistribution(i);
            values.add(d);
        }

        chart(title, yLabel, parameters, values);
        ArrayList<Double> success = new ArrayList<Double>();
        for (int i = 0; i < trials + 1; i++) {
            double itoD = i;
            success.add(itoD);
        }

        Color points = Color.decode("#ae0001");
        Color lines = Color.decode("#d3a625");

        for (int i = 0; i < values.size(); i++) {

            StdDraw.setPenColor(points);
            StdDraw.setPenRadius(0.015);
            StdDraw.point(success.get(i), values.get(i));
            StdDraw.setPenColor(lines);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(success.get(i), 0.0, success.get(i), values.get(i));
        }

        StdDraw.show();
        StdDraw.clear();
    } 

    /************************************************/
    public int getTrials() {
        return trials;
    }

    /************************************************/
    public double getParam() {
        return param;
    }

    /************************************************/
    public double getMean() {
        
        return trials * param;
    }

    /************************************************/
    public double getVariance() {

        return getMean() * (1 - param);
    }


    /************************************************/
    private static BigInteger[][] pascal(int n) {

        BigInteger[][] triangle = new BigInteger[n + 1][n + 2];

        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < n + 2; j++)
                triangle[i][j] = BigInteger.ZERO;

        triangle[0][1] = BigInteger.ONE;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 2; j++) {
                triangle[i][j] = triangle[i - 1][j - 1].add(triangle[i - 1][j]);
            }
        }

        return triangle;
    }

    /************************************************/
    private static double cdf(double z) {

        if (z < -8.0) return 0.0;
        if (z > 8.0) return 1.0;
        
        double g = Math.exp(-z * z / 2) / Math.sqrt(2 * Math.PI);
        
        double sum = 0.0;
        double t = z;
        
        for (int i = 3; sum + t != sum; i += 2) {
            sum = sum + t;
            t = t * z * z / i;
        }
        return 0.5 + sum * g;
    }

    /************************************************/
    public static double getNormalApprox(int n, int x, double p) {

        double mean = n * p;
        double sigma = Math.sqrt(n * p * (1 - p));
        return cdf((x + 0.5 - mean) / sigma);
    }


    /************************************************/
    public static int getMax(int[] sample) {

        int n = sample.length;
        for (int i = 0; i < n; i++) {
            if (sample[i] < 0) {
                throw new IllegalArgumentException("this sample array must content no negative values");
            }
        }

        int max = sample[0];
        for (int i = 1; i < n; i++) {
            if (sample[i] > max) {
                max = sample[i];
            }
        }

        return max;
    }

    
    /************************************************/
    public static double getMLParam(int[] sample, int n) {

        for (int i = 0; i < sample.length; i++) {
            if (sample[i] < 0) {
                throw new IllegalArgumentException("this sample array must content no negative values");
            }
        }
        
        if (n <= 0) {
            throw new IllegalArgumentException("n must represent the number of trials on a Binomial distribution");
        }
        
        int sum = 0;
        for (int i = 0; i < sample.length; i++) {
            sum += sample[i];
        }

        double p = 1.0 * sum / (n * sample.length);
        return p;
    }


    /************************************************/
    public static int getMLTrials(int[] sample, double p) {

        for (int i = 0; i < sample.length; i++) {
            if (sample[i] < 0) {
                throw new IllegalArgumentException("this sample array must content no negative values");
            }
        }
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException("p must represent a measure of probability");
        }

        int n = sample.length;
        int sMax = getMax(sample);
        ArrayList<Polynomial> b = new ArrayList<Polynomial>();

        for (int i = 0; i < n; i++) {
            double s = sample[i];
            Polynomial binomial = new Polynomial(1.0, 0).plus(new Polynomial(-1.0 * s, 1));
            b.add(binomial);
        }

        Polynomial poly = b.get(0);
        for (int i = 1; i < b.size(); i++) {
            poly = poly.times(b.get(i));
        }

        double q = 1 - p;
        int j = 0;
        double c = 1;
        while (j < n) {
            c = c * q;
            j++;
        }

        Polynomial cx = new Polynomial(-1.0 * c, 0);
        poly = poly.plus(cx);
        double[] roots = poly.getRealRoots();
        
        double opZ = 0.0;
        
        for (int i = 0; i < roots.length; i++) {
            
            opZ = 1.0 / roots[i];
            
            if (opZ >= sMax) {
                break;
            }
        }

        return Math.floor(opZ);
    }


    /************************************************/
    public static void main(String[] args) {

        // number of trials
        int n = Integer.parseInt(args[0]);
        // number of successes
        int x = Integer.parseInt(args[1]);
        // success probability
        double p = Double.parseDouble(args[2]);
        // size of the sample
        int m = Integer.parseInt(args[3]);

        BinomialDist np = new BinomialDist(n, p);

        System.out.println("Mass of x = " + x + ": " + np.getProbability(x));
        System.out.println("Distribution of x = " + x + ": " + np.getDistribution(x));

        double cum = np.getDistribution(n);
        System.out.println("The cumulative to n: " + cum);
        
        np.plotMass();
        StdDraw.save("Mass_Plot.jpg");
        np.plotDist();
        StdDraw.save("Distribution_Plot.jpg");

        // When the number of trials is very huge a good idea is to implement an approximation using the Normal
        if (x > 0) {
            double pASup = getNormalApprox(n, x, p);
            double pAInf = getNormalApprox(n, x - 1, p);
            double mA = pASup - pAInf;
            System.out.println("Approximated Distribution in x using Normalization: " + pASup);
            System.out.println("Approximated mass in x using Normalization: " + mA);
        } else {
            double pASup = getNormalApprox(n, 0, p);
            System.out.println("Approximated Distribution in x using Normalization: " + pASup);
            System.out.println("Approximated mass in x using Normalization: " + pASup);
        }
        System.out.println("Mean: " + np.getMean());
        System.out.println("Variance: " + np.getVariance());

        // simulating a sample for this binomial distribution 
        int[] sample = new int[m];
        for (int i = 0; i < m; i++) {
            sample[i] = np.sampling();
        }
        
        System.out.println("Suppose for a moment you know the trials but you don't know the success probability");
        double mlP = getMLParam(sample, n);
        System.out.println("MLE for the parameter p: " + mlP);
        System.out.println("Estimated Mean: " + n * mlP);
        System.out.println("Estimated Variance: " + n * mlP *(1 - mlP));

        System.out.println("Suppose for a moment you know the success probability but you don't know the trials");
        int mlTrials = getMLTrials(sample, p);
        System.out.println("MLE for the parameter trials: " + mlTrials);
    }
}
