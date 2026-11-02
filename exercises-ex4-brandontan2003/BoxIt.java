/**
 * Takes an item and return the item in a box.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T obj) {
    return Box.ofNullable(obj);
  }
}
