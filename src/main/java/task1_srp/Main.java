package task1_srp;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        StudentRecordProcessor processor = new StudentRecordProcessor(
                new AcademicRecordValidator(),
                new GpaCalculator(),
                new ScholarshipPolicy(),
                new TranscriptFormatter(),
                new ScholarshipRegistryExporter("scholarship_registry.csv"),
                new CuratorNotifier());

        Student student = new Student("ST-101", "Ivan Petrenko");
        List<CourseGrade> grades = List.of(
                new CourseGrade("Software Engineering", 95, 6),
                new CourseGrade("Databases", 88, 5),
                new CourseGrade("Algorithms", 92, 4));

        processor.process(student, grades, "curator@univ.local");
    }
}
