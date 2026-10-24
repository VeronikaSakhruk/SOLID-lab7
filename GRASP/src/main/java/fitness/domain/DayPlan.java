package fitness.domain;

public class DayPlan extends MembershipPlan {
    public DayPlan(double monthlyRate, int months) {
        super("Day", monthlyRate, months);
    }

    @Override
    public double baseCost() {
        return rawCost() * 0.8;
    }
}
