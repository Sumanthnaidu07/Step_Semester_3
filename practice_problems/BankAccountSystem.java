import java.util.*;

class BankAccountSystem {

    static class Account {
        String id;
        double balance;
        boolean savings;

        Account(String id, double balance, boolean savings) {
            this.id = id;
            this.balance = balance;
            this.savings = savings;
        }

        String withdraw(double amount) {
            if (savings && balance - amount < 1000) {
                return id + " rejected: minimum balance 1000";
            }

            if (!savings && balance - amount < -5000) {
                return id + " rejected: overdraft limit 5000";
            }

            balance -= amount;
            return id + " balance " + (long) balance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equalsIgnoreCase("Savings")) {
                accounts.put(p[1],
                    new Account(p[1], Double.parseDouble(p[2]), true));
            } else if (p[0].equalsIgnoreCase("Current")) {
                accounts.put(p[1],
                    new Account(p[1], Double.parseDouble(p[2]), false));
            } else if (p[0].equalsIgnoreCase("WITHDRAW")) {
                Account a = accounts.get(p[1]);

                if (a == null) {
                    System.out.println("Account not found");
                } else {
                    System.out.println(
                        a.withdraw(Double.parseDouble(p[2])));
                }
            }
        }

        sc.close();
    }
}
