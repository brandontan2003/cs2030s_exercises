class Bank {

  /**
   * The counter that is associated when calling the bank service
   */
  private Seq<BankCounter> counters;

  /**
   * The queue used for bank simulation.
   */
  private Queue<BankCustomer> bankQueue;

  /**
   * A getter method to retrieve all counters
   *
   */
  public Seq<BankCounter> getCounters() {
    return this.counters;
  }

  /**
   * Retrieves the counter that is available
   */
  public BankCounter findAvailableCounter() {
    return counters.min();
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
   * Retrieve the customers in the bank waiting list
   */
  @Override
  public String toString() {
    return this.bankQueue.toString();
  }

  /**
   * Check if the Bank Queue is full
   */
  public boolean waitlistFull() {
    return this.bankQueue.isFull();
  }

  /**
   * Retrieve the next customer from the Queue
   */
  public BankCustomer getNextCustomer() {
    if (!this.bankQueue.isEmpty()) {
      return (BankCustomer) this.bankQueue.deq();
    }
    return null;
  }

  /**
   * Add the customer to waitlist if the counter is full
   */
  public void addToWaitlist(BankCustomer customer) {
    this.bankQueue.enq(customer);
  }

  /**
   * Initialize the bank with counters
   */
  public Bank(int numCounters, int queueSize, int counterSize) {
    this.counters = new Seq<BankCounter>(numCounters);
    this.bankQueue = new Queue<BankCustomer>(queueSize);

    for (int i = 0; i < numCounters; i++) {
      counters.set(i, new BankCounter(i, counterSize));
    }
  }
}
