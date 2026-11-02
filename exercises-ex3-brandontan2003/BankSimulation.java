import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Adi Yoga S. Prabawa
 * @version 2026-2027 Sem 1
 */
class BankSimulation extends Simulation {
  /**
   * The service method used for bank simulation.
   */
  private Bank bank;

  /**
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /**
   * Constructor for a bank simulation.
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int counterSize = sc.nextInt();
    int queueSize = sc.nextInt();
    this.bank = new Bank(numOfCounters, queueSize, counterSize);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int transactionType = sc.nextInt();
      int transactionAmt = sc.nextInt();
      BankCustomer customer = new BankCustomer(serviceTime, id, transactionType, transactionAmt);

      initEvents[id] = new ArrivalEvent(arrivalTime, customer, this.bank);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
