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

  /*
   * The type of task the customer is performing (i.e. deposit/withdrawal)
   */
  private Task task;

  /*
   * A field used to determine if the transaction is successful or failure
   */
  private boolean txnStatus;

  /**
   * The getter method for serviceTime.
   */
  public double getServiceTime() {
    return this.serviceTime;
  }

  public void serve(BankCounter counter) {
    this.txnStatus = task.execute(counter);
  }

  /**
   * Constructor for a BankCustomer
   *
   * @param time      The time this event occurs.
   * @param customerId The customer associated with this event.
   *
   */
  public BankCustomer(double time, int customerId, int txnType, int txnAmt) {

    // Initialize the fields
    this.serviceTime = time;
    this.customerId = customerId;

    if (txnType == 0) {
      this.task = new DepositTask(txnAmt);
    } else if (txnType == 1) {
      this.task = new WithdrawalTask(txnAmt);
    }
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);
  }

  public String describeTask() {
    return task.toString();
  }

  public String transactionResult() {
    return txnStatus ? "success" : "fail";
  }
}
