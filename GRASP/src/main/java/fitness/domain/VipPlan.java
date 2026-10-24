package fitness.domain;

public class VipPlan extends MembershipPlan {
    public VipPlan(double monthlyRate, int months) {
        super("VIP", monthlyRate, months);
    }

    @Override
    public double baseCost() {
        return rawCost() + 5000.0;
    }
}
