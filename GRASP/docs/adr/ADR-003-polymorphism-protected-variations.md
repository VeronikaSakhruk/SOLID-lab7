# ADR-003: Polymorphic plans and protected variations

## Status
Accepted

## Context
The legacy code selects the price rule by an `if/else` cascade on the plan code (`VIP` adds 5000, `Day` multiplies by 0.8, `Full` has no change). A new plan type means editing the calculation.

## QOC
* Question: how to remove the plan type cascade?
* Options:
  * A: keep `if/else`.
  * B: `switch` in `Subscription`.
  * C: abstract `MembershipPlan` with subclasses `DayPlan`, `FullPlan`, `VipPlan`, resolved through a map in `PlanCatalog`.
* Criteria:
  * Polymorphism: behavior depends on the type.
  * Protected Variations: adding a plan must not change `Subscription`.
* Conclusion: Option C.

## Decision
* Polymorphism: each plan overrides `baseCost()`.
* Protected Variations: `Subscription` and the controller depend only on the stable `MembershipPlan` contract; storage and notification variations are hidden behind `SubscriptionRepository` and `PushNotifier`.
* Dictionary dispatch: `PlanCatalog` maps the plan code to a plan instance.

## Consequences
* Positive: a new plan is one new class plus one map entry.
* Negative / compromises: plan rate and duration are hardcoded in `PlanCatalog`.
