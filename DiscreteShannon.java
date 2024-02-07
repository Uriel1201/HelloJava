/*  *****************************************************************************
 *  Name:              Dante1201 (Carlos Uriel García)
 *  Id:                1201
 *  
 *  # THE DISCRETE SHANNON ENTROPY
 *  ******************************
 *  The qualities of this formula "H" play a central role in information theory as 
 *  measure of information, 
 *  choice and uncertainty. 
 *  This measure for example is the H in Boltzmann's famous H Theorem.
 *
 *  This quantity has some interesting properties:
 *  > 1. When we are certain of the outcomes in a set of observations 
 *    does H vanish, otherwise H is positive.
 *  > 2. For a total of m possible results in a set of observations, 
 *    H is maximum when all proportions of this possibilities are equal. 
 *    This is also the most uncertain situation.
 *  > 3. The uncertainty is never increased by experience. 
 *    It will be decreased unless this experience is 
 *    irrelevant (independent), in which case uncertainty is not changed
 *  *************************************************************************** */

public class DiscreteShannon {

  /****************************************************************************/
  public static distBer(double p) {

    if (p > 1 || p < 0) {
      throw new IllegalArgumentException("p must represent a probability measure");
    }
    
    double[] ber = new double[2];
    ber[0] = 1.0 - p;
    ber[1] = 1.0;

    return ber;
  }


  /************************************************/
  public static int sampling(double[] dist) {

    double u = Math.random();
    if (u <= dist[0]) {

      return 0;
    }

    for (int i = 1; i < dist.length; i++) {

      if (u <= dist[i] && u > dist[i - 1]) return i;
    }
  }
  public static int sampling(double[] dist) {

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
}




#----------------------------------------------------
def discreteShannon(array, m):
    if m < 0:
        raise ValueError(f'm must be a cardinal')
    f = np.zeros(m, dtype = int)
    n = len(array)
    for i in range(n):
        if array[i] < 0:
            raise ValueError(f'The entries must be no negative')
        elif array[i] == 0:
            f[0] += 1
        elif array[i] < m:
            f[array[i]] += 1
    h = 0.0
    for i in range(m):
        if f[i] > 0:
            p = 1.0 * f[i] / n
            arg = -1.0 * p * np.log(p) / np.log(2)
            h += arg
    return h


#----------------------------------------------------
def main():
    lz = 50
    x = np.linspace(0, 1, lz, dtype = float)
    y = []
    for i in range(len(x)):
        dist = distBer(x[i])
        a = []
        t = 1000
        for j in range(t):
            a.append(sampling(dist))
        a_array = np.array(a)
        y.append(discreteShannon(a_array, 2))
    y_array = np.array(y)
    plt.figure(figsize = (8, 9))
    sns.set(style = 'darkgrid')
    plt.title(f'{lz} SAMPLES OF SIZE {t}')
    plt.ylabel(f'Estimated Uncertainty')
    plt.xlabel(f'sample with ~ Ber(p)')
    sns.scatterplot(x = x, y = y_array, color = '#088F8F')
    plt.tight_layout()
    plt.show()
  
