package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  private static final InfiniteList<?> SENTINEL = new Sentinel<>();

  private InfiniteList() {
    this.head = null;
    this.tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(
        Lazy.of(() -> Maybe.some(producer.produce())),
        Lazy.of(() -> InfiniteList.generate(producer)));
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(
        Lazy.of(Maybe.some(seed)), Lazy.of(() -> InfiniteList.iterate(next.transform(seed), next)));
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail.produce());
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(
        Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail().map(mapper)));
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(
        Lazy.of(() -> this.head.get().filter(predicate)),
        Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    // The only way we can put an object into the Infinite is through the constructor and
    // factory methods, which strictly accept type T
    // Since the empty box instance contains null, and its type parameter is earsed,
    // it is safe to cast InfiniteList<Object> to Infinite<T>
    InfiniteList<T> sent = (InfiniteList<T>) SENTINEL;
    return sent;
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return sentinel();
    }
    return new InfiniteList<>(
        this.head,
        Lazy.of(() -> this.tail.get().limit(this.head.get().map(x -> n - 1).orElseGet(() -> n))));
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> filterHead = Lazy.of(() -> this.head.get().filter(predicate));
    return new InfiniteList<>(
        filterHead,
        Lazy.of(
            () ->
                this.head
                    .get()
                    .map(
                        x ->
                            filterHead
                                .get()
                                .map(y -> this.tail.get().takeWhile(predicate))
                                .orElseGet(() -> InfiniteList.<T>sentinel()))
                    .orElseGet(() -> this.tail.get().takeWhile(predicate))));
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    U result = identity;
    InfiniteList<T> curr = this;
    while (!curr.isSentinel()) {
      final U acc = result;
      result = curr.head.get().map(x -> accumulator.combine(acc, x)).orElseGet(() -> acc);
      curr = curr.tail.get();
    }
    return result;
  }

  public long count() {
    long count = 0;
    InfiniteList<T> curr = this;
    while (!curr.isSentinel()) {
      count += curr.head.get().map(x -> 1L).orElseGet(() -> 0L);
      curr = curr.tail.get();
    }
    return count;
  }

  public List<T> toList() {
    List<T> result = new ArrayList<>();
    InfiniteList<T> curr = this;

    while (!curr.isSentinel()) {
      curr.head.get().ifPresent(x -> result.add(x));
      curr = curr.tail.get();
    }
    return result;
  }

  public InfiniteList<T> append(Producer<InfiniteList<T>> other) {
    return new InfiniteList<>(this.head, Lazy.of(() -> this.tail.get().append(other)));
  }

  public <R> InfiniteList<R> flatMap(Transformer<? super T, ? extends InfiniteList<R>> mapper) {
    return this.head
        .get()
        .map(x -> mapper.transform(x).append(() -> this.tail.get().flatMap(mapper)))
        .orElseGet(() -> this.tail.get().flatMap(mapper));
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  public static class Sentinel<T> extends InfiniteList<T> {
    @Override
    public T head() {
      throw new NoSuchElementException();
    }

    @Override
    public InfiniteList<T> tail() {
      return sentinel();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
      return sentinel();
    }

    @Override
    public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
      return sentinel();
    }

    @Override
    public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
      return sentinel();
    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public InfiniteList<T> limit(long n) {
      return sentinel();
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public List<T> toList() {
      return List.of();
    }

    @Override
    public InfiniteList<T> append(Producer<InfiniteList<T>> other) {
      return other.produce();
    }

    @Override
    public <R> InfiniteList<R> flatMap(Transformer<? super T, ? extends InfiniteList<R>> mapper) {
      return sentinel();
    }

    @Override
    public String toString() {
      return "-";
    }
  }
}
