package fitness.domain;

public class FullPlan extends MembershipPlan {
    public FullPlan(double monthlyRate, int months) {
        super("Full", monthlyRate, months);
    }

    @Override
    public double baseCost() {
        return rawCost();
    }
}
