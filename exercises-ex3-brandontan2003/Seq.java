/**
 * The Seq<T> class for CS2030S
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class Seq<T extends Comparable<T>> {
  private T[] seq;

  public Seq(int size) {
    // The only way to put an object into an array is through the method set()
    // and we only put object of type T inside.
    // So it is safe to cast Object[] to T[].
    @SuppressWarnings({"unchecked"})
    T[] array = (T[]) new Comparable<?>[size];
    this.seq = array;
  }

  public void set(int index, T item) {
    this.seq[index] = item;
  }

  public T get(int index) {
    return this.seq[index];
  }

  public T min() {
    T min = null;
    for (T item : seq) {
      if (min == null || item.compareTo(min) < 0) {
        min = item;
      }
    }
    return min;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.seq.length; i++) {
      s.append(i + ":" + this.seq[i]);
      if (i != this.seq.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
