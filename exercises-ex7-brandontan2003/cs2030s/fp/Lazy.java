package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  private Lazy(T v) {
    this.value = Maybe.some(v);
    this.producer = null;
  }

  private Lazy(Producer<? extends T> producer) {
    this.producer = producer;
    this.value = Maybe.none();
  }

  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  public T get() {
    T result = this.value.orElseGet(this.producer);
    this.value = Maybe.some(result);
    return result;
  }

  public <S> Lazy<S> map(Transformer<? super T, ? extends S> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()));
  }

  public <S> Lazy<S> flatMap(Transformer<? super T, ? extends Lazy<? extends S>> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()).get());
  }

  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) {
    return Lazy.of(() -> bc.test(this.get()));
  }

  public <S, R> Lazy<R> combine(
      Lazy<? extends S> lazy, Combiner<? super T, ? super S, ? extends R> combiner) {
    return Lazy.of(() -> combiner.combine(this.get(), lazy.get()));
  }

  @Override
  public String toString() {
    return this.value.map(val -> String.valueOf(val)).orElse("?");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Lazy) {
      Lazy<?> lazy = (Lazy) obj;
      return this.get().equals(lazy.get());
    }
    return false;
  }
}
