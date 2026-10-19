package task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class TranscriptFormatter {

    public String format(Student student, List<CourseGrade> grades, double gpa, BigDecimal scholarship) {
        StringBuilder transcript = new StringBuilder();
        transcript.append("==========================================\n");
        transcript.append("ACADEMIC TRANSCRIPT: ").append(student.name()).append(" (ID: ").append(student.id()).append(")\n");
        transcript.append("==========================================\n");
        for (CourseGrade grade : grades) {
            transcript.append(String.format("%-30s | %d Credits | Score: %d%n", grade.courseName(), grade.ectsCredits(), grade.score()));
        }
        transcript.append("------------------------------------------\n");
        transcript.append(String.format("GPA: %.2f%n", gpa));
        transcript.append("Scholarship: ").append(scholarship).append(" UAH\n");
        return transcript.toString();
    }
}
