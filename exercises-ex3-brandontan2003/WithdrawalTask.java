class WithdrawalTask extends Task {
  private int amount;

  public WithdrawalTask(int amount) {
    this.amount = amount;
  }

  @Override
  public boolean execute(BankCounter counter) {
    return counter.withdraw(amount);
  }

  @Override
  public String toString() {
    return String.format("withdrawal of $%d", this.amount);
  }
}
