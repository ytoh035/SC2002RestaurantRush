# SC2002 Restaurant Rush

A turn based restaurant management game written in Java for SC2002 Object Oriented Design and Programming.
This version is Stage 2 (tag `stage-2`). It keeps everything from Stage 1 (tag `stage-1`): Regular
customers, the three menu items, Waiter, Chef, Cashier, tables, orders, the waiting queue, the full six
step turn sequence, and both endings. Stage 2 adds VIP and Critic customers.

## Build and run

Requires JDK 11 or newer. No external libraries, no network access.

```
javac -d out $(find src -name "*.java")       # Linux / macOS
javac -d out src\app\*.java src\core\*.java src\domain\*.java src\enums\*.java src\staff\*.java src\ui\*.java src\util\*.java   # Windows
java -cp out app.Main            # Stage 2 game (includes a VIP and a Critic)
java -cp out app.Main regular    # six Regular customers, exactly like Stage 1
```

The game reads the manager's choices from standard input, one number per line, so a whole run can be
replayed from a file: `java -cp out app.Main < tests/victory.txt`.

## Package layout

| Package  | Classes | Responsibility |
|----------|---------|----------------|
| `app`    | `Main` | Wires the objects together and starts the UI. |
| `ui`     | `RestaurantCLI` | Collects decisions and prints state. Contains no game rules. |
| `core`   | `GameEngine`, `GameConfig`, `ArrivalSchedule`, `GameStats`, `TurnSummary`, `ActionResult` | Turn sequencing, configuration, scoring and reporting. |
| `domain` | `Restaurant`, `Table`, `WaitingQueue`, `Customer`, `RegularCustomer`, `VIPCustomer`, `CriticCustomer`, `CustomerFactory`, `Menu`, `MenuItem`, `Order` | Restaurant state and the rules that belong to each object. |
| `staff`  | `Staff`, `Waiter`, `Chef`, `Cashier` | The three roles; each enforces its own one task per turn limit. |
| `enums`  | `CustomerState`, `CustomerType`, `OrderStatus`, `WaiterTask`, `GameResult` | Closed sets of states and choices. |
| `util`   | `Money` | Integer cent arithmetic and dollar formatting. |

## How a turn runs

`RestaurantCLI.run()` loops over 18 turns. Each iteration calls the engine in this order:

1. `GameEngine.startTurn()`: the scheduled customer (turns 1, 4, 7, 10, 13, 16) joins the queue at
   satisfaction 100; staff `actedThisTurn` flags are reset.
2. The UI prints the start of turn state and the tasks currently available.
3. The UI asks for the Waiter's choice and calls `applyWaiterAction(task, id)`, then asks for the Chef's
   choice and calls `applyChefAction(orderId)`. The Waiter always acts before the Chef, so an order that
   becomes READY this turn cannot be served until next turn. A failed action returns an `ActionResult`
   with a reason and does not consume the staff member's task; the UI simply asks again.
4. `GameEngine.finishTurn()`: the Cashier pays out the oldest READY_TO_PAY customer, then customers
   served on an earlier turn become READY_TO_PAY (payment runs first so they pay the following turn),
   then every unserved customer loses satisfaction and anyone at 0 abandons (order cancelled, table
   freed). It returns a `TurnSummary` which the UI prints.
5. After turn 18, `evaluateResult()` returns VICTORY if paid customers >= 4, revenue >= $45.00 and the
   average recorded satisfaction of paid customers >= 60, otherwise DEFEAT.

## Rules decided by me

**Food choice.** Customers choose their own food. `Customer.chooseFood(menu)` uses a deterministic rule:
menu index = (customer id - 1) mod 3, so customers 1 to 6 order Salad, Burger, Pasta, Salad, Burger,
Pasta. The choice is displayed when the order is taken. This is a method on `Customer` so that later
customer types can override it without any type checks in the engine.

**Customer types (Stage 2).**

