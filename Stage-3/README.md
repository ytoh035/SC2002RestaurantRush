# SC2002 Restaurant Rush

A turn based restaurant management game written in Java for SC2002 Object Oriented Design and Programming.
This version is Stage 3 (tag `stage-3`), the final version.

| Tag | What it contains |
|-----|------------------|
| `stage-1` | Regular customers, Salad / Burger / Pasta, Waiter, Chef, Cashier, tables, orders, waiting queue, the six step turn, victory and defeat. |
| `stage-2` | Stage 1 plus VIP and Critic customers. |
| `stage-3` | Stage 2 plus the Host, ComboMeal and Happy Hour. |

## Build and run

Requires JDK 11 or newer. No external libraries, no network access.

```
javac -d out $(find src -name "*.java")       # Linux / macOS
javac -d out src\app\*.java src\core\*.java src\domain\*.java src\enums\*.java src\staff\*.java src\ui\*.java src\util\*.java   # Windows
java -cp out app.Main            # base setting (includes a VIP and a Critic)
java -cp out app.Main regular    # optional: six Regular customers instead
```

The game reads the manager's choices from standard input, one number per line, so a whole run can be
replayed from a file: `java -cp out app.Main < tests/victory.txt`.

## Package layout

| Package  | Classes | Responsibility |
|----------|---------|----------------|
| `app`    | `Main` | Wires the objects together and starts the UI. |
| `ui`     | `RestaurantCLI` | Collects decisions and prints state. Contains no game rules. |
| `core`   | `GameEngine`, `GameConfig`, `ArrivalSchedule`, `GameStats`, `TurnSummary`, `ActionResult` | Turn sequencing, configuration, scoring and reporting. |
| `domain` | `Restaurant`, `Table`, `WaitingQueue`, `Customer`, `RegularCustomer`, `VIPCustomer`, `CriticCustomer`, `CustomerFactory`, `Menu`, `MenuItem`, `ComboMeal`, `Order`, `HappyHour` | Restaurant state and the rules that belong to each object. |
| `staff`  | `Staff`, `Host`, `Waiter`, `Chef`, `Cashier` | The four roles; each enforces its own one task per turn limit. |
| `enums`  | `CustomerState`, `CustomerType`, `OrderStatus`, `WaiterTask`, `GameResult` | Closed sets of states and choices. |
| `util`   | `Money` | Integer cent arithmetic and dollar formatting. |

## How a turn runs

`RestaurantCLI.run()` loops over 18 turns. Each iteration calls the engine in this order:

1. `GameEngine.startTurn()`: the scheduled customer (turns 1, 4, 7, 10, 13, 16) joins the queue at
   satisfaction 100, staff `actedThisTurn` flags are reset, and if Happy Hour is already running,
   waiting customers recover 15 satisfaction.
2. The UI prints the start of turn state, including Happy Hour's status and the tasks available.
3. While Happy Hour has not been used, the UI asks whether to switch it on (`activateHappyHour()`).
4. The UI asks for the Host's choice (`applyHostAction(id)`), then the Waiter's
   (`applyWaiterAction(task, id)`), then the Chef's (`applyChefAction(orderId)`). The Host acts first,
   so a customer the Host seats can have their order taken by the Waiter in the same turn. The Waiter
   always acts before the Chef, so an order that becomes READY this turn cannot be served until next
   turn. A failed action returns an `ActionResult` with a reason and does not use up the task.
5. `GameEngine.finishTurn()`: the Cashier pays out the oldest READY_TO_PAY customer, then customers
   served on an earlier turn become READY_TO_PAY, then every unserved customer loses satisfaction and
   anyone at 0 leaves (order cancelled, table freed), then Happy Hour counts down one turn. It returns
   a `TurnSummary` which the UI prints.
6. After turn 18, `evaluateResult()` returns VICTORY if paid customers >= 4, revenue >= $45.00 and the
   average recorded satisfaction of paid customers >= 60, otherwise DEFEAT.

