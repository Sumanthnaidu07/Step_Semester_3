abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {

    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {

    enum Status {
        Pending,
        Approved,
        Rejected
    }

    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private Status status;

    LeaveRequest(Employee employee,
                 String startDate,
                 String endDate,
                 int days) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = Status.Pending;
    }

    public void approve() {

        if (status != Status.Pending) {
            System.out.println(
                    "Cannot approve an already processed request.");
            return;
        }

        if (employee.canTakeLeave(days)) {
            status = Status.Approved;

            System.out.println(
                    employee.getName() +
                    "'s leave request (" +
                    startDate + "-" + endDate +
                    ") approved.");
        } else {
            System.out.println(
                    "Leave policy does not allow this request.");
        }
    }

    public void reject() {

        if (status != Status.Pending) {
            System.out.println(
                    "Cannot reject an already processed request.");
            return;
        }

        status = Status.Rejected;

        System.out.println(
                employee.getName() +
                "'s leave request (" +
                startDate + "-" + endDate +
                ") rejected.");
    }

    public void changeToPending() {

        if (status != Status.Pending) {
            System.out.println(
                    "Cannot change leave request status from "
                    + status + " to Pending.");
        }
    }

    public void displayStatus() {
        System.out.println("Status: " + status);
    }
}

public class EmployeeLeaveSystem {

    public static void main(String[] args) {

        FullTimeEmployee john =
                new FullTimeEmployee("John");

        PartTimeEmployee jane =
                new PartTimeEmployee("Jane");

        LeaveRequest johnRequest =
                new LeaveRequest(
                        john,
                        "Jan 1",
                        "Jan 5",
                        5);

        System.out.println(
                "Leave request submitted for John (Jan 1-Jan 5).");

        johnRequest.displayStatus();

        johnRequest.approve();

        johnRequest.displayStatus();

        LeaveRequest janeRequest =
                new LeaveRequest(
                        jane,
                        "Feb 10",
                        "Feb 11",
                        2);

        System.out.println(
                "\nLeave request submitted for Jane (Feb 10-Feb 11).");

        janeRequest.displayStatus();

        janeRequest.reject();

        janeRequest.displayStatus();

        System.out.println("\nJohn attempts to change status:");

        johnRequest.changeToPending();
    }
}