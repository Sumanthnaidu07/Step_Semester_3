
import java.util.*;

class StudentClubRegistration {

    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student s = (Student) obj;
            return rollNumber.equals(s.rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Student> members = new HashSet<>();
        List<String> addResults = new ArrayList<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            Student s = new Student(p[1], p[2]);

            if (p[0].equalsIgnoreCase("ADD")) {
                if (members.add(s)) {
                    addResults.add("Added");
                } else {
                    addResults.add("duplicate rejected");
                }
            } else if (p[0].equalsIgnoreCase("CONTAINS")) {
                containsResults.add(
                    "contains: " + members.contains(s));
            }
        }

        for (String result : addResults) {
            System.out.println(result);
        }

        System.out.println("member count " + members.size());

        for (String result : containsResults) {
            System.out.println(result);
        }

        sc.close();
    }
}
