/**
 * This class encapsulates an event in the bank simulation.
 *
 * @author Brandon Tan
 * @version 2026-2027 Sem 1
 */
class ArrivalEvent extends Event {

  private BankCustomer customer;
  private Bank bank;

  public ArrivalEvent(double time, BankCustomer customer, Bank bank) {
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
        + String.format(": %s arrives %s", customer.toString(), bank.toString());
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
    BankCounter counter = this.bank.findAvailableCounter();
    // If there are available counter and the customer is allocated to the first available counter
    if (counter.isAvailable()) {
      return new Event[] {new ServiceBeginEvent(this.getTime(), this.customer, counter, this.bank)};
    } else if (this.bank.waitlistFull() && counter.queueFull()) {
      // If both the bank queue and the counter queue is full, the customer departs
      return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
    }

    // if the counter queue is not full, add the customer to the counter queue,
    // else add it to the bankQueue
    if (!counter.queueFull()) {
      return new Event[] {new JoinCounterQueueEvent(this.getTime(), this.customer, counter)};
    }
    return new Event[] {new JoinBankQueueEvent(this.getTime(), this.customer, this.bank)};
  }
}
