package cs2030s.fp;

/**
 * A conditional statement that returns either true of false.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
public interface BooleanCondition<T> {

  abstract boolean test(T obj);
}
