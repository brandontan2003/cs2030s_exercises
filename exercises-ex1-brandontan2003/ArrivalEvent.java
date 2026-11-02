/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ArrivalEvent extends Event {

  private BankCustomer customer;
  private BankService service;

  public ArrivalEvent(double time, BankCustomer customer, BankService service) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
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
    return super.toString() + String.format(": Customer %d arrives", customerId);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // Find the first available counter.
    BankCounter counter = this.service.findAvailableCounter();
    if (counter == null) {
      // If no such counter can be found, the customer should depart.
      return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
    } else {
      // Else, the customer should go the the first
      // available counter and get served.
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter, this.service)
      };
    }
  }
}
