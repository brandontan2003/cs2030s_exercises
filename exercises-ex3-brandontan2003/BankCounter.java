class BankCounter implements Comparable<BankCounter> {

  /*
   * The id of the counter associated with this event.
   * This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private int counterId;

  private int counterBalance;

  private Queue<BankCustomer> counterQueue;

  /**
   * The customer that this counter is currently serving.
   */
  private BankCustomer customer;

  /**
   * A setter method to set the customer if the counter is occupied
   */
  public void serve(BankCustomer customer) {
    this.customer = customer;
  }

  /**
   * A setter method to set the customer to null once the counter is freed.
   */
  public void release() {
    this.customer = null;
  }

  public boolean deposit(int amount) {
    this.counterBalance += amount;
    return true;
  }

  public boolean withdraw(int amount) {
    if (amount <= counterBalance) {
      counterBalance -= amount;
      return true;
    }
    return false;
  }

  public boolean queueFull() {
    return this.counterQueue.isFull();
  }

  public String getWaitingList() {
    return this.counterQueue.toString();
  }

  public void addToWaitlist(BankCustomer customer) {
    this.counterQueue.enq(customer);
  }

  /**
   * Retrieve the next customer from the Counter Queue
   */
  public BankCustomer getNextCustomer() {
    if (!this.counterQueue.isEmpty()) {
      return this.counterQueue.deq();
    }
    return null;
  }

  /**
   * Check if the counter is being occupied by a customer:.
   */
  public boolean isAvailable() {
    return this.customer == null;
  }

  /**
   * Constructor for a bank counter.
   *
   * @param counterId   The id of the counter associated with
   *                    this event.
   */
  public BankCounter(int counterId, int queueSize) {
    // Initialize the fields
    this.counterId = counterId;
    this.counterQueue = new Queue<BankCustomer>(queueSize);
    this.counterBalance = 100;
  }

  @Override
  public String toString() {
    return String.format(
        "(at S%d $%d %s)", this.counterId, this.counterBalance, this.counterQueue.toString());
  }

  @Override
  public int compareTo(BankCounter counter) {
    if (this.isAvailable() != counter.isAvailable()) {
      // If 'this' is available  return -1 (this wins)
      // If counter is available, return 1 (counter wins)
      return this.isAvailable() ? -1 : 1;
    }
    // Get the counter that has a lesser number of customer in the queue.
    int queueCompare = Integer.compare(this.counterQueue.length(), counter.counterQueue.length());

    if (queueCompare != 0) {
      return queueCompare;
    }
    return Integer.compare(this.counterId, counter.counterId);
  }
}
