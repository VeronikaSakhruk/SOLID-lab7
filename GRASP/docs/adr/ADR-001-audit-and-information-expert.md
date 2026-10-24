# ADR-001: Audit of legacy code and move of pricing logic into domain entities

## Status
Accepted

## Context
Variant 3: fitness club network, subscription registration and pricing.
The legacy `LegacyFitnessService.registerSubscriptionConsole()` is one method that does everything.

Detected problems:
* Anemic Domain Model: `Member`, `MembershipPlan`, `Subscription` have only public mutable fields and no behavior.
* Broken encapsulation: the service fills every field of every object step by step from outside.
* Bloated Controller / Monolithic Main: one method reads console input, builds objects, prices the plan, applies the discount, formats the contract, "saves" to the database and sends the push notification.
* Spaghetti code: `if/else` cascade on the plan code (`VIP`, `Day`) mixed with trainer and discount calculation.
* Law of Demeter violations: 8 chained accesses such as `sub.plan.baseMonthlyRate`, `sub.member.hasDiscountCard`, `sub.member.name`.
* High coupling of the business flow to console, storage and notification details.

## QOC
* Question: who is responsible for calculating the subscription price and the member discount?
* Options:
  * A: keep the calculation in the service / `Main`.
  * B: put everything into `Subscription`, including the discount rule.
  * C: `MembershipPlan` calculates the plan cost, `Member` calculates its own discount, `Subscription` composes the total.
* Criteria:
  * Information Expert: who owns the data needed for the calculation.
  * High Cohesion: each class manages only its own state.
  * Low Coupling: the service must not know entity internals.
* Conclusion: Option C. `MembershipPlan` owns rate and duration, `Member` owns the discount card flag, `Subscription` owns the trainer fee and the final sum.

## Decision
* Information Expert: `MembershipPlan.baseCost()`, `Member.discountOn(amount)`, `Subscription` constructor computes base amount, discount and final cost, `Subscription.contractText()` builds the contract.
* Creator: `Subscription.open(...)` and `Member.create(...)` are static factory methods; constructors are private; all fields are `private final`, so an object is always created in a valid state.

## Consequences
* Positive: no public fields, no setters, no Demeter chains, price rules live next to their data.
* Negative / compromises: `Subscription` depends on `Member`, `MembershipPlan` and `PlanCatalog`.
