/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ServiceBeginEvent extends Event {

  private BankCustomer customer;
  private BankCounter counter;
  private BankService service;

  public ServiceBeginEvent(
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
        + String.format(": Customer %d service begins (at Counter %d)", customerId, counterId);
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
    this.service.serve(this.customer, this.counter);
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {new ServiceEndEvent(endTime, this.customer, this.counter, this.service)};
  }
}
