final class Balance {
    private final Currency cur;
    private final long amt;

    Balance() {
        this(Currency.RUB, 0);
    }

    Balance(long amt) {
        this(Currency.RUB, amt);
    }

    Balance(Currency cur) {
        this(cur, 0);
    }

    Balance(Currency cur, long amt) {
        if (cur == null) {
            throw new IllegalArgumentException("Currency cannot be null!");
        }

        if (amt < 0) {
            throw new IllegalArgumentException("Incorrect balance!");
        }

        this.cur = cur;
        this.amt = amt;
    }

    long getAmount() {
        return amt;
    }

    Currency getCurrency() {
        return cur;
    }

    Balance add(long amt_new) {
        if (amt_new <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }

        return new Balance(cur, Math.addExact(amt, amt_new));
    }

    Balance subtract(long amt_new) {
        if (amt_new <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }

        if (amt_new > amt) {
            throw new IllegalStateException("Not enough money!");
        }

        return new Balance(cur, amt - amt_new);
    }

    Balance convert(Currency cur2) {
        if (cur2 == null) {
            throw new IllegalArgumentException("Currency cannot be null!");
        }

        java.math.BigDecimal result = java.math.BigDecimal.valueOf(amt)
                .multiply(java.math.BigDecimal.valueOf(
                        cur.getPriceInRublesKopecks()
                ))
                .divide(
                        java.math.BigDecimal.valueOf(
                                cur2.getPriceInRublesKopecks()
                        ),
                        0,
                        java.math.RoundingMode.HALF_UP
                );

        return new Balance(cur2, result.longValueExact());
    }

    void show() {
        System.out.printf(
                "Current balance: %d.%02d %s%n",
                amt / 100,
                amt % 100,
                cur.getSymbol()
        );
    }
}