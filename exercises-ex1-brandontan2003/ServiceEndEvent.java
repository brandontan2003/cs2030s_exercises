/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ServiceEndEvent extends Event {

  private BankCustomer customer;
  private BankCounter counter;
  private BankService service;

  public ServiceEndEvent(
      double time, BankCustomer customer, BankCounter counter, BankService service) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
    this.counter = counter;
    this.service = service;
  }

  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    int customerId = this.customer.getCustomerId();

    int counterId = this.counter.getCounterId();
    return super.toString()
        + String.format(": Customer %d service ends (at Counter %d)", customerId, counterId);
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
    this.service.finishService(this.counter);
    return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
  }
}
