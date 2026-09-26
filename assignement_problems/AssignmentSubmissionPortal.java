import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double applyLatePenalty(
        double marks,
        long lateDays
    );
}

class CodingAssignment extends Assignment {

    public CodingAssignment(
        String title,
        int maxMarks,
        LocalDate dueDate
    ) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(double marks, long lateDays) {

        double penalty = lateDays * 0.10;

        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    public WrittenAssignment(
        String title,
        int maxMarks,
        LocalDate dueDate
    ) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(double marks, long lateDays) {

        double penalty = lateDays * 0.20;

        return marks * (1 - penalty);
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

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {

    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(
        Student student,
        Assignment assignment,
        LocalDate submissionDate
    ) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;
    }

    public void grade(double awardedMarks) {

        if (status == SubmissionStatus.GRADED) {
            System.out.println(
                "Cannot grade again: " +
                assignment.getTitle() +
                " has already been graded."
            );
            return;
        }

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                assignment.getDueDate(),
                submissionDate
            );
        }

        finalMarks = assignment.applyLatePenalty(
            awardedMarks,
            lateDays
        );

        status = SubmissionStatus.GRADED;

        if (lateDays == 0) {
            System.out.printf(
                "%s graded: %.0f/%d. Status: Graded.%n",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks()
            );
        } else {
            System.out.printf(
                "%s graded: %.0f/%d after %d days late penalty. Status: Graded.%n",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks(),
                lateDays
            );
        }
    }

    public void resubmit(LocalDate newDate) {

        if (status == SubmissionStatus.GRADED) {
            System.out.println(
                "Cannot resubmit: '" +
                assignment.getTitle() +
                "' has already been graded."
            );
            return;
        }

        submissionDate = newDate;

        System.out.println(
            student.getName() +
            " resubmitted '" +
            assignment.getTitle() +
            "'."
        );
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
            "Linked List Lab",
            50,
            LocalDate.of(2026, 3, 10)
        );

        Assignment written = new WrittenAssignment(
            "Design Essay",
            50,
            LocalDate.of(2026, 3, 12)
        );

        Submission ashaSubmission = new Submission(
            asha,
            coding,
            LocalDate.of(2026, 3, 10)
        );

        Submission raviSubmission = new Submission(
            ravi,
            written,
            LocalDate.of(2026, 3, 14)
        );

        System.out.println(
            "Asha's submission for 'Linked List Lab' received (on time)."
        );
        System.out.println("Status: Submitted.");

        System.out.println(
            "Ravi's submission for 'Design Essay' received (2 days late)."
        );
        System.out.println("Status: Submitted.");

        ashaSubmission.grade(45);

        raviSubmission.grade(40);

        ashaSubmission.resubmit(
            LocalDate.of(2026, 3, 15)
        );
    }
}