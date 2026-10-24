package fitness.domain;

public class Subscription {
    private final double trainerFee = 3000.0;
    private final int id;
    private final Member member;
    private final MembershipPlan plan;
    private final boolean personalTrainerIncluded;
    private final String startDate;
    private final double baseAmount;
    private final double discountAmount;
    private final double finalCost;
    private final String status;

    private Subscription(int id, Member member, MembershipPlan plan, boolean personalTrainerIncluded, String startDate) {
        this.id = id;
        this.member = member;
        this.plan = plan;
        this.personalTrainerIncluded = personalTrainerIncluded;
        this.startDate = startDate;
        this.baseAmount = plan.baseCost() + (personalTrainerIncluded ? trainerFee : 0.0);
        this.discountAmount = member.discountOn(baseAmount);
        this.finalCost = baseAmount - discountAmount;
        this.status = "Active";
    }

    public static Subscription open(int id, String memberName, String planCode, boolean personalTrainerIncluded, String startDate) {
        return new Subscription(id, Member.create(memberName), PlanCatalog.find(planCode), personalTrainerIncluded, startDate);
    }

    public int id() {
        return id;
    }

    public String memberName() {
        return member.name();
    }

    public String contractText() {
        return "Contract #" + id + " with " + member.name()
                + "\nPlan: " + plan.code()
                + "\nBase cost: " + baseAmount + " UAH"
                + "\nDiscount: " + discountAmount + " UAH"
                + "\nTotal due: " + finalCost + " UAH";
    }
}
