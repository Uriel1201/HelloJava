import org.ejml.data.Complex_F64;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.factory.DecompositionFactory_DDRM;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.interfaces.decomposition.QRDecomposition_F64;
  
public class PolynomialRoots {

  /*******************************************************************/
  private static DMatrixRMaj getCompanionM(double[] coefficients) {

    int n = coefficients.length - 1;
    DMatrixRmaj comp = new DMatrixRmaj(n, n);
    double monic = coefficients[n];
    
    for (int i = 0; i < n; i++) {
      comp.set(i, n - 1, -1.0 * coefficients[i] / monic);
    }
    for (int i = 0; i < n; i++) {
      comp.set(i, i - 1, 1.0);
    }

    return comp;
  }


  /*******************************************************************/
  private static double innerProd(double[] a, double[] b) {

    if (a.length < b.length || a.length > b.length) {
      throw new IllegalArgumentException("Vectors must have the same dimension");
    }

    double sum = 0.0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i] * b[i];
    }

    return sum;
  }


  /*******************************************************************/
  private static DMatrixRMaj HouseholderPr(DMatrixRMaj matrix) {

    int n = matrix.getNumRows();
    double[] w = new double[n];
    double[] e = new double[n];
    double[] a = new double[n];

    e[0] = 1.0;
  
    for (int i = 0; i < n; i++) {
      a[i] = matrix.get(i, 0);
    }
    double b = Math.sqrt(innerProd(a, a));
    if (a[0] == 0.0) {
      for (int i = 0; i < n; i++) {
        w[i] = (b * e[i]) - a[i];
      }
    } else {
      double c = -1.0 * a[0] / Math.abs(a[0]);
      
      for (int i = 0; i < n; i++) {
        w[i] = (b * e[i]) + (c * a[i]);
      }
    }

    DMatrixRMaj wW = new DMatrixRMaj(w); 
    double nW = innerProd(w, w);
    DMatrixRMaj id_n = CommonOps_DDRM.identity(n);
    DMatrixRMaj house= new DMatrixRMaj(n, n);

    CommonOps_DDRM.multTransB(-2.0 / nW, wW, wW, house);
    CommonOps_DDR.add(id_n, house, house);

    return house;
  }
  
  
  /*******************************************************************/
  private static boolean isUpperTriangular(DMatrixRmaj matrix, double error) {

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

    DMatrixRmaj companion = getCompanionM(coefficients);
    int n = coefficients.length - 1;

    int maxIterations = 10000;
    double eps = 1.0E-10;

    for (int i = 0; i < maxIterations; i++) {
      
      QRDecomposition_F64<DMatrixRmaj> qr = DecompositionFactory_DDRM.qrp(n, n);
      qr.decompose(companion);
      DMatrixRmaj q = qr.getQ(null, false);
      DMatrixRmaj r = qr.getR(null, false);
      companion = CommonOps_DDRM.mult(r, q, companion);

      if (isUpperTriangular(companion, eps)) {
        break;
      }
    }

    Complex_F64[] roots = new Complex_F64[n];
    for (int i = 0; i < n; i++) {
      roots[i] = companion.get(i, i);
    }

    return roots;
  }

  
}
