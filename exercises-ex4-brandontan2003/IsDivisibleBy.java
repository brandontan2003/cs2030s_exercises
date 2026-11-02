/**
 * A boolean condition with an integer parameter y that can be
 * apply to another integer x.  Returns true if x is divisible
 * by y, false otherwise.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class IsDivisibleBy implements BooleanCondition<Integer> {

  private int divisor;

  public IsDivisibleBy(int divisor) {
    this.divisor = divisor;
  }

  public boolean test(Integer value) {
    return (value % divisor == 0) ? true : false;
  }
}
