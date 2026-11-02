package cs2030s.fp;

/**
 * Represent a box with possibly missing value.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
import java.util.NoSuchElementException;

public abstract sealed class Maybe<T> permits Maybe.Some, Maybe.None {

  private final T value;

  private static final None MAYBE_NONE = new None();

  public static final <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    // The only way we can put an object into the Maybe is through the constructor and
    // factory methods, which strictly accept type T
    // Since the empty box instance contains null, and its type parameter is earsed,
    // it is safe to cast Maybe<Object> to Maybe<T>
    Maybe<T> mb = (Maybe<T>) MAYBE_NONE;
    return mb;
  }

  public static final <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  private Maybe(T t) {
    this.value = t;
  }

  public static <T> Maybe<T> of(T value) {
    if (value == null) {
      return none();
    }
    return some(value);
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> bc);

  public abstract <S> Maybe<S> map(Transformer<? super T, ? extends S> transformer);

  public abstract <S> Maybe<S> flatMap(
      Transformer<? super T, ? extends Maybe<? extends S>> transformer);

  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void ifPresent(Consumer<? super T> consumer);

  @Override
  public String toString() {
    return String.format("[%s]", this.value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Some && !(this instanceof None)) {
      Maybe<?> s = (Maybe) obj;
      if (!(s.value == null || this.value == null)) {
        return this.value.equals(s.value);
      }
      return this.value == s.value;
    }
    return obj instanceof None && this instanceof None;
  }

  private static final class Some<T> extends Maybe<T> {

    private Some(T t) {
      super(t);
    }

    @Override
    protected T get() {
      Maybe<T> m = this;
      return m.value;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> bc) {
      if (!(this.get() == null) && !bc.test(this.get())) {
        return super.none();
      }
      return this;
    }

    @Override
    public <S> Maybe<S> map(Transformer<? super T, ? extends S> transformer) {
      return Maybe.some(transformer.transform(this.get()));
    }

    @Override
    public <S> Maybe<S> flatMap(Transformer<? super T, ? extends Maybe<? extends S>> transformer) {
      Maybe<? extends S> result = transformer.transform(this.get());
      return result.<S>map(res -> res);
    }

    @Override
    public T orElse(T value) {
      return this.get();
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.get();
    }

    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(this.get());
    }

    // This is not needed in this scenario, added String representation for future implementation
    @Override
    public String toString() {
      return String.format("%s", super.toString());
    }
  }

  private static final class None extends Maybe<Object> {
    private None() {
      super(null);
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> bc) {
      return Maybe.none();
    }

    @Override
    public <S> Maybe<S> map(Transformer<? super Object, ? extends S> transformer) {
      return Maybe.none();
    }

    @Override
    public <S> Maybe<S> flatMap(
        Transformer<? super Object, ? extends Maybe<? extends S>> transformer) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object value) {
      return value;
    }

    @Override
    public Object orElseGet(Producer<?> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> consumer) {
      // do nothing
    }

    @Override
    public String toString() {
      return "[]";
    }
  }
}
