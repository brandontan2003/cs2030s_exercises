/**
 * The Transformer interface that can transform a type T
 * to type U.
 *
 * @author XXX
 * @version 2026-2027 Sem 1
 */
interface Transformer<T, U> {

  abstract U transform(T t);
}
