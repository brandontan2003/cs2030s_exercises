/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class JoinBankQueueEvent extends Event {

  private BankCustomer customer;
  private Bank bank;

  public JoinBankQueueEvent(double time, BankCustomer customer, Bank bank) {
    // Call the constructor Event(double)
    super(time);

    this.customer = customer;
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
            ": %s joins bank queue %s", customer.toString(), bank.toString());
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event:.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    this.bank.addToWaitlist(this.customer);
    return new Event[] {};
  }
}
