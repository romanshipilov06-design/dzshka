final class Balance {
    private final Currency cur;
    private final double amt;

    Balance() {
        this(Currency.RUB, 0);
    }

    Balance(double amt) {
        this(Currency.RUB, amt);
    }

    Balance(Currency cur) {
        this(cur, 0);
    }

    Balance(Currency cur, double amt) {
        if (cur == null) {
            throw new IllegalArgumentException("Currency cannot be null!");
        }

        if (!Double.isFinite(amt) || amt < 0) {
            throw new IllegalArgumentException("Incorrect balance!");
        }

        this.cur = cur;
        this.amt = amt;
    }

    double getAmount() {
        return amt;
    }

    Currency getCurrency() {
        return cur;
    }

    Balance add(double amt_new) {
        if (!Double.isFinite(amt_new) || amt_new <= 0) {
            throw new IllegalArgumentException("Incorrect amount!");
        }

        double result = this.amt + amt_new;

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Balance overflow!");
        }

        return new Balance(cur, result);
    }

    Balance subtract(double amt_new) {
        if (!Double.isFinite(amt_new) || amt_new <= 0) {
            throw new IllegalArgumentException("Incorrect amount!");
        }

        if (amt_new > this.amt) {
            throw new IllegalStateException("Not enough money!");
        }

        return new Balance(cur, this.amt - amt_new);
    }

    Balance convert(Currency cur2) {
        if (cur2 == null) {
            throw new IllegalArgumentException("Currency cannot be null!");
        }

        double oldRate = cur.getPriceInRublesKopecks();
        double newRate = cur2.getPriceInRublesKopecks();

        if (oldRate <= 0 || newRate <= 0) {
            throw new IllegalStateException("Incorrect currency rate!");
        }

        double result = amt * oldRate / newRate;

        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Conversion overflow!");
        }

        return new Balance(cur2, result);
    }

    void show() {
        System.out.printf(
                "Current balance: %.2f %s%n",
                amt,
                cur.getSymbol()
        );
    }
}