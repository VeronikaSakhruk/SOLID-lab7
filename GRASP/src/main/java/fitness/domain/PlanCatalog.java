package fitness.domain;

import java.util.Locale;
import java.util.Map;

public final class PlanCatalog {
    private static final Map<String, MembershipPlan> PLANS = Map.of(
            "day", new DayPlan(1200.0, 12),
            "full", new FullPlan(1200.0, 12),
            "vip", new VipPlan(1200.0, 12));

    private PlanCatalog() {}

    public static MembershipPlan find(String code) {
        MembershipPlan plan = PLANS.get(code.trim().toLowerCase(Locale.ROOT));
        if (plan == null) {
            throw new IllegalArgumentException("Unknown plan type: " + code);
        }
        return plan;
    }
}
