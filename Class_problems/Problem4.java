class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                "Borrow limit must be positive"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.print(
            "General | Books: " + booksBorrowed
        );
    }
}


class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(String memberId,
                          int borrowLimit,
                          String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {

        System.out.print(
            "Student | Course: " + course +
            " | Books: " + booksBorrowed
        );
    }
}


public class Problem4 {

    public static String batchPrint(
        LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (LibraryMember member : members) {

            member.displayInfo();

            if (member instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) member;

                report.append(
                    "Student | Course: "
                );

                report.append(
                    student.getCourse()
                );

                report.append(
                    " | Books: "
                );

                report.append(
                    student.getBooksBorrowed()
                );

                report.append(
                    " [Course via downcast: "
                );

                report.append(
                    student.getCourse()
                );

                report.append("] | ");

            } else {

                report.append(
                    "General | Books: "
                );

                report.append(
                    member.getBooksBorrowed()
                );

                report.append(" | ");
            }
        }

        return report.toString();
    }


    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB5", 3);

        StudentMember student =
            new StudentMember(
                "STU6", 3, "ECE"
            );

        LibraryMember[] members = {
            general, student
        };

        System.out.println(
            batchPrint(members)
        );
    }
}