## Rules decided by us

**Food choice.** Customers choose their own food; the manager never does. `Customer.chooseFood(menu)`
uses a deterministic rule:

* every third customer (ids 3, 6, ...) orders a combo: combo number (id / 3 - 1) mod 2, so customer 3
  orders Salad + Pasta Combo and customer 6 orders Salad + Burger Combo;
* everyone else orders menu item (id - 1) mod 3, so customers 1, 2, 4, 5 order Salad, Burger, Salad,
  Burger;
* a Critic overrides this rule (below).

The choice is shown when the order is taken.

**Customer types.**

| Type | Satisfaction loss per waiting turn | Bill | Food choice |
|------|-----------------------------------|------|-------------|
| Regular | 8 | Full price | Rule above |
| VIP | 5 | 10% off, rounded to the nearest cent | Rule above |
| Critic | 12 | Full price | Always the most expensive single dish |

**Critic behaviour (our own rule).** A food critic judges the kitchen by its best dish, so
`CriticCustomer` overrides `chooseFood` and asks `Menu.getMostExpensive()` for the highest priced single
item (on a tie, the one with more preparation units). With the standard menu this is Pasta. A Critic
never orders a combo, because combos are kept in a separate list on the menu.

**Arrivals.** Six arrivals on turns 1, 4, 7, 10, 13 and 16. Customer 2 (turn 4) is a VIP and customer
4 (turn 10) is a Critic; the rest are Regular. The line-up is set in `GameConfig`; `CustomerFactory`
turns each `CustomerType` into the right subclass.

**Combo meals (Stage 3).**

| Combo | Price | Preparation units |
|-------|-------|-------------------|
| Salad + Pasta Combo | ($9.00 + $18.00) x 0.9 = $24.30 | 1 + 2 = 3 |
| Salad + Burger Combo | ($9.00 + $15.00) x 0.9 = $21.60 | 1 + 1 = 2 |

`ComboMeal` extends `MenuItem` and holds its two component items, so every other class treats a combo
like any dish.

**Host (Stage 3).** The Host may seat one queued customer per turn at the lowest numbered free table,
on top of the Waiter's task. The Waiter can still seat customers. Both call `Restaurant.seatCustomer()`.

**Happy Hour (Stage 3).** The manager may switch it on once per game, at the start of a turn. It lasts
that turn and the next; the state display shows how many turns are left. Orders taken while it is
active get 20% off, applied after the VIP discount (VIP Burger: $15.00 x 0.9 x 0.8 = $10.80). The price
is locked inside `Order` when the order is taken, so it never changes orders already placed. At the
start of each active turn, customers still waiting for a table or food recover 15 satisfaction
(maximum 100); customers who have eaten, paid or left are not affected.

**Seating.** The manager chooses which queued customer to seat; the Host or Waiter seats them at the
lowest numbered free table.

**Payment order.** `Restaurant.getReadyToPayOldestFirst()` sorts by the turn the customer became
READY_TO_PAY, then by id.

**Money.** Prices are integer cents. A bill is the list price, then `Customer.applyDiscount`, then
`HappyHour.applyDiscount`, each rounded to the nearest cent.

## Test notes

Input files are in `tests/`. Each line is one answer to a prompt. The Happy Hour question is only asked
while Happy Hour is still unused.

### Victory run: `tests/victory.txt`

Run with `java -cp out app.Main < tests/victory.txt`.

Strategy: the Host seats each customer on arrival and the Waiter takes the order in the same turn.
Happy Hour is switched on at turn 4, just as the VIP orders, so the VIP gets both discounts and
recovers to 100 at the start of turn 5. On turns 10 and 13 the Waiter serves the customer already
waiting for food instead of taking the newcomer's order.

