package task1_srp;

import java.util.List;

public class GpaCalculator {

    public double calculate(List<CourseGrade> grades) {
        double weightedScore = 0;
        int totalCredits = 0;
        for (CourseGrade grade : grades) {
            weightedScore += grade.score() * grade.ectsCredits();
            totalCredits += grade.ectsCredits();
        }
        return weightedScore / totalCredits;
    }
}
