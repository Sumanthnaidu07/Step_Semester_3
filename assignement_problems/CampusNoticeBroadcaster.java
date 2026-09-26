import java.util.*;

interface NotificationChannel {

    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
            "[Email → " +
            student.getName() +
            "] " +
            notice.getTitle()
        );
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
            "[SMS → " +
            student.getName() +
            "] " +
            notice.getTitle()
        );
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
            "[App → " +
            student.getName() +
            "] " +
            notice.getTitle()
        );
    }
}

class Student {

    private String name;
    private String department;

    private List<NotificationChannel> channels =
        new ArrayList<>();

    public Student(
        String name,
        String department
    ) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(
        NotificationChannel channel
    ) {
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> targetDepartments;

    public Notice(
        String title,
        Set<String> targetDepartments
    ) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Notice title cannot be empty."
            );
        }

        if (
            targetDepartments == null ||
            targetDepartments.isEmpty()
        ) {
            throw new IllegalArgumentException(
                "At least one target department is required."
            );
        }

        this.title = title;
        this.targetDepartments =
            new HashSet<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTargetDepartments() {
        return targetDepartments;
    }
}

class NoticeBoard {

    private List<Student> students =
        new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        System.out.println(
            "Notice '" +
            notice.getTitle() +
            "' posted to " +
            String.join(
                ", ",
                notice.getTargetDepartments()
            ) +
            "."
        );

        for (Student student : students) {

            if (
                notice.getTargetDepartments()
                    .contains(student.getDepartment())
            ) {

                for (
                    NotificationChannel channel :
                    student.getChannels()
                ) {

                    channel.send(
                        student,
                        notice
                    );
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        NoticeBoard board =
            new NoticeBoard();

        // Create students
        Student asha =
            new Student("Asha", "CSE");

        Student ravi =
            new Student("Ravi", "ECE");

        // Set preferred channels
        asha.addChannel(
            new EmailChannel()
        );

        asha.addChannel(
            new AppChannel()
        );

        ravi.addChannel(
            new SmsChannel()
        );

        // Add students to notice board
        board.addStudent(asha);
        board.addStudent(ravi);

        // Notice 1
        Set<String> cse =
            new HashSet<>();

        cse.add("CSE");

        Notice notice1 =
            new Notice(
                "Lab Closed Tomorrow",
                cse
            );

        board.postNotice(notice1);

        // Notice 2
        Set<String> cseEce =
            new HashSet<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice notice2 =
            new Notice(
                "Fee Deadline Extended",
                cseEce
            );

        board.postNotice(notice2);

        // Invalid notice
        try {

            Set<String> emptyDepartments =
                new HashSet<>();

            Notice notice3 =
                new Notice(
                    "Sports Day",
                    emptyDepartments
                );

            board.postNotice(notice3);

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Cannot post notice: " +
                e.getMessage()
            );
        }
    }
}