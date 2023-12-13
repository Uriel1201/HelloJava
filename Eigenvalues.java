import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.factory.DecompositionFactory_DDRM;
import org.ejml.interfaces.decomposition.QRDecomposition;


public class Eigenvalues {

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
    // Returns the inner product of two vectors
    private static double innerProd(double[] a, double[] b) {
        
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors must have the same dimension");
        }

        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        
        return sum;
     }


     /*******************************************************************/
     // Returns a matrix reflected under householder's method
     private static DMatrixRMaj householderPr(double[] column) {
         
         int n = column.length;
         double[] w = new double[n];
         double[] e = new double[n];
         double[] a = new double[n];

         e[0] = 1.0;
  
         for (int i = 0; i < n; i++) {
             a[i] = column[i];
         }
         
         double b = Math.sqrt(innerProd(a, a));
         if (a[0] == 0.0) {
             for (int i = 0; i < n; i++) {
                 w[i] = (b * e[i]) - a[i];
             }
         } else {
             double c = 1.0 * a[0] / Math.abs(a[0]);
             for (int i = 0; i < n; i++) {
                 w[i] = (b * e[i]) + (c * a[i]);
             }
         }

         DMatrixRMaj wW = new DMatrixRMaj(w); 
         double nW = innerProd(w, w);
         DMatrixRMaj id_n = CommonOps_DDRM.identity(n);
         DMatrixRMaj house = new DMatrixRMaj(n, n);

         CommonOps_DDRM.multTransB(-2.0 / nW, wW, wW, house);
         CommonOps_DDR.add(id_n, house, house);

         return house;
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
    public static double[] getRoots(double[] coefficients) {

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

        double[] roots = new double[n];
        for (int i = 0; i < n; i++) {
            roots[i] = companion.get(i, i);
        }

        return roots;
    }


    /*******************************************************************/
    public static void main(String[] args) {
        double[] coef = new double[4];
        coef[0] = 1.0;
        coef[1] = -5.0;
        coef[2] = 0.0;
        coef[3] = 1.0;
        double[] r = getRoots(coef);
        for (int i = 0; i < coef.length; i++)
            System.out.println(r[i]);
    }
}
