import java.util.Scanner;

/**
 * The main class for CS2030S Exercise 1.
 *
 * @author Adi Yoga S. Prabawa
 * @version 2026-2027 Sem 1
 */
class Main {
  public static void main(String[] args) {

    // Create a scanner to read from standard input.
    Scanner sc = new Scanner(System.in);

    // Create a simulation.  The BankSimulation
    // constructor will read the simulation parameters
    // and initial events using the scanner.
    Simulation simulation = new BankSimulation(sc);

    // Create a new simulator and run the simulation
    new Simulator(simulation).run();

    // Clean up the scanner.
    sc.close();
  }
}
