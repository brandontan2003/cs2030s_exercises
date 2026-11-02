/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ServiceBeginEvent extends Event {

  private BankCustomer customer;
  private BankCounter counter;
  private Bank bank;

  public ServiceBeginEvent(double time, BankCustomer customer, BankCounter counter, Bank bank) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
  }

  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": %s %s %s begins", customer.toString(), customer.describeTask(), counter.toString());
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // a service-end event at the current time + service time.
    this.bank.serve(this.customer, this.counter);
    this.customer.serve(this.counter);
    double endTime = this.getTime() + this.customer.getServiceTime();
    ServiceEndEvent endEvent = new ServiceEndEvent(endTime, this.customer, this.counter, this.bank);

    if (!this.counter.queueFull()) {
      BankCustomer bankCustomer = this.bank.getNextCustomer();
      if (bankCustomer != null) {
        double movementTime = 0.01;
        double eventTime = this.getTime() + movementTime;
        return new Event[] {
          endEvent, new JoinCounterQueueEvent(eventTime, bankCustomer, this.counter)
        };
      }
    }

    return new Event[] {endEvent};
  }
}