| Type | Satisfaction loss per waiting turn | Bill | Food choice |
|------|-----------------------------------|------|-------------|
| Regular | 8 | Full price | (id - 1) mod 3 rule above |
| VIP | 5 | 10% off, rounded to the nearest cent | (id - 1) mod 3 rule above |
| Critic | 12 | Full price | Always the most expensive dish on the menu |

**Critic behaviour (our own rule).** A food critic judges the kitchen by its best dish, so
`CriticCustomer` overrides `chooseFood` and asks `Menu.getMostExpensive()` for the highest priced item
(on a tie, the one with more preparation units). With the standard menu this is Pasta, which needs 2
preparation units, so a Critic who already loses 12 per turn needs the Chef's attention early.

**Arrivals (Stage 2).** Still six arrivals on turns 1, 4, 7, 10, 13 and 16. Two regular arrivals are
replaced: customer 2 (turn 4) is a VIP and customer 4 (turn 10) is a Critic. The line-up is set in
`GameConfig`; `CustomerFactory` turns each `CustomerType` into the right subclass.

**Seating.** The manager chooses which queued customer to seat; the Waiter seats them at the lowest
numbered free table. Seating priority is the meaningful decision, table number is not.

**Payment order.** `Restaurant.getReadyToPayOldestFirst()` sorts by the turn the customer became
READY_TO_PAY, then by id.

**Prices.** Stored as integer cents in `MenuItem` and locked into `Order.priceCents` when the order is
taken, via `Customer.applyDiscount` (full price for Regular customers).

## Test notes

Input files are in `tests/`. Each line is one answer to a prompt.

### Victory run: `tests/victory.txt` (Stage 2 line-up)

Run with `java -cp out app.Main < tests/victory.txt`.

Strategy: serve customers in arrival order. On turn 9 the Waiter waits because the Pasta only becomes
READY during the Chef's step. On turn 13 the Waiter seats customer 5 while the Chef finishes the
Critic's Pasta, so the Critic is served on turn 14.

| Customer | Type | Food | Seated | Served | Paid | Bill | Satisfaction recorded |
|----------|------|------|--------|--------|------|------|-----------------------|
| 1 | Regular | Salad  | turn 1  | turn 3  | turn 5  | $9.00  | 84 |
| 2 | VIP     | Burger | turn 4  | turn 6  | turn 8  | $13.50 | 90 |
| 3 | Regular | Pasta  | turn 7  | turn 10 | turn 12 | $18.00 | 76 |
| 4 | Critic  | Pasta  | turn 11 | turn 14 | turn 16 | $18.00 | 52 |
| 5 | Regular | Burger | turn 13 | turn 16 | turn 18 | $15.00 | 76 |
| 6 | Regular | Pasta  | turn 17 | never   | never   | -      | not recorded |

The VIP pays $15.00 x 0.9 = $13.50 and loses only 5 per turn. The Critic orders Pasta although the
normal rule would give customer 4 a Salad, and drops to 52 because of the 12 point loss.

Expected final screen:

```
Paid customers:   5 (target 4)
Revenue:          $73.50 (target $45.00)
Avg satisfaction: 75.6 (target 60)
Served: 5   Unhappy departures: 0
RESULT: VICTORY
```

### Defeat run: `tests/defeat.txt` (Stage 2 line-up)

Strategy: Waiter and Chef wait every turn. Customer 1 leaves at the end of turn 13 and the Critic
(customer 4, arrived turn 10) leaves at the end of turn 18 after only nine turns. The VIP, losing only
5 per turn, never runs out of patience.

Expected final screen:

```
Paid customers:   0 (target 4)
Revenue:          $0.00 (target $45.00)
Avg satisfaction: N/A (target 60)
Served: 0   Unhappy departures: 2
RESULT: DEFEAT
```

### Stage 1 regression check

