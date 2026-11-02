class BankCounter {

  /**
   * The id of the counter associated with this event.
   * This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private int counterId;

  /**
   * The customer that this counter is currently serving.
   */
  private BankCustomer customer;

  /**
   * The getter method to return the counterId.
   */
  public int getCounterId() {
    return this.counterId;
  }

  /**
   * The getter method to return the customer.
   */
  public BankCustomer getCustomer() {
    return this.customer;
  }

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

  /**
   * Check if the counter is being occupied by a customer.
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
  public BankCounter(int counterId) {
    // Initialize the fields
    this.counterId = counterId;
  }
}
