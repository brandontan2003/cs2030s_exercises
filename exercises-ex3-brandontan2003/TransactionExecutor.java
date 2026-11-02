public interface TransactionExecutor {
  boolean execute(int txnType, int amount);
}
