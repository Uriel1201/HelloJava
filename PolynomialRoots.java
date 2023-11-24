public class PolynomialRoots {

  private static DMatrixRmaj getCompanionM(double[] coefficients) {

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

  public static Complex_F64[] getRoots(double[] coefficients) {

    DMatrixRmaj companion = getCompanionM(coefficients);
    int n = coefficients.length - 1;

    int maxIterations = 10000;
    double eps = 1.0E-10;

    for (int i = 0; i < maxIterations; i++) {
      
      QRDecomposition<DMatrixRmaj> qr = DecompositionFactory_DDRM.qr(n, n);
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
