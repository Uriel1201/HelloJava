import org.ejml.data.DMatrixRMaj;
import org.ejml.data.Complex_F64;
import org.ejml.dense.row.factory.DecompositionFactory_DDRM;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.interfaces.decomposition.QRDecomposition;

/******************************************************************
* This class is intended to define a polynomial as an object for 
* applying the fundamental arithmetic operations on it and 
* determining its root solutions using the QR_Algorithm, which is
* utilized for finding eigenvalues of a matrix.
*******************************************************************/

public class Polynomialrts {

    private double[] coef;
    private int degree;
    
    /*******************************************************************/
    public Polynomialrts(double a, int b) {

        if (b < 0) {
            throw new IllegalArgumentException("Exponents must be no negative");
        }

        coef = new double[b + 1];
        coef[b] = a;
        reduce();
    }


    /*******************************************************************/
    private void reduce() {

        degree = -1;
        for (int i = coef.length - 1; i >= 0; i--) {
            if (coef[i] != 0) {
                degree = i;
                return;
            }
        }
    }
    

    /*******************************************************************/
    public Polynomialrts plus(Polynomialrts that) {

        Polynomialrts p = new Polynomialrts(0.0, Math.max(degree, that.degree));

        for (int i = 0; i <= degree; i++) {
            p.coef[i] += coef[i];
        }
        for (int i = 0; i <= that.degree; i++) {
            p.coef[i] += that.coef[i];
        }

        p.reduce();
        return p;
    }


    /*******************************************************************/
    public Polynomialrts times(Polynomialrts that) {

        Polynomialrts p = new Polynomialrts(0.0, degree + that.degree);

        for (int i = 0; i < degree + 1; i++) {
            for (int j = 0; j < that.degree + 1; j++) {
                p.coef[i + j] += (coef[i] * that.coef[j]);
            }
        }

        p.reduce();
        return p;
    }


    /*******************************************************************/
    public double[] getCoefficients() {
        
        double[] copy = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            copy[i] = coef[i]
        }

        return copy;
    }


    /*******************************************************************/
    public String toString() {

        if (degree == -1) return "0";
        else if (degree == 0) return "" + coef[0];
        else if (degree == 1) return coef[1] + "x + " + coef[0];
        
        String s = coef[degree] + "x^" + degree;
        
        for (int i = degree - 1; i >= 0; i--) {
            if (coef[i] == 0) continue;
            else if (coef[i] > 0.0) s = s + " + " + coef[i];
            else if (coef[i] < 0.0) s = s + " + " + (-1.0 * coef[i]);
            if (i == 1) s = s + "x";
            else if (i > 1) s = s + "x^" + i;
        }

        return s;
    }


    /*******************************************************************/
    // Returns the Companion Matrix of a polynomial whose coefficients 
    // are ordered from least to most significant
    private static DMatrixRMaj getCompanionM(double[] coefficients) {

        int n = coefficients.length - 1;
        DMatrixRMaj comp = new DMatrixRMaj(n, n);
        double monic = coefficients[n];

        for (int i = 0; i < n; i++) {
            comp.set(i, n - 1, -1.0 * coefficients[i] / monic);
        }
        for (int i = 0; i < n - 1; i++) {
            comp.set(i + 1, i, 1.0);
        }

        return comp;
    }


    /*******************************************************************/
    // Is this matrix an upper triangular matrix 
    private static boolean isUpperTriangular(DMatrixRMaj matrix, double error) {

        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(matrix.get(i, j)) > error) {
                    return false;
                }
            }
        }
        return true;
    }


    /*******************************************************************/
    public static Complex_F64[] getRoots(double[] coefficients) {
        
        if (coefficients == null || coefficients.length < 2 ) {
            throw new IllegalArgumentException("Invalid Coefficients");
        }
        
        DMatrixRMaj companion = getCompanionM(coefficients);
        int n = coefficients.length - 1;

        int maxIterations = 10000;
        double eps = 1.0E-10;

        for (int i = 0; i < maxIterations; i++) {

            QRDecomposition<DMatrixRMaj> qr = DecompositionFactory_DDRM.qr(n, n);
            qr.decompose(companion);
            DMatrixRMaj q = qr.getQ(null, false);
            DMatrixRMaj r = qr.getR(null, false);
            companion = CommonOps_DDRM.mult(r, q, companion);

            if (isUpperTriangular(companion, eps)) {
                break;
            }
        }

        Complex_F64[] roots = new Complex_F64[n];
        for (int i = 0; i < n; i++) {
            roots[i] = new Complex_F64(companion.get(i, i), 0);
        }

        return roots;
    }


    /*******************************************************************/
    public static void main(String[] args) {

        Polynomialrts p1 = new Polynomialrts(1.0, 3);
        Polynomialrts p2 = new Polynomialrts(-4.0, 2);
        Polynomialrts p3 = new Polynomialrts(6.0, 1);
        Polynomialrts p4 = new Polynomialrts(-24.0, 0);
        
        Polynomialrts p = p1.plus(p2).plus(p3).plus(p4);
        System.out.println("p(x) = " + p);
        double[] c = p.getCoefficients();
        Complex_F64[] r = getRoots(c);
        
        for (int i = 0; i < r.length; i++) 
            System.out.println("root " + i + " = " + r[i]);
    }
}
