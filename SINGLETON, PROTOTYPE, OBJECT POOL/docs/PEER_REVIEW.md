# Peer Review: Lab 9, Variant 3 "Parcel+"

Scope: source code in `src/main/java/parcelplus`, diagrams in `docs/diagrams`, runner output of `parcelplus.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?

## Task 1. Daily delivery routes (Prototype)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Fields, methods, visibility and the composition `Route 1 *-- 1..* Stop` correspond to `Route.java` and `Stop.java`. `Main ..> Stop` is shown because the runner creates the extra stop. |
| Problem removed | Yes | `copy(date)` creates a new stops list, new `Stop` objects and a new restrictions map. Changes to one route never reach the reference or other days. |
| Runner reproduces scenario | Yes | Reference built once (expensive log entry, 500 ms), 3 routes copied with no rebuilding, one route gets a new courier, a new stop and a changed weight limit. Reference and the modified route are printed, the reference is unchanged. |

Findings:

- Minor, not fixed: the reference route has the date `reference` and the courier `unassigned` as plain strings. Acceptable for the scope of the lab, a real model would use `LocalDate` and a nullable courier.
- Minor, not fixed: `Stop` has only final fields, so its `copy()` is technically not needed for isolation today. It is kept so that the deep copy stays correct if `Stop` becomes mutable.

## Task 2. Service vans (Object Pool)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `VanPool o-- 3 Van`, package-private members (`~`) of `Van` match the code, the queue and the free deque are shown as fields of `VanPool`. |
| Problem removed | Yes | Exactly 3 `Van` objects are ever created (statistics: total vans created 3). A returned van is reset (courier and parcels cleared) and handed out again without the preparation log entry. |
| Runner reproduces scenario | Yes | 5 couriers request vans, 3 get F-1, F-2, F-3, 2 are queued. F-1 is returned and goes straight to the next courier with no parcels from the previous trip. Statistics are printed at the end. |

Findings:

- Fixed, medium: `release()` could be called twice for the same van and would put it into the free deque twice, so two couriers could receive the same van. A guard was added: a van without a courier is already in the pool, the call is ignored and logged.
- Fixed, minor: `Van.getParcels()` returned the internal list, so callers could change the cargo bypassing the pool. It now returns an unmodifiable view.
- Design decision for the defense: waiting couriers are served in a FIFO queue. It is fair, easy to explain and needs no extra classes. The alternative (postponing shipments to the next departure) would require shipment scheduling that is outside the scope of the situation.

## Task 3. Waybill numbering (Singleton)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Private constructor, private static `instance`, public static `getInstance()`, `nextNumber()` and `getId()` are shown as in `WaybillService.java`. |
| Problem removed | Yes | The only way to get the service is `getInstance()`. `getInstance()` and `nextNumber()` are synchronized, so numbers stay unique under concurrent access. |
| Runner reproduces scenario | Yes | Branch #1, Branch #2 and Online cabinet register 3 shipments each. The log has one initialization message, 9 distinct sequential numbers (1000001 to 1000009) and one distinct service id. |

Findings:

- No defects found.
- Note for the defense: reflection and serialization can break a classic Singleton. An `enum` Singleton would avoid this, the class form was kept because it is the textbook form and the requirements do not mention these attacks.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`, time is simulated.
- The code contains no comments, UI, database or third-party libraries, as required.
- Only the classes needed to model the situations exist: `Route`, `Stop`, `Van`, `VanPool`, `WaybillService`, plus `Log` and the runner `Main`.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the others |
|---|---|---|
| Daily routes: expensive to build, almost identical, shared nested data got modified | Prototype | The goal is to get a new independent object from an existing one without rebuilding. A pool would share objects, a Singleton would give one route for all days. |
| Service vans: limited number, expensive preparation, reused after reset | Object Pool | The goal is to cap the number of objects and reuse them. Prototype would create new vans, a Singleton would give only one van. |
| Waybill numbers: duplicates because several services existed | Singleton | The goal is exactly one shared instance with global access. Prototype and Pool are about many objects. |
