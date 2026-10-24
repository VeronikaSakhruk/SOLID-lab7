package task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class StudentRecordProcessor {

    private final AcademicRecordValidator validator;
    private final GpaCalculator gpaCalculator;
    private final ScholarshipPolicy scholarshipPolicy;
    private final TranscriptFormatter formatter;
    private final ScholarshipRegistryExporter exporter;
    private final CuratorNotifier notifier;

    public StudentRecordProcessor(AcademicRecordValidator validator,
                                  GpaCalculator gpaCalculator,
                                  ScholarshipPolicy scholarshipPolicy,
                                  TranscriptFormatter formatter,
                                  ScholarshipRegistryExporter exporter,
                                  CuratorNotifier notifier) {
        this.validator = validator;
        this.gpaCalculator = gpaCalculator;
        this.scholarshipPolicy = scholarshipPolicy;
        this.formatter = formatter;
        this.exporter = exporter;
        this.notifier = notifier;
    }

    public void process(Student student, List<CourseGrade> grades, String curatorEmail) {
        validator.validate(student, grades);
        double gpa = gpaCalculator.calculate(grades);
        BigDecimal scholarship = scholarshipPolicy.calculate(gpa, grades);
        String transcript = formatter.format(student, grades, gpa, scholarship);
        exporter.export(student, gpa, scholarship);
        notifier.notify(curatorEmail, student, transcript);
    }
}
