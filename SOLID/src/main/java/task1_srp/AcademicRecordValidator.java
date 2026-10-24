package task1_srp;

import java.util.List;

public class AcademicRecordValidator {
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 100;

    public void validate(Student student, List<CourseGrade> grades) {
        if (student == null || isBlank(student.id()) || isBlank(student.name())) {
            throw new IllegalArgumentException("Student id and name must not be empty.");
        }
        if (grades == null || grades.isEmpty()) {
            throw new IllegalStateException("Course list is empty.");
        }
        for (CourseGrade grade : grades) {
            validateGrade(grade);
        }
    }

    private void validateGrade(CourseGrade grade) {
        if (grade.score() < MIN_SCORE || grade.score() > MAX_SCORE) {
            throw new IllegalArgumentException("Invalid score " + grade.score() + " for course " + grade.courseName() + ".");
        }
        if (grade.ectsCredits() <= 0) {
            throw new IllegalArgumentException("ECTS credits must be positive for course " + grade.courseName() + ".");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
