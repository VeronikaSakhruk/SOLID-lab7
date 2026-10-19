package task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class ScholarshipPolicy {

    public static final int PASSING_SCORE = 60;
    public static final double INCREASED_GPA_THRESHOLD = 90.0;
    public static final double REGULAR_GPA_THRESHOLD = 75.0;
    public static final BigDecimal INCREASED_AMOUNT = new BigDecimal("2910.00");
    public static final BigDecimal REGULAR_AMOUNT = new BigDecimal("2000.00");

    public BigDecimal calculate(double gpa, List<CourseGrade> grades) {
        if (hasFailedCourses(grades)) {
            return BigDecimal.ZERO;
        }
        if (gpa >= INCREASED_GPA_THRESHOLD) {
            return INCREASED_AMOUNT;
        }
        if (gpa >= REGULAR_GPA_THRESHOLD) {
            return REGULAR_AMOUNT;
        }
        return BigDecimal.ZERO;
    }

    private boolean hasFailedCourses(List<CourseGrade> grades) {
        return grades.stream().anyMatch(grade -> grade.score() < PASSING_SCORE);
    }
}
