class BankService {

  /**
   * The counter that is associated when calling the bank service
   */
  private BankCounter[] counters;

  /**
   * A getter method to retrieve all counters
   *
   */
  public BankCounter[] getCounters() {
    return this.counters;
  }

  /**
   * Retrieves the counter that is available
   */
  public BankCounter findAvailableCounter() {
    for (BankCounter counter : this.counters) {
      if (counter.isAvailable()) {
        return counter;
      }
    }
    return null;
  }

  /**
   * Assign the customer to the respective available counter.
   */
  public void serve(BankCustomer customer, BankCounter counter) {
    counter.serve(customer);
  }

  /**
   * Free up the counter once the customer had been served.
   */
  public void finishService(BankCounter counter) {
    counter.release();
  }

  /**
   * Initialize the bank with counters
   */
  public BankService(int numCounters) {
    this.counters = new BankCounter[numCounters];

    for (int i = 0; i < numCounters; i++) {
      this.counters[i] = new BankCounter(i);
    }
  }
}
