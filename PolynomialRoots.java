public class PolynomialRoots {

  public DMatrixRmaj getcompanionM(double[] coefficients) {

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

  public static Complex_F64[] getRoots(DMatrixRmaj companion) {

    DMatrixRmaj copy = companion.copy();
    int n = companion.getNumRows();

    QRDecomposition<DMatrixRmaj> qr = DecompositionFactory_DDRM.qr(n, n);
    
  }
}
