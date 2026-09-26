interface MembershipPlan {

    double calculateFee();

    String getPlanName();
}

class MonthlyPlan implements MembershipPlan {

    private final double baseRate = 1000;

    public double calculateFee() {
        return baseRate;
    }

    public String getPlanName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {

    private final double baseRate = 1000;

    public double calculateFee() {
        return baseRate * 3 * 0.90;
    }

    public String getPlanName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {

    private final double baseRate = 1000;

    public double calculateFee() {
        return baseRate * 12 * 0.75;
    }

    public String getPlanName() {
        return "Annual";
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    public Membership(
        Member member,
        MembershipPlan plan
    ) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
    }

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {

            System.out.println(
                member.getName() +
                " checked in successfully."
            );

        } else {

            System.out.println(
                "Check-in denied: " +
                member.getName() +
                "'s membership is " +
                status + "."
            );
        }
    }

    public void freeze() {

        if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                "Cannot freeze an Expired membership."
            );

        } else if (status == MembershipStatus.FROZEN) {

            System.out.println(
                member.getName() +
                "'s membership is already frozen."
            );

        } else {

            status = MembershipStatus.FROZEN;

            System.out.println(
                member.getName() +
                "'s membership frozen. Status: Frozen."
            );
        }
    }

    public void unfreeze() {

        if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                "Cannot unfreeze an Expired membership."
            );

        } else if (status == MembershipStatus.FROZEN) {

            status = MembershipStatus.ACTIVE;

            System.out.println(
                member.getName() +
                "'s membership unfrozen. Status: Active."
            );

        } else {

            System.out.println(
                "Membership is already Active."
            );
        }
    }

    public void expire() {

        status = MembershipStatus.EXPIRED;

        System.out.println(
            member.getName() +
            "'s membership expired. Status: Expired."
        );
    }

    public MembershipStatus getStatus() {
        return status;
    }
}

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        MembershipPlan quarterly =
            new QuarterlyPlan();

        MembershipPlan monthly =
            new MonthlyPlan();

        Membership ashaMembership =
            new Membership(
                asha,
                quarterly
            );

        Membership raviMembership =
            new Membership(
                ravi,
                monthly
            );

        System.out.printf(
            "Quarterly membership created for Asha. Fee: ₹%.2f. Status: Active.%n",
            quarterly.calculateFee()
        );

        System.out.printf(
            "Monthly membership created for Ravi. Fee: ₹%.2f. Status: Active.%n",
            monthly.calculateFee()
        );

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes
        ashaMembership.freeze();

        // Asha tries to check in
        ashaMembership.checkIn();

        // Ravi expires
        raviMembership.expire();

        // Ravi tries to freeze
        raviMembership.freeze();
    }
}