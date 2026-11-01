public class Main {
    public static void main(String[] args) {

        BankAccount t1 = new BankAccount(5000);
        BankAccount t2 = new BankAccount(10000);

        System.out.println("Initial balances:");
        t1.show();
        t2.show();

        try {
            System.out.println("Transfer 500:");
            t1.exchange(t2, 500);
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
            t1.exchange(t2, -500);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative amount error: " + e.getMessage());
        }

        try {
            t1.exchange(t1, 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Self-transfer error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Minimum amount error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, 100000);
        } catch (IllegalStateException e) {
            System.out.println("Insufficient funds error: " + e.getMessage());
        }

        try {
            t1.exchange(null, 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Null account error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Zero amount error: " + e.getMessage());
        }

        try {
            t1.exchange(t2, Double.NaN);
        } catch (IllegalArgumentException e) {
            System.out.println("NaN error: " + e.getMessage());
        }

        try {
            Balance rub = new Balance(Currency.RUB, 1000);
            Balance usd = rub.convert(Currency.USD);

            System.out.println("Currency conversion:");
            rub.show();
            usd.show();

        } catch (IllegalArgumentException e) {
            System.out.println("Conversion argument error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Conversion state error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Conversion arithmetic error: " + e.getMessage());
        }
    }
}