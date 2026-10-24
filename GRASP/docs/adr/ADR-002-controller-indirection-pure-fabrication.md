# ADR-002: Thin console UI, use-case controller, repository and notifier abstractions

## Status
Accepted

## Context
Console input, business flow, storage and notification were mixed in one method.

## QOC
* Question: who coordinates the registration use case and how is it isolated from I/O and infrastructure?
* Options:
  * A: leave the flow in `Main`.
  * B: a use-case controller that calls domain objects directly and prints results.
  * C: thin CLI, an application controller without I/O, interfaces for storage and notification.
* Criteria:
  * Controller: first object after the UI that handles the system operation.
  * Indirection: the controller must not depend on concrete storage or notification.
  * Low Coupling: CBO of the controller is at most 4.
* Conclusion: Option C.

## Decision
* Thin UI: `ConsoleCommandParser` reads the text and builds `RegistrationRequest`; `Main` only wires objects and prints returned lines. No business logic in the CLI.
* Controller: `SubscriptionController.register(request)` creates the subscription, saves it, notifies the member and returns the output lines. It has no console input/output.
* Indirection: `SubscriptionRepository` and `PushNotifier` interfaces.
* Pure Fabrication: `ConsoleCommandParser`, `InMemorySubscriptionRepository`, `DevicePushNotifier`, `PlanCatalog` do not exist in the business domain and keep entities free of infrastructure duties.

## Consequences
* Positive: the controller can be tested without console, database or push service; storage and notification can be replaced without touching the controller.
* Negative / compromises: more classes than the legacy single method; `Main` is the composition root and wires four collaborators.