| Customer | Type | Food | Seated | Served | Paid | Bill | Satisfaction recorded |
|----------|------|------|--------|--------|------|------|-----------------------|
| 1 | Regular | Salad | turn 1 (Host) | turn 2 | turn 4 | $9.00 | 92 |
| 2 | VIP | Burger | turn 4 (Host) | turn 5 | turn 7 | $10.80 (VIP + Happy Hour) | 100 |
| 3 | Regular | Salad + Pasta Combo | turn 7 (Host) | turn 10 | turn 12 | $24.30 | 76 |
| 4 | Critic | Pasta | turn 10 (Host) | turn 13 | turn 15 | $18.00 | 64 |
| 5 | Regular | Burger | turn 13 (Host) | turn 15 | turn 17 | $15.00 | 84 |
| 6 | Regular | Salad + Burger Combo | turn 16 (Host) | turn 18 | never | - | not recorded |

Expected final screen:

```
Paid customers:   5 (target 4)
Revenue:          $77.10 (target $45.00)
Avg satisfaction: 83.2 (target 60)
Served: 6   Unhappy departures: 0
RESULT: VICTORY
```

### Defeat run: `tests/defeat.txt`

Strategy: Happy Hour is never switched on and the Host, Waiter and Chef wait every turn. Customer 1
leaves at the end of turn 13 and the Critic (customer 4) leaves at the end of turn 18. The VIP, losing
only 5 per turn, never runs out of patience.

Expected final screen:

```
Paid customers:   0 (target 4)
Revenue:          $0.00 (target $45.00)
Avg satisfaction: N/A (target 60)
Served: 0   Unhappy departures: 2
RESULT: DEFEAT
```

### How the manager's choices change the result

With the victory strategy every customer who can pay does, and Happy Hour turns the VIP's 95 into 100.
Doing nothing gives the defeat run. In between, seating late, cooking the wrong order first, or wasting
Happy Hour on a turn when nobody orders all lower the recorded satisfaction or the number of paid
customers.

### Invalid input

Non numeric input, menu numbers out of range, unknown ids, seating when no table is free, serving an
order that is not READY, and taking an order from a customer who is not seated all print a reason and
prompt again without using up the staff task.

## Design notes

* Rules live in the objects that own the data: `Order` locks the bill and decides when a dish is READY,
  `Customer` decides its own satisfaction loss, food choice and discount, `HappyHour` decides its own
  duration and discount, `Staff` decides whether a role may still act. `GameEngine` only sequences the
  steps. `RestaurantCLI` never touches a rule.
* Polymorphism: `GameEngine`, `Host`, `Waiter`, `Chef`, `Cashier` and `Order` never check which type a
  customer is; they call `waitingLoss`, `chooseFood` and `applyDiscount` and each subclass answers.
  The same goes for menu items: a `ComboMeal` is used everywhere a `MenuItem` is expected.
* Composition: `Restaurant` owns its tables, queue, orders, stats and `HappyHour`; `Menu` owns its
  items and combos; a `ComboMeal` holds its two component items.
* `CustomerFactory` is the only class with a `switch` on `CustomerType`.
* `ActionResult` carries success or a reason back to the UI so that validation and re-prompting need
  no exceptions and no rule knowledge in the UI.

## Stage 1 to Stage 2 changes

Compare link: `<repo-url>/compare/stage-1...stage-2` (replace `<repo-url>` with our GitHub URL).
This section describes the `stage-2` tag; see that tag's README for the Stage 2 test notes.

**New files**

| File | Responsibility |
|------|----------------|
| `src/domain/VIPCustomer.java` | Loses 5 per waiting turn; overrides `applyDiscount` for 10% off. |
| `src/domain/CriticCustomer.java` | Loses 12 per waiting turn; overrides `chooseFood` to order the most expensive dish. |
| `src/domain/CustomerFactory.java` | Creates the right `Customer` subclass for a `CustomerType`. |
| `src/enums/CustomerType.java` | REGULAR, VIP, CRITIC: names the kind of each scheduled arrival. |
| `tests/stage1-victory.txt` | The Stage 1 victory inputs, kept for the regression check. |
| `docs/class-diagram-stage2.drawio` | Stage 2 class diagram. |
| `docs/stage2-sequence-diagram.*` | Stage 2 sequence diagram (VIP from arrival to payment). |

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

