package fitness.domain;

public abstract class MembershipPlan {
    private final String code;
    private final double monthlyRate;
    private final int months;

    protected MembershipPlan(String code, double monthlyRate, int months) {
        this.code = code;
        this.monthlyRate = monthlyRate;
        this.months = months;
    }

    public String code() {
        return code;
    }

    protected double rawCost() {
        return monthlyRate * months;
    }

    public abstract double baseCost();
}
