# Metrics report

Counting rules: types from `java.*` and primitives are not counted; only project types and `Scanner` as console infrastructure in the legacy code is counted.
LCOM4: constructors, static factories and trivial accessors are excluded.

## CBO

| Class | Before | After | Coupled classes (after) |
|---|---|---|---|
| LegacyFitnessService | 4 (Scanner, Member, MembershipPlan, Subscription) | removed | |
| SubscriptionController | n/a | 4 | RegistrationRequest, Subscription, SubscriptionRepository, PushNotifier |
| Subscription | 0 (passive data) | 3 | Member, MembershipPlan, PlanCatalog |
| Member | 0 (passive data) | 0 | |
| MembershipPlan | 0 (passive data) | 0 | |
| DayPlan / FullPlan / VipPlan | n/a | 1 each | MembershipPlan |
| PlanCatalog | n/a | 4 | MembershipPlan, DayPlan, FullPlan, VipPlan |
| ConsoleCommandParser | n/a | 1 | RegistrationRequest |
| InMemorySubscriptionRepository | n/a | 2 | SubscriptionRepository, Subscription |
| DevicePushNotifier | n/a | 1 | PushNotifier |
| Main (composition root) | n/a | 4 | SubscriptionController, ConsoleCommandParser, InMemorySubscriptionRepository, DevicePushNotifier |

Note on the legacy value: the formula gives 4 because all work is done by one method that touches only three data classes. The real problem is that this one class mixes 9 responsibilities (input, object building, plan pricing, trainer fee, discount, contract formatting, persistence, notification, console output) and reaches into the internals of the data classes with 8 Demeter-violating chains.
After refactoring all domain entities and the controller satisfy CBO <= 4 and the number of Demeter-violating chains is 0.

## LCOM4

| Class | Before | After |
|---|---|---|
| LegacyFitnessService | 1 formally (single method), 9 responsibilities | removed |
| Member | 1 (anemic, no methods) | 1 (`discountOn`) |
| MembershipPlan and subclasses | 1 (anemic) | 1 (`baseCost` / `rawCost` share rate and months) |
| Subscription | 1 (anemic) | 1 (`contractText` uses all state) |
| SubscriptionController | n/a | 1 (`register`) |
| ConsoleCommandParser | n/a | 1 (`read`) |
| PlanCatalog | n/a | 1 (`find`) |
| InMemorySubscriptionRepository | n/a | 1 (`save`) |
| DevicePushNotifier | n/a | 1 (`notifyMember`) |

Responsibilities per class: 9 in the legacy service, 1 in every class after refactoring.

## Instability I = Ce / (Ca + Ce)

| Package | Ca | Ce | I |
|---|---|---|---|
| domain | 3 | 0 | 0.00 |
| application | 4 | 1 | 0.20 |
| infrastructure | 1 | 3 | 0.75 |
| cli | 0 | 4 | 1.00 |

Dependencies go from unstable to stable: cli (1.00) -> application (0.20) -> domain (0.00); infrastructure (0.75) -> application (0.20) -> domain (0.00).

## Conclusions
* The domain core has no dependency on console, storage or notification.
* All domain entities and the controller have CBO <= 4; all classes have LCOM4 = 1.
* Behavior of the legacy code is preserved (VIP, 12 months, trainer, discount card: base 22400.0, discount 2240.0, total 20160.0).
