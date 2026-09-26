import java.util.*;

abstract class Question {

    protected String questionText;
    protected int marks;

    Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    MultipleChoiceQuestion(
            String questionText,
            String correctAnswer,
            int marks) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    TrueFalseQuestion(
            String questionText,
            boolean correctAnswer,
            int marks) {

        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Student {

    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {

    String examName;
    ArrayList<Question> questions;

    Examination(String examName) {
        this.examName = examName;
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {

    Student student;
    Examination examination;

    HashMap<Integer, String> answers;

    boolean submitted;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new HashMap<>();
        submitted = false;
    }

    public void recordAnswer(int questionNumber, String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println(
                "Answer recorded for Question "
                + questionNumber + ".");
    }

    public void submit() {

        submitted = true;

        System.out.println(
                examination.examName +
                " submitted by " +
                student.name);

        calculateResult();
    }

    public void calculateResult() {

        int totalScore = 0;
        int totalMarks = 0;

        for (int i = 0; i < examination.questions.size(); i++) {

            Question question =
                    examination.questions.get(i);

            totalMarks += question.marks;

            String answer = answers.get(i + 1);

            if (answer != null &&
                    question.evaluate(answer)) {

                totalScore += question.marks;

                System.out.println(
                        "Question " + (i + 1) +
                        ": Correct (" +
                        question.marks +
                        " points)");
            } else {

                System.out.println(
                        "Question " + (i + 1) +
                        ": Incorrect (0 points)");
            }
        }

        System.out.println(
                "Total score: " +
                totalScore + "/" + totalMarks);
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student =
                new Student("Student 1");

        Examination exam =
                new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "Which is an OOP concept?",
                        "C",
                        5));

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Java supports inheritance.",
                        true,
                        5));

        Attempt attempt =
                new Attempt(student, exam);

        System.out.println(
                "Exam A started by Student 1.");

        attempt.recordAnswer(1, "C");

        attempt.recordAnswer(2, "True");

        attempt.submit();

        System.out.println("\nStudent tries to change Question 1:");

        attempt.recordAnswer(1, "A");
    }
}