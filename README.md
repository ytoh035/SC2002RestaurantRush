# SC2002 Restaurant Rush

A turn-based restaurant management game in Java (command line), built in three stages for SC2002.
Lab group SCED, Group 4: Toh Yu Chen, Lee Geng Yun, Sharma Siddhanth, Aman Shajee

*Note: The team has internal agreement (confirmed by TA) to use separate folders instead of tags for stage delineation.*

## Submission & Project Links
* **Repository URL:** https://github.com/ytoh035/SC2002RestaurantRush
* **Folder Links:** 
  * [Stage-1 Folder](https://github.com/ytoh035/SC2002RestaurantRush/tree/main/Stage-1/SC2002RestaurantRush)
  * [Stage-2 Folder](https://github.com/ytoh035/SC2002RestaurantRush/tree/main/Stage-2/SC2002RestaurantRush)
  * [Stage-3 Folder](https://github.com/ytoh035/SC2002RestaurantRush/tree/main/Stage-3/SC2002RestaurantRush)

* **AI History Logs:** 
  * [Toh Yu Chen AI Branch / Logs](https://github.com/ytoh035/SC2002RestaurantRush/blob/yuchen/ai-history/stage-3/chat-01.md)
  * [Lee Geng Yun AI Branch / Logs](https://github.com/ytoh035/SC2002RestaurantRush/blob/Lee-Geng-Yun-AI-History/AI%20history/geng_yun%20-%20Google%20Gemini.pdf)
  * [Sharma Siddhanth AI Branch / Logs](https://github.com/ytoh035/SC2002RestaurantRush/blob/Sharma-Siddhanth-ai-history/ai-history/stage-3/chat-01.md) 
  * [Aman Shajee AI Branch / Logs](https://github.com/ytoh035/SC2002RestaurantRush/blob/aman-shajee-ai-history/ai-history/stage-3/chat-01.md)

* **Video Presentation Link:** [Watch Video Presentation](https://youtu.be/hMqCSqS3nNs)

## Stage comparisons

Each stage README lists every file added, changed or deleted in the transition, with the reason:

- **Stage 1 to Stage 2:** 4 classes added, 5 modified, 20 of 25 unchanged (the engine, staff and `Order` did not change). Details: "Stage 1 to Stage 2 changes" in the [Stage 2 README](Stage-2/SC2002RestaurantRush/README.md).
  Commits: [copy of Stage 1 to start Stage 2](https://github.com/ytoh035/SC2002RestaurantRush/commit/610f30d975ecd40e3182980590e9ffc6f6a7f97f) ·
  [VIP and Critic, Menu](https://github.com/ytoh035/SC2002RestaurantRush/commit/44faaf233f16dba459b6a1c7f4f96f7e7f6b4a4a) ·
  [CustomerFactory](https://github.com/ytoh035/SC2002RestaurantRush/commit/e7ddbce63039531a2def16ef6d2eb084ca2c1369) ·
  [GameConfig](https://github.com/ytoh035/SC2002RestaurantRush/commit/f83958ec879320fc571b6e6eba5fd62d5745429a) ·
  [ArrivalSchedule](https://github.com/ytoh035/SC2002RestaurantRush/commit/ae43a77042630ff9dd97d430149f14c4e35b832f) ·
  [GameConfig and ArrivalSchedule wiring](https://github.com/ytoh035/SC2002RestaurantRush/commit/8be537d67a72179233973901e07e4d5047295242) ·
  [RestaurantCLI](https://github.com/ytoh035/SC2002RestaurantRush/commit/dd9d9226f30fe4053ca3f7fc6793c0a0a3226790)
- **Stage 2 to Stage 3:** 3 classes added, 8 modified, 21 of 29 unchanged (the customer subclasses, `CustomerFactory`, `Chef` and `Cashier` did not change). Details: "Stage 2 to Stage 3 changes" in the [Stage 3 README](Stage-3/SC2002RestaurantRush/README.md).
  Commit: [Stage 3 folder uploaded after integration](https://github.com/ytoh035/SC2002RestaurantRush/commit/ea2207148fbd12600748e2ba6cdbf352f370c18c) (built and tested together, then committed as one batch).

To see every line that changed between two stages, from the repository root:

    git diff --no-index Stage-1/SC2002RestaurantRush/src Stage-2/SC2002RestaurantRush/src
    git diff --no-index Stage-2/SC2002RestaurantRush/src Stage-3/SC2002RestaurantRush/src

---

## Video presentation timestamps

Video: https://youtu.be/hMqCSqS3nNs

Items marked [R1] to [R7] are the brief's 7 required demonstrations.

| Time | Segment |
|------|---------|
| 0:00 | 1. Game overview and the three stages |
| 0:50 | [R1] The game launching from the submitted Stage 3 project (VS Code, F5) |
| 1:27 | 2. Gameplay demonstration and key outcomes |
| 1:48 | [R3] A dish going READY -> SERVED (turn 2); payment collected at the end of turn 4 (3:02) |
| 2:12 | [R4][R5] VIP arrives and the manager switches on Happy Hour (turn 4): VIP Burger $10.80 |
| 3:16 | [R5] Stage 3 combo: customer 3 orders the Salad + Pasta Combo (turn 7) |
| 3:32 | [R2] Invalid input and an order that is not READY are refused (turn 8) |
| 4:00 | [R2] Manager choice with staff constrained: Host seats the Critic while the Waiter serves (turn 10) |
| 4:12 | [R4] Critic orders the most expensive single dish, Pasta (turn 11) |
| 4:28 | [R6] One victory and one defeat (replayed from the test files) |
| 4:45 | [R7] 3. Class diagrams and evidence of stage changes |
| 7:20 | [R7] 3.5 Change map (classes added / modified / unchanged) and design principles |
| 8:31 | 4. Selected use case and sequence diagram (a VIP orders during Happy Hour) |
| 12:01 | 5. Individual contributions and key learning |


---

## Stages

The repository holds all three stages side by side. The sections below describe Stage 3 (folder `Stage-3`), the final version.

| Folder | What it contains |
|-----|------------------|
| `Stage-1/` | Regular customers, Salad / Burger / Pasta, Waiter, Chef, Cashier, tables, orders, waiting queue, the six step turn, victory and defeat. |
| `Stage-2/` | Stage 1 plus VIP and Critic customers. |
| `Stage-3/` | Stage 2 plus the Host, ComboMeal and Happy Hour. |

## How to run

Each stage is a separate project. Open **one stage folder at a time** (the folder that contains `src` and `tests`):
all three stages use the same class names, so opening the whole repository at once makes VS Code show false red errors.

**VS Code (easiest).** Install the *Extension Pack for Java*. Then **File > Open Folder** > e.g.
`Stage-3/SC2002RestaurantRush`, open `src/app/Main.java`, press **F5**, and type your choices in the **Terminal** panel.

**Terminal** (needed to replay the test files), from inside a stage folder. Requires JDK 11 or newer.

Windows PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out app.Main
Get-Content tests\victory.txt | java -cp out app.Main
Get-Content tests\defeat.txt  | java -cp out app.Main
```

macOS / Linux:

```bash
javac -d out $(find src -name "*.java")
java -cp out app.Main
java -cp out app.Main < tests/victory.txt
java -cp out app.Main < tests/defeat.txt
```

The game reads one choice per line, so every run can be replayed from the input files in `tests/`.

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

## Diagrams

| Diagram | Files |
|---------|-------|
| Stage 1 class diagram | [stage1classdiagram.png](Stage-1/SC2002RestaurantRush/docs/stage1classdiagram.png) |
| Stage 2 class diagram | [stage2classdiagram.png](Stage-2/SC2002RestaurantRush/docs/stage2classdiagram.png) |
| Stage 3 class diagram | [stage3classdiagram.png](Stage-3/SC2002RestaurantRush/docs/stage3classdiagram.png) |
| Stage 3 sequence diagram (a VIP orders during Happy Hour) | [`Stage-3/SC2002RestaurantRush/docs/`](Stage-3/SC2002RestaurantRush/docs) (`stage3-sequence-diagram.drawio`, `.png`) |

The `.drawio` files open in [diagrams.net](https://app.diagrams.net).

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

## Tests

| Stage | Run | Paid | Revenue | Avg satisfaction | Result |
|-------|-----|------|---------|------------------|--------|
| 1 | `tests/victory.txt` | 5 | $66.00 | 79.2 | VICTORY |
| 1 | `tests/defeat.txt` | 0 | $0.00 | N/A | DEFEAT |
| 2 | `tests/victory.txt` | 5 | $73.50 | 75.6 | VICTORY |
| 2 | `tests/defeat.txt` | 0 | $0.00 | N/A | DEFEAT |
| 2 | `regular` option with `tests/stage1-victory.txt` (Stage 1 regression) | 5 | $66.00 | 79.2 | VICTORY |
| 3 | `tests/victory.txt` | 5 | $77.10 | 83.2 | VICTORY |
| 3 | `tests/defeat.txt` | 0 | $0.00 | N/A | DEFEAT |


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

**In numbers:** 4 classes added, 5 modified, 20 of Stage 1's 25 classes unchanged. This section describes the `Stage-2` folder; see that folder's README for the Stage 2 test notes.

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

## Stage 2 to Stage 3 changes

**In numbers:** 3 classes added, 8 modified, 21 of Stage 2's 29 classes unchanged (`GameConfig` only had a comment corrected, so it counts as unchanged). Both versions are kept side by side in the `Stage-2` and `Stage-3` folders.

**New files**

| File | Responsibility |
|------|----------------|
| `src/staff/Host.java` | Seats at most one queued customer per turn, in addition to the Waiter. |
| `src/domain/ComboMeal.java` | A `MenuItem` made of two existing items: units added, price 90% of the total. |
| `src/domain/HappyHour.java` | Once per game, two turns, 20% off new orders, 15 satisfaction recovery while active. |
| `docs/class-diagram-stage3.*` | Stage 3 class diagram (final design). |
| `docs/stage3-sequence-diagram.*` | Stage 3 sequence diagram (a VIP orders during Happy Hour). |


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
| `src/core/GameConfig.java` | Comment on `regularOnly()` only (no code change). | With combos it no longer recreates Stage 1 exactly, so the comment was corrected. |
| `tests/victory.txt`, `tests/defeat.txt` | New inputs with the Happy Hour and Host answers. | New prompts mean the Stage 2 inputs no longer fit. |
| `tests/stage1-victory.txt` | Deleted. | It was a Stage 2 regression input; it no longer fits the Stage 3 prompts. The `Stage-1` and `Stage-2` folders keep their own test files. |
| `README.md` | Stage 3 rules, test notes and this section. | Documentation. |

**Not changed:** `RegularCustomer`, `VIPCustomer`, `CriticCustomer`, `CustomerFactory`, `MenuItem`,
`Table`, `WaitingQueue`, `Chef`, `Cashier`, `Staff`, `GameStats`, `ArrivalSchedule`. The
Chef and Cashier handle combos and Happy Hour prices without any change, because a combo is a
`MenuItem` and the price is already inside the `Order`.

**What still works:** the turn order, READY then SERVED, eating, payment, abandonment, VIP and Critic
behaviour and the win check are the same code as in Stage 2. Both Stage 3 runs go through all of them,
and the VIP and Critic still lose 5 and 12 per turn and the Critic still orders Pasta.
