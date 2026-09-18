class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // Public no-argument constructor
    public LibraryMember() {
    }

    // Write-once membershipId
    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getMembershipId() {
        return membershipId;
    }

    // Name property
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Premium member property
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        // Simple one-way transformation
        securityAnswer = Integer.toString(answer.hashCode());
    }
}

public class Case4 {

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");

        m.setName("Priya Nair");

        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());

        // Second attempt is ignored
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        System.out.println(m.isPremiumMember());

        // No getter exists for securityAnswer
        m.setSecurityAnswer("BlueMountain");
    }
}