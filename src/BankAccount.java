final class BankAccount {
    private Balance bal;
    private double amt_all_op = 0;
    private final double min_amt = 200;

    BankAccount() {
        this.bal = new Balance();
    }

    BankAccount(Balance bal) {
        if (bal == null) {
            throw new IllegalArgumentException("Balance cannot be null!");
        }

        this.bal = bal;
    }

    BankAccount(int start_amt) {
        this.bal = new Balance(start_amt);
    }

    BankAccount exchange(BankAccount bk, double amt) {
        if (bk == null) {
            throw new IllegalArgumentException("Account cannot be null!");
        }

        if (this == bk) {
            throw new IllegalArgumentException(
                    "Cannot transfer money to yourself!"
            );
        }

        if (!Double.isFinite(amt) || amt <= 0) {
            throw new IllegalArgumentException("Incorrect amount!");
        }

        if (amt < min_amt) {
            throw new IllegalArgumentException(
                    "Minimum transfer amount is " + min_amt
            );
        }

        if (bal.getCurrency() != bk.bal.getCurrency()) {
            throw new IllegalArgumentException(
                    "Accounts have different currencies!"
            );
        }

        if (bal.getAmount() < amt) {
            throw new IllegalStateException("Not enough money!");
        }

        if (!Double.isFinite(amt_all_op + amt) ||
                !Double.isFinite(bk.amt_all_op + amt)) {
            throw new ArithmeticException("Operation counter overflow!");
        }

        Balance newSenderBalance = bal.subtract(amt);
        Balance newReceiverBalance = bk.bal.add(amt);

        this.bal = newSenderBalance;
        bk.bal = newReceiverBalance;

        this.amt_all_op += amt;
        bk.amt_all_op += amt;

        return this;
    }

    void show() {
        bal.show();

        System.out.println(
                "Amount of all operations: " + amt_all_op + "\n"
        );
    }
}