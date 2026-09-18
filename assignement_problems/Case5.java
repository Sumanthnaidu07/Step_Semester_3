final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        // Defensive copy
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        // Make a new copy
        String[] newBookIds = bookIds.clone();

        // Change only the new copy
        newBookIds[index] = newId;

        // Return a brand-new object
        return new LoanReceipt(memberId, newBookIds);
    }
}


// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


public class Case5 {

    static class CirculationLedger {

        static String branchCode;

        // Static block
        static {
            branchCode = "PT-LIB";
        }

        static String processNightlyCirculation(
                LoanReceipt[] receipts) {

            int processed = 0;
            int nullSkipped = 0;
            int referenceOnly = 0;
            int regular = 0;

            for (LoanReceipt receipt : receipts) {

                // Handle null safely
                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;

                // Check object type
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }

            return processed + " processed | " +
                   nullSkipped + " null skipped | " +
                   referenceOnly + " reference-only | " +
                   regular + " regular";
        }
    }


    public static void main(String[] args) {

        // -------------------------------
        // Test 1: Defensive Copy
        // -------------------------------

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);


        // -------------------------------
        // Test 2: Wither Pattern
        // -------------------------------

        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(r.getBookIds()[0]);
        System.out.println(r.getBookIds()[1]);

        System.out.println(corrected.getBookIds()[0]);
        System.out.println(corrected.getBookIds()[1]);


        // -------------------------------
        // Test 3: Nightly Processing
        // -------------------------------

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(
            CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}