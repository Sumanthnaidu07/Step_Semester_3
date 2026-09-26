interface WashType {
    int getDuration();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }

    public String getName() {
        return "Quick";
    }
}

class NormalWash implements WashType {
    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }

    public String getName() {
        return "Normal";
    }
}

class HeavyWash implements WashType {
    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }

    public String getName() {
        return "Heavy";
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        System.out.println(
            washType.getName() + " wash started on " +
            machine.getMachineId() + " for " +
            student.getName() + " (" +
            washType.getDuration() + " min)."
        );

        System.out.printf("Charge: ₹%.2f%n", washType.getCharge());
    }
}

class WashingMachine {
    private String machineId;
    private boolean busy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public WashCycle startWash(Student student, WashType washType) {

        if (busy) {
            System.out.println(
                "Machine " + machineId + " is currently busy."
            );
            return null;
        }

        busy = true;

        WashCycle cycle = new WashCycle(student, this, washType);
        cycle.start();

        return cycle;
    }

    public void completeWash() {
        if (busy) {
            busy = false;
            System.out.println(
                machineId + " cycle completed. " +
                machineId + " is now free."
            );
        }
    }
}

public class HostelLaundryQueue {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType heavy = new HeavyWash();
        WashType normal = new NormalWash();

        // Asha starts Quick wash on M1
        m1.startWash(asha, quick);

        // Ravi attempts Heavy wash on busy M1
        m1.startWash(ravi, heavy);

        // Ravi starts Heavy wash on M2
        m2.startWash(ravi, heavy);

        // M1 completes
        m1.completeWash();

        // Neha starts Normal wash on M1
        m1.startWash(neha, normal);
    }
}