`java -cp out app.Main regular < tests/stage1-victory.txt` uses six Regular customers and gives exactly
the Stage 1 result: 5 paid, $66.00, average 79.2, VICTORY. `java -cp out app.Main regular <
tests/defeat.txt` gives the Stage 1 defeat. This shows the Stage 1 rules still work unchanged.

### Invalid input

Non numeric input, menu numbers out of range, unknown ids, serving an order that is not READY, and
taking an order from a customer who is not seated all print a reason and prompt again without
consuming the staff task. Example: entering `7`, then `abc`, then choosing Serve on order `5` before
any order exists, then seating customer 1 succeeds on the fourth attempt.

## Design notes

* Rules live in the objects that own the data: `Order.advance()` decides when a dish is READY,
  `Customer.applyWaitingLoss()` decides who loses satisfaction, `Staff` decides whether a role may act.
  `GameEngine` only sequences the steps. `RestaurantCLI` never touches a rule.
* `ActionResult` carries success or a reason back to the UI so that validation and re-prompting need
  no exceptions and no rule knowledge in the UI.
* `TurnSummary` is a read only value object, which keeps `System.out` out of the engine.
* `Customer` is abstract with three hooks (`waitingLoss`, `chooseFood`, `applyDiscount`) and
  `RegularCustomer`, `VIPCustomer` and `CriticCustomer` each override them. `GameEngine`, `Waiter`,
  `Chef`, `Cashier` and `Order` never check which type a customer is; they just call the hooks
  (polymorphism).
* `CustomerFactory` is the only class with a `switch` on `CustomerType`. Keeping creation in one place
  means the turn controller has no type checks, as the brief requires.
* `GameConfig` holds the base setting as one object so a TA can reproduce runs and so that other
  difficulty settings can be added without touching the engine.

## Stage 1 to Stage 2 changes


**New files**

| File | Responsibility |
|------|----------------|
| `src/domain/VIPCustomer.java` | Loses 5 per waiting turn; overrides `applyDiscount` for 10% off. |
| `src/domain/CriticCustomer.java` | Loses 12 per waiting turn; overrides `chooseFood` to order the most expensive dish. |
| `src/domain/CustomerFactory.java` | Creates the right `Customer` subclass for a `CustomerType`. |
| `src/enums/CustomerType.java` | REGULAR, VIP, CRITIC: names the kind of each scheduled arrival. |
| `tests/stage1-victory.txt` | The Stage 1 victory inputs, kept for the regression check. |
| `docs/class-diagram-stage2.drawio` | Stage 2 class diagram. |

**Changed files**

| File | What changed | Why |
|------|--------------|-----|
| `src/core/GameConfig.java` | Added `arrivalTypes`, a second constructor and `regularOnly()`. | The base setting must include one VIP and one Critic; `regularOnly()` recreates Stage 1 for testing. |
| `src/core/ArrivalSchedule.java` | Takes the list of types and asks `CustomerFactory` for each arrival. | Stage 1 always created `RegularCustomer`. |
| `src/domain/Menu.java` | Added `getMostExpensive()`. | The Critic's rule needs it, and finding the dearest dish is the menu's job, not the customer's. |
| `src/app/Main.java` | Passes the types to `ArrivalSchedule`; `regular` argument runs the Stage 1 line-up. | Setup change, plus the regression check. |
| `src/ui/RestaurantCLI.java` | Banner says Stage 2. | Display only. |
| `tests/victory.txt` | New inputs for the Stage 2 line-up. | The arrivals changed, so the Stage 1 inputs no longer fit. |
| `README.md` | This section, customer types, Stage 2 test notes. | Documentation. |

**Not changed:** `Customer`, `RegularCustomer`, `Order`, `Restaurant`, `GameEngine`, `Waiter`, `Chef`,
`Cashier`, `GameStats`. Because `Customer` already had the `waitingLoss`, `chooseFood` and
`applyDiscount` hooks in Stage 1, the new behaviour came from new subclasses instead of new `if` checks
(open/closed principle).
