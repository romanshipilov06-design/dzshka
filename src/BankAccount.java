public class Main {
    public static void main(String[] args) {

        BankAccount t1 = new BankAccount(500_000);
        BankAccount t2 = new BankAccount(1_000_000);

        System.out.println("Initial balances:");
        t1.show();
        t2.show();

        try {
            t1.exchange(t2, 50_000);
            System.out.println("Transfer successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Argument error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Account error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        }

        t1.show();
        t2.show();

        try {
            t1.exchange(t2, -50_000);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative amount error: " + e.getMessage());
        }

        try {
            t1.exchange(t1, 50_000);
        } catch (IllegalArgumentException e) {
            System.out.println("Self-transfer error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, 10_000);
        } catch (IllegalArgumentException e) {
            System.out.println("Minimum amount error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, 10_000_000);
        } catch (IllegalStateException e) {
            System.out.println("Insufficient funds error: " + e.getMessage());
        }

        try {
            t1.exchange(null, 50_000);
        } catch (IllegalArgumentException e) {
            System.out.println("Null account error: " + e.getMessage());
        }

        try {
            Balance rub = new Balance(Currency.RUB, 100_000);
            Balance usd = rub.convert(Currency.USD);

            System.out.println("Currency conversion:");
            rub.show();
            usd.show();

        } catch (IllegalArgumentException e) {
            System.out.println("Conversion argument error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Conversion arithmetic error: " + e.getMessage());
        }
    }
}