/**
 * The Seq<T> class for CS2030S
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class Seq<T> { // TODO: Change to bounded type parameter
  private T[] seq;

  public Seq(int size) {
    // TODO
  }

  public void set(int index, T item) {
    // TODO
  }

  public T get(int index) {
    // TODO
  }

  public T min() {
    // TODO
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
