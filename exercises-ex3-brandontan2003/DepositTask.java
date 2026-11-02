class DepositTask extends Task {
  private int amount;

  public DepositTask(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean execute(BankCounter counter) {
    return counter.deposit(amount);
  }

  @Override
  public String toString() {
    return String.format("deposit of $%d", this.amount);
  }
}
