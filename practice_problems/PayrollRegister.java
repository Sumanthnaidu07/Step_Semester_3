
import java.util.*;

class PayrollRegister {

    static class Payslip {
        String name;
        String type;
        long pay;

        Payslip(String name, String type, long pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        @Override
        public String toString() {
            return "Payslip[name=" + name +
                   ", type=" + type +
                   ", pay=" + pay + "]";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Payslip> staffList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            String type = p[0];
            String name = p[1];
            long pay;

            switch (type) {
                case "FullTime":
                    pay = Long.parseLong(p[2]);
                    break;

                case "PartTime":
                    long hours = Long.parseLong(p[2]);
                    long rate = Long.parseLong(p[3]);
                    pay = hours * rate;
                    break;

                case "Intern":
                    pay = Long.parseLong(p[2]);
                    break;

                default:
                    continue;
            }

            staffList.add(new Payslip(name, type, pay));
        }

        Payslip[] payslips =
            staffList.toArray(new Payslip[0]);

        long total = 0;
        Payslip topEarner = payslips.length > 0
                            ? payslips[0] : null;

        for (Payslip p : payslips) {
            System.out.println(p);

            total += p.pay;

            if (topEarner == null || p.pay > topEarner.pay) {
                topEarner = p;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null) {
            System.out.println(
                "top earner " + topEarner.name);
        }

        sc.close();
    }
}
