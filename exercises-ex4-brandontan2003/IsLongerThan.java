/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
class IsLongerThan implements BooleanCondition<String> {

  private int limit;

  public IsLongerThan(int limit) {
    this.limit = limit;
  }

  public boolean test(String value) {
    return (value.length() > limit) ? true : false;
  }
}
