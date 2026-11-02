/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class DepartureEvent extends Event {

  private BankCustomer customer;

  public DepartureEvent(double time, BankCustomer customer) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
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
    return super.toString() + String.format(": Customer %d departs", customerId);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
