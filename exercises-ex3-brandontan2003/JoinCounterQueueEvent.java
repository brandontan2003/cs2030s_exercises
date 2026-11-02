/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class JoinCounterQueueEvent extends Event {

  private BankCustomer customer;
  private BankCounter counter;

  public JoinCounterQueueEvent(double time, BankCustomer customer, BankCounter counter) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
    this.counter = counter;
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
            ": %s joins counter queue %s", customer.toString(), this.counter.toString());
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event:.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    this.counter.addToWaitlist(this.customer);
    return new Event[] {};
  }
}
