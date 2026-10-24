# Peer review checklist

| Check | Result |
|---|---|
| No public fields or setters in domain classes | Passed |
| Entities are created only through static factories in a valid state | Passed |
| No console input/output in the controller | Passed |
| No business logic in the CLI | Passed |
| No `if/else` cascade by plan type | Passed |
| No Demeter violations | Passed |
| CBO <= 4 for entities and controller | Passed |
| LCOM4 = 1 for all classes | Passed |
| ADR documents present in `docs/adr/` | Passed |

GRASP mapping:
* Information Expert: `MembershipPlan.baseCost`, `Member.discountOn`, `Subscription.contractText`
* Creator: `Subscription.open`, `Member.create`
* Controller: `SubscriptionController`
* Low Coupling / High Cohesion: see `docs/METRICS.md`
* Pure Fabrication: `ConsoleCommandParser`, `PlanCatalog`, `InMemorySubscriptionRepository`, `DevicePushNotifier`
* Indirection: `SubscriptionRepository`, `PushNotifier`
* Polymorphism: `DayPlan`, `FullPlan`, `VipPlan`
* Protected Variations: `MembershipPlan`, `SubscriptionRepository`, `PushNotifier`
