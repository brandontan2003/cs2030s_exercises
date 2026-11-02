/**
 * A generic box storing an item.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class Box<T> {

  private final T value;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  public Box(T value) {
    this.value = value;
  }

  public static <T> Box<T> of(T value) {
    return value == null ? null : new Box<T>(value);
  }

  @SuppressWarnings({"unchecked"})
  public static <T> Box<T> empty() {
    // The only way we can put an object into the box is through the constructor and
    // factory methods, which strictly accept type T
    // Since the empty box instance contains null, and its type parameter is earsed,
    // it is safe to cast Box<Object> to Box<T>
    return (Box<T>) EMPTY_BOX;
  }

  public boolean isPresent() {
    return this.value != null;
  }

  public static <T> Box<T> ofNullable(T value) {
    if (value == null) {
      return Box.empty();
    }
    return Box.of(value);
  }

  public <U> Box<U> map(Transformer<T, U> transformer) {
    if (this.value == null) {
      return Box.empty();
    }
    U value = transformer.transform(this.value);
    if (value == null) {
      return Box.empty();
    }
    return Box.ofNullable(value);
  }

  public Box<T> filter(BooleanCondition<? super T> bc) {
    if (this.value == null || !bc.test(this.value)) {
      return Box.empty();
    }
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj instanceof Box) {
      Box<?> box = (Box) obj;
      // if both value are not null, compare using .equals() method.
      if (!(box.value == null || this.value == null)) {
        return this.value.equals(box.value);
      }
      // otherwise, compare using the == operator.
      return this.value == box.value;
    }
    return false;
  }

  public boolean equals(Box<T> box) {
    return this.value == box.value;
  }

  @Override
  public String toString() {
    return this.isPresent() ? String.format("[%s]", value) : String.format("[]");
  }
}