## Stage 2 to Stage 3 changes

Compare link: `<repo-url>/compare/stage-2...stage-3` (replace `<repo-url>` with our GitHub URL).

**New files**

| File | Responsibility |
|------|----------------|
| `src/staff/Host.java` | Seats at most one queued customer per turn, in addition to the Waiter. |
| `src/domain/ComboMeal.java` | A `MenuItem` made of two existing items: units added, price 90% of the total. |
| `src/domain/HappyHour.java` | Once per game, two turns, 20% off new orders, 15 satisfaction recovery while active. |
| `docs/class-diagram-stage3.*` | Stage 3 class diagram (final design). |
| `docs/stage3-sequence-diagram.*` | Stage 3 sequence diagram (VIP orders during Happy Hour). |

**Changed files**

| File | What changed | Why |
|------|--------------|-----|
| `src/domain/Menu.java` | Holds two combos in a separate list; `getCombo`, `comboCount`, `getCombos`. | Combos must be built from existing items; keeping them separate means the Critic's "most expensive single dish" rule is unchanged. |
| `src/domain/Customer.java` | `chooseFood` gives every third customer a combo; new `recoverSatisfaction`; `MAX_SATISFACTION` constant. | The brief asks for a documented combo rule; Happy Hour recovery belongs to the customer who owns the satisfaction. |
| `src/domain/Order.java` | Constructor takes the `HappyHour` and applies it after the customer discount; remembers whether the Happy Hour price was used. | The price must be locked when the order is taken, with VIP first and Happy Hour second. |
| `src/domain/Restaurant.java` | Owns a `HappyHour`; passes it to new orders; new `seatCustomer()`. | The Host and Waiter both seat customers, so the seating steps moved here to avoid copying them. |
| `src/staff/Waiter.java` | `seat()` calls `Restaurant.seatCustomer()`. | Same seating rule as the Host, written once. Behaviour is unchanged. |
| `src/core/GameEngine.java` | Has a `Host`; new `applyHostAction`, `hostCanSeat`, `canActivateHappyHour`, `activateHappyHour`; `startTurn` returns messages and applies Happy Hour recovery; `finishTurn` counts Happy Hour down. | Sequencing the new role and the new policy is the controller's job. |
| `src/ui/RestaurantCLI.java` | Prints the menu, Happy Hour status and the Host's task; asks the Happy Hour and Host questions. | Display and input only; no rules. |
| `src/app/Main.java` | Creates the `Host` and passes it to `GameEngine`. | Setup change. |
| `src/core/GameConfig.java` | Comment on `regularOnly()` only. | With combos it no longer recreates Stage 1 exactly, so the comment was corrected. |
| `tests/victory.txt`, `tests/defeat.txt` | New inputs with the Happy Hour and Host answers. | New prompts mean the Stage 2 inputs no longer fit. |
| `tests/stage1-victory.txt` | Deleted. | It was a Stage 2 regression input; it no longer fits the Stage 3 prompts. The `stage-1` and `stage-2` tags keep their own test files. |
| `README.md` | Stage 3 rules, test notes and this section. | Documentation. |

**Not changed:** `RegularCustomer`, `VIPCustomer`, `CriticCustomer`, `CustomerFactory`, `MenuItem`,
`Table`, `WaitingQueue`, `Chef`, `Cashier`, `Staff`, `GameStats`, `ArrivalSchedule`. The
Chef and Cashier handle combos and Happy Hour prices without any change, because a combo is a
`MenuItem` and the price is already inside the `Order`.

**What still works:** the turn order, READY then SERVED, eating, payment, abandonment, VIP and Critic
behaviour and the win check are the same code as in Stage 2. Both Stage 3 runs go through all of them,
and the VIP and Critic still lose 5 and 12 per turn and the Critic still orders Pasta.
