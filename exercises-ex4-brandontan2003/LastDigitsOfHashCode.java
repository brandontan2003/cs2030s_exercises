/**
 * A transformer with a parameter k that takes in an object x
 * and outputs the last k digits of the hash value of x.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class LastDigitsOfHashCode<T> implements Transformer<T, Integer> {

  private int digits;

  public LastDigitsOfHashCode(int digits) {
    this.digits = digits;
  }

  @Override
  public Integer transform(T obj) {
    int hash = obj.hashCode();
    int modulo = 1;

    for (int i = 0; i < digits; i++) {
      modulo *= 10;
    }

    int result = hash % modulo;
    // Check if result (hash % modulo) is negative, if negative multiply by -1 to make it positive
    // Else, return result
    return (result < 0) ? -1 * result : result;
  }
}
