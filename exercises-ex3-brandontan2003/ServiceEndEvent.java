/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ServiceEndEvent extends Event {

  private BankCustomer customer;
  private BankCounter counter;
  private Bank bank;

  public ServiceEndEvent(double time, BankCustomer customer, BankCounter counter, Bank bank) {
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
            ": %s %s %s ends: %s",
            customer.toString(),
            customer.describeTask(),
            counter.toString(),
            customer.transactionResult());
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // The current event is a service-end event.
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.bank.finishService(this.counter);
    DepartureEvent departEvent = new DepartureEvent(this.getTime(), this.customer);

    BankCustomer counterCustomer = this.counter.getNextCustomer();
    BankCustomer newCustomer =
        (counterCustomer == null) ? this.bank.getNextCustomer() : counterCustomer;

    if (newCustomer != null) {
      return new Event[] {
        departEvent, new ServiceBeginEvent(this.getTime(), newCustomer, this.counter, this.bank)
      };
    }
    return new Event[] {departEvent};
  }
}
