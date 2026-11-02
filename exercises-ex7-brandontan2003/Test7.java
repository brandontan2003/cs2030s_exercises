import cs2030s.fp.InfiniteList;
import cs2030s.fp.Transformer;
import java.util.List;

/**
 * Test 7 for CS2030S Lab 7 (AY25/26 Sem 1).  Tests
 * for InfiniteList flatMap().
 *
 * @author Adi Yoga Sidi Prabawa
 */
class Test7 {
  /**
   * Main method for Test7.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {
    InfiniteList<Integer> ones = InfiniteList.iterate(0, x -> x + 1);
    InfiniteList<Integer> reps =
        ones.limit(4).flatMap(x -> InfiniteList.generate(() -> x).limit(x));
    System.out.println(reps.toList().equals(List.of(1, 2, 2, 3, 3, 3)));
    System.out.println(reps.count() == 6L);

    InfiniteList<Integer> tens = InfiniteList.iterate(0, x -> x + 10);
    InfiniteList<Integer> prod = ones.limit(3).flatMap(x1 -> tens.limit(4).map(x2 -> x1 + x2));
    System.out.println(prod.toList().equals(List.of(0, 10, 20, 30, 1, 11, 21, 31, 2, 12, 22, 32)));
    System.out.println(prod.count() == 12L);

    InfiniteList<Integer> twos = InfiniteList.generate(() -> 2);
    Transformer<Integer, InfiniteList<Integer>> f =
        val -> InfiniteList.generate(() -> val).limit(1);
    Transformer<Integer, InfiniteList<Integer>> g = val -> InfiniteList.generate(() -> val);

    InfiniteList<Integer> lazy1 = twos.limit(1).flatMap(f).flatMap(g).limit(1);
    System.out.println(lazy1.toList().equals(List.of(2)));
    System.out.println(lazy1.count() == 1L);

    InfiniteList<Integer> lazy2 = twos.limit(1).flatMap(x -> f.transform(x).flatMap(g)).limit(1);
    System.out.println(lazy2.toList().equals(List.of(2)));
    System.out.println(lazy2.count() == 1L);

    InfiniteList<Integer> lazy3 =
        InfiniteList.generate(() -> 2)
            .limit(1)
            .flatMap(
                y ->
                    InfiniteList.generate(() -> 2)
                        .flatMap(x -> InfiniteList.generate(() -> 2).limit(1)))
            .limit(1);
    System.out.println(lazy3.toList().equals(List.of(2)));
    System.out.println(lazy3.count() == 1L);

    InfiniteList<Integer> lazy4 =
        InfiniteList.generate(() -> 2)
            .limit(1)
            .flatMap(
                y ->
                    InfiniteList.generate(() -> 2)
                        .limit(1)
                        .flatMap(x -> InfiniteList.generate(() -> 2)))
            .limit(1);
    System.out.println(lazy4.toList().equals(List.of(2)));
    System.out.println(lazy4.count() == 1L);

    InfiniteList<Double> lazy5 =
        InfiniteList.iterate(0.0, x -> 1 / (1 - x))
            .flatMap(y -> InfiniteList.generate(() -> y).limit(2))
            .limit(4);
    System.out.println(lazy5.toList().equals(List.of(0.0, 0.0, 1.0, 1.0)));

    InfiniteList<Integer> lazy6 =
        InfiniteList.iterate(0, x -> x + 1)
            .flatMap(y -> InfiniteList.generate(() -> 1 / (2 - y)).limit(2))
            .limit(4);
    System.out.println(lazy6.toList().equals(List.of(0, 0, 1, 1)));

    InfiniteList<Integer> lazy7 =
        InfiniteList.iterate(0, x -> 1 / (1 - x))
            .flatMap(y -> InfiniteList.generate(() -> y).limit(2))
            .limit(4);
    System.out.println(lazy7.toList().equals(List.of(0, 0, 1, 1)));
    System.out.println(lazy7.count() == 4L);
  }
}
