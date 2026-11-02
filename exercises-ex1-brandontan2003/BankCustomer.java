class BankCustomer {

  /**
   * The id of a customer associated with this event.
   * First customer has id 0. Next is 1, 2, etc.
   */
  private int customerId;

  /**
   * The service time of the customer associated
   * this event.  This field matters only if the
   * event type is ARRIVAL or SERVICE_BEGIN.
   */
  private double serviceTime;

  /**
   * The getter method for customerId.
   */
  public int getCustomerId() {
    return this.customerId;
  }

  /**
   * The getter method for serviceTime.
   */
  public double getServiceTime() {
    return this.serviceTime;
  }

  /**
   * Constructor for a BankCustomer
   *
   * @param time      The time this event occurs.
   * @param customerId The customer associated with this event.
   *
   */
  public BankCustomer(double time, int customerId) {

    // Initialize the fields
    this.serviceTime = time;
    this.customerId = customerId;
  }
}
