class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (monthlyFee <= 0) {

            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {

        System.out.print(
            "Standard | Sessions: " +
            sessionsAttended
        );
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.print(
            "Premium | Trainer: " +
            trainerName +
            " | Sessions: " +
            sessionsAttended
        );
    }
}


public class Problem4 {

    public static String batchPrint(
            GymMember[] members) {

        StringBuilder announcement =
            new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                    (PremiumMember) member;

                announcement.append(
                    "Premium | Trainer: "
                );

                announcement.append(
                    premium.getTrainerName()
                );

                announcement.append(
                    " | Sessions: "
                );

                announcement.append(
                    premium.getSessionsAttended()
                );

                announcement.append(
                    " [Trainer via downcast: "
                );

                announcement.append(
                    premium.getTrainerName()
                );

                announcement.append(
                    "] | "
                );

            } else {

                announcement.append(
                    "Standard | Sessions: "
                );

                announcement.append(
                    member.getSessionsAttended()
                );

                announcement.append(" | ");
            }
        }

        return announcement.toString();
    }


    public static void main(String[] args) {

        GymMember standard =
            new GymMember(
                "MEM6",
                1000
            );

        PremiumMember premium =
            new PremiumMember(
                "MEM7",
                2000,
                "Coach Riya"
            );

        GymMember[] members = {
            standard,
            premium
        };

        System.out.println(
            batchPrint(members)
        );
    }
}