# SC2002 Restaurant Rush Project Collaboration

*User prompt: I'm Aman, and I'm working on the SC2002 Restaurant Rush video demonstration. I'll be handling Segment 1 (game overview), sharing Segment 2 (gameplay demonstration) with Siddhanth, and doing my individual reflection in Segment 5. How should I structure my parts so they fit smoothly into our group's 15-minute video?*

Response: Treat your three parts as one thread: Segment 1 sets up the rules, your half of Segment 2 shows those same rules in action, and Segment 5 comes back to what you built behind them. I don’t have your script’s exact timings, so the times below are suggestions to fit against what the group agreed.

### Segment 1: Game overview (about 1:30 to 2:00)
1.  **Premise, in one or two sentences.** You are the manager of a turn based restaurant, directing staff each turn while customers lose patience.
2.  **How you win or lose.** State the actual thresholds (turns, paid customers, revenue, average satisfaction) so viewers can judge the gameplay that follows.
3.  **The three stages, one sentence each.** Say what each stage adds, such as the VIP and Critic in Stage 2 and the Host in Stage 3. Keep the design detail for the later segments.
4.  **Handover.** End with a line that points at the screen, something like “Let’s see how that plays out from turn 1.”

Show the class diagram or title screen here, not code, so it doesn’t overlap with the design segments.

### Segment 2: Turns 1 to 4 (about 2 minutes of a 4 minute segment)
1.  **Record one continuous run with Siddhanth.** Use the same test input file and one screen recording, then split the narration at turn 4. Two separate recordings will show mismatched state at the cut.
2.  **Narrate decisions, not the screen.** For each turn, say what you tell the Waiter and Chef and why, then point out one consequence, such as a satisfaction drop or an order moving to ready.
3.  **Use the early turns to teach the loop.** Turns 1 to 4 are arrivals, seating and first orders. Go slowly on turn 1 and speed up by turn 3, so Siddhanth can go straight to the interesting events.
4.  **Finish on an open state.** For example, “We have two tables waiting on food and a VIP in the queue, over to Siddhanth.” His opening line should pick up that exact state.

### Segment 5: Individual reflection (about 45 to 60 seconds)
Three beats work well here:

1.  **What you owned.** The README, the test inputs, and assembling Stage 3 with the shared `seatCustomer()` method.
2.  **One specific problem.** The Stage 1 input breaking at turn 13 on Stage 2 is your strongest story because it is concrete.
3.  **What you would do differently.** Treat test files like code.

Read your own contribution lines here, not the ones in the Word script that describe the game loop and CLI work.

### Making it fit the full video
- **Timing:** If Segments 3 and 4 cover design and stage features, your parts total about 4.5 to 5 minutes. Rehearse with a timer and cut to about 10 seconds under your allocation, because recorded speech usually runs long.
- **Wording:** Agree on terms with the group (for example “satisfaction” versus “patience”) and use the same ones in all three of your parts.

If you paste the group’s segment timings or the current script, I can tighten your lines to the exact second counts.

------------------------------------------------------------------------

*User prompt: For Segment 1, I was thinking of starting with a quick introduction to Restaurant Rush, then showing our GitHub repository and explaining the three stages. I don't want to go into too much technical detail since the other members will cover architecture later. Does this approach make sense?*

Response: Yes, that order works, and leaving architecture to the others is the right call. Three adjustments will make it land better.

1.  **Add the win and lose conditions to the introduction.** Without them, viewers watching Segment 2 can’t tell whether a turn is going well. One sentence covers it: how many turns, and the thresholds for paid customers, revenue and average satisfaction.
2.  **Keep the GitHub part short and purposeful, about 20 to 30 seconds.** Show the repo landing page with the three stage folders and the tags, and scroll once past the README. Avoid opening source files, since that drifts into the architecture segments.
3.  **Explain each stage by what the player experiences, not what the code does.** For example, “Stage 2 adds VIP and Critic customers who are harder to please” rather than naming classes. One sentence per stage is enough.

A rough timing for a 1:30 to 2:00 segment would be 30 seconds on the premise and win conditions, 30 seconds on the repo, 30 to 45 seconds on the three stages, then a single line handing over to the gameplay.

Since you wrote the README, you can say so in passing while it is on screen. It sets up your Segment 5 reflection without spending extra time there.

------------------------------------------------------------------------

*User prompt: Can you draft a natural spoken script for my Segment 1? Include what I should show on screen while introducing the game, the repository structure and the three stages.*

Response: This runs about 1 minute 45 seconds at a normal speaking pace (roughly 260 words). The numbers come from your Stage 1 README, so check them against the final Stage 3 build before recording.

### Part 1: Introduction (about 35 seconds)
**On screen:** The game’s title screen in the terminal, then the first turn display showing the queue, tables and revenue line.

**Say:**  
“Hi, I’m Aman, and this is Restaurant Rush, our SC2002 project. It’s a turn based restaurant management game written in Java. You play the manager. Customers arrive over 18 turns, and every turn you decide what your Waiter and your Chef should do, while the Cashier handles payments on its own. The catch is that customers lose satisfaction the longer they wait, and if it hits zero, they walk out. To win, you need at least 4 paying customers, at least 45 dollars in revenue, and an average satisfaction of 60 or above.”

### Part 2: Repository (about 25 seconds)
**On screen:** The GitHub repo landing page. Hover over the three stage folders, open the tags dropdown to show stage 1, stage 2 and stage 3, then scroll slowly past the README down to the class diagram.

**Say:**  
“Everything lives in this GitHub repository. We built the game in three stages, each with its own folder and its own tag, so you can check out the game exactly as it was at any stage. Each stage has its source code, test input files for a victory run and a defeat run, and a README that explains how to compile and play.”

### Part 3: The three stages (about 35 seconds)
**On screen:** Open each stage folder in turn as you mention it, or show three short terminal clips, one per stage.

**Say:**  
“Stage 1 is the core game: regular customers, a three item menu, and the full turn sequence from seating to payment. Stage 2 makes it harder by adding two new customer types, the VIP and the Critic, who are more demanding than regular customers. Stage 3 adds a Host to the staff, who takes over seating, which frees up your Waiter for orders and serving.”

### Part 4: Handover (about 10 seconds)
**On screen:** Back to the terminal at turn 1 of the run you and Siddhanth recorded.

**Say:**  
“My teammates will walk through the design behind all of this later. First, let’s see how a game actually plays out, starting from turn 1.”

### Check before recording
- **Stage 2 and 3 descriptions:** I worded these from what I know of your project, and I’m least sure about Stage 3. If it also adds something like Happy Hour, give it one extra sentence.
- **Win thresholds:** If they change between stages, say “in the base game” before the numbers.
- **Segment 2 opening:** The handover line assumes you narrate turn 1 straight after, so you can drop a second self introduction there.

------------------------------------------------------------------------

*User prompt: For Segment 2, Siddhanth and I will share the gameplay demonstration. How should we divide Stage 1, Stage 2 and Stage 3 between us to avoid repeating information?*

Response: Split it by what is new at each stage: you take Stage 1 and teach the core loop once, and Siddhanth takes Stages 2 and 3 and shows only what each one adds. Each mechanic is then explained once, by whoever reaches it first.

|              | **You**                                                                    | **Siddhanth**                                                                             |
|--------------|----------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| **Covers**   | Stage 1: the core loop                                                     | Stage 2 and Stage 3: the additions, plus the ending                                       |
| **Shows**    | Turns 1 to 4: arrival, seating, taking an order, cooking, serving, payment | A VIP and a Critic in play, then the Host seating customers, then the final result screen |
| **Explains** | How a turn works and how satisfaction drops                                | Only what behaves differently from Stage 1                                                |
| **Time**     | About 2 minutes                                                            | About 2 minutes                                                                           |

This matches the turns 1 to 4 narration you already have, and it follows on from your Segment 1.

**To avoid repeating each other:**

1.  **You follow one customer from start to finish.** Take a single regular customer from the queue to payment, so the whole loop is on screen once. Siddhanth can then say “same loop as before” and skip it.
2.  **Siddhanth opens each stage with the difference.** For example, “In Stage 2, two new customer types arrive,” then straight to a turn where the VIP or Critic matters. He doesn’t need to show seating or ordering again unless the new feature changes it.
3.  **Only one of you shows an ending.** Give Siddhanth the victory screen. If there is time, a five second clip of the defeat run is enough to prove both outcomes exist.
4.  **Agree on the handover state.** You end with something like “That’s the core game. Siddhanth will show how Stages 2 and 3 raise the difficulty.”

**One alternative:** if the group would rather demo a single continuous run of the Stage 3 build, keep the same split by turns. You narrate the early turns, where only core mechanics appear, and Siddhanth takes over from the turn where the first VIP or Critic arrives. The catch is that the Host seats customers from turn 1 in Stage 3, so you would have to mention it briefly, which blurs the split.

You wrote the Stage 2 test inputs, so you know which turns show the VIP and Critic best. Passing those turn numbers to Siddhanth will save him from hunting for them.

------------------------------------------------------------------------

*User prompt: I want my gameplay section to focus more on actually demonstrating how the game works instead of explaining the code. Can you help me write a script where I describe what I'm doing in the terminal, the staff actions, customer interactions and the resulting outcomes?*

Response: I ran your Stage 1 victory input from the group repo, so every number and message below matches what the terminal will show if you record with `tests/victory.txt`. I extended your part from turn 4 to turn 5, because that is when Customer 1 pays and the first revenue appears. The script runs about 2 minutes.

### Opening (10 seconds)
**On screen:** Turn 1 display, before typing anything.

**Say:** “This is Stage 1. Each turn I get the state of the restaurant up top, and then I give one instruction to the Waiter and one to the Chef.”

### Turn 1: Seating
**Type:** Waiter `1`, customer `1`. Chef `2`.

**Say:** “Customer 1 has just arrived and is in the queue with full satisfaction, 100. Both tables are free, so I tell the Waiter to seat them. Customer 1 goes to table 1. There are no orders yet, so the Chef waits.”

### Turn 2: Ordering and cooking
**Type:** Waiter `2`, customer `1`. Chef `1`, order `1`.

**Say:** “Notice the satisfaction has already dropped to 92. Every turn a customer spends waiting costs 8 points, so speed matters. I send the Waiter to take the order, and Customer 1 asks for a Salad. A Salad needs one unit of prep, so I put the Chef on it straight away and it’s ready the same turn.”

### Turn 3: Serving
**Type:** Waiter `3`, order `1`. Chef `2`.

**Say:** “Satisfaction is down to 84, but the order shows as ready with a bill of 9 dollars. I tell the Waiter to serve it. The summary now shows one customer served.”

### Turn 4: A second customer
**Type:** Waiter `1`, customer `2`. Chef `2`.

**Say:** “Customer 1 is eating, and their satisfaction has stopped falling at 84 because the food arrived. Meanwhile Customer 2 has joined the queue, so I seat them at table 2. At the end of the turn the game tells me Customer 1 has finished eating and is ready to pay.”

### Turn 5: Payment
**Type:** Waiter `2`, customer `2`. Chef `1`, order `2`.

**Say:** “I don’t need to do anything about the payment, because the Cashier works automatically. So I use my Waiter on Customer 2, who orders a Burger, and the Chef cooks it. At the end of the turn the Cashier collects 9 dollars from Customer 1, their satisfaction of 84 is recorded, and table 1 is free again. That’s our first paying customer.”

### Handover (10 seconds)
**On screen:** End of turn 5 summary showing revenue of \$9.00.

**Say:** “That’s the full cycle: seat, order, cook, serve, eat and pay. Repeat that well for 18 turns and you win. Siddhanth will now show how Stages 2 and 3 make that harder.”

### Recording tips
- **Pause before typing on each turn.** Leave the state on screen for about two seconds so viewers can read it before the output scrolls.
- **Point with the cursor.** Highlight the satisfaction value in turns 2 and 3 and the cashier line in turn 5.
- **If the group insists on turns 1 to 4,** end on the “ready to pay” line and say “next turn the Cashier collects automatically.”

------------------------------------------------------------------------

*User prompt: Our game has three stages. Stage 1 introduces basic restaurant management, Stage 2 adds VIP and Critic customers, and Stage 3 introduces Host, ComboMeal and Happy Hour. How can I explain these differences naturally while showing the actual gameplay?*

Response: Give each new feature one moment on screen, tied to a number the viewer can see change. Your three victory runs make this easy, because the same customers appear in every stage with different outcomes. I ran all three from the repo, and these are the moments worth showing.

### Stage 1: the baseline (turns 1 to 5)
This is the script you already have. The numbers to plant here are Customer 1 losing 8 satisfaction per waiting turn, being served on turn 3, and paying \$9.00 on turn 5 with a satisfaction of 84. Everything after is compared against these.

### Stage 2: same loop, different customers
| **Moment**                 | **What the terminal shows**                                                        | **What to say**                                                                                                                                                                            |
|----------------------------|------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Turns 4 to 6, the VIP      | C2 is labelled VIP, drops 100, 95, 90, and the Burger bill reads \$13.50           | “The VIP is more patient, losing 5 points a turn, not 8, and gets 10 percent off, so the 15 dollar Burger costs 13.50.”                                                                    |
| Turns 10 to 14, the Critic | C4 is labelled Critic, drops 100, 88, 76, 64, 52, and orders Pasta at 2 prep units | “The Critic is the opposite. They lose 12 points a turn and always order the most expensive dish, the Pasta, which takes the Chef two turns. By the time it’s served, they’re down to 52.” |

Jump straight to turn 4 and turn 10 with a cut. The viewer doesn’t need to watch the regular customers again.

### Stage 3: new tools for the manager
| **Moment**                | **What the terminal shows**                                                                                                   | **What to say**                                                                                                                                                                 |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Turn 1, the Host          | A new Host prompt before the Waiter; C1 is seated and has their order taken in the same turn                                  | “In Stage 1 this took two turns. With the Host seating, the Waiter is free to take the order immediately, so Customer 1 is served a turn earlier and pays with 92, up from 84.” |
| Turns 4 and 5, Happy Hour | The Happy Hour question is answered yes; the VIP’s Burger bill reads \$10.80, and their satisfaction is back at 100 on turn 5 | “Happy Hour can be used once per game and lasts two turns. Orders get 20 percent off, on top of the VIP discount, and waiting customers recover 15 satisfaction.”               |
| Turn 7, the ComboMeal     | C3 orders the Salad + Pasta Combo, \$24.30, 3 prep units                                                                      | “Customer 3 orders a combo, two dishes at 10 percent off. It’s our biggest bill, but it ties up the Chef for three turns.”                                                      |

### Making it sound natural
1.  **Compare, don’t list.** Use phrases like “Remember Customer 1 paid with 84 in Stage 1?” The viewer then sees the feature working without you describing the rule in the abstract.
2.  **Name the trade-off.** Each feature has one: the VIP pays less but waits better, the Critic pays well but is risky, and the combo earns more but blocks the Chef.
3.  **End on the final screens.** The victory totals rise from \$66.00 to \$73.50 to \$77.10 across the stages, and the Critic’s recorded satisfaction improves from 52 in Stage 2 to 64 in Stage 3. Showing the three result screens side by side for five seconds sums up the progression.

Under the split we discussed, the Stage 2 and 3 moments are Siddhanth’s to narrate, so you can pass him this list of turns directly.

------------------------------------------------------------------------

*User prompt: I also want to demonstrate how the game handles invalid staff actions and how different decisions affect the outcome. How can I incorporate those demonstrations into my part without making it too long?*

Response: Fold both into moments you are already showing, so they add about 30 seconds in total. I tested the Stage 1 build to confirm the exact messages below.

### Invalid actions: put them in turn 1 (adds about 15 seconds)
Customer 1 is still in the queue on turn 1, so a wrong move is easy to stage before your real one. Two mistakes are enough: one that breaks a game rule and one that is bad input.

| **You type**              | **Terminal responds**                                             | **What to say**                                                                                       |
|---------------------------|-------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| Waiter `2`, customer `1 ` | “Customer 1 is not seated, or has already ordered. Choose again.” | “If I try to take an order from someone who’s still in the queue, the game refuses and explains why.” |
| `abc `                    | “‘abc’ is not a number.”                                          | “Bad input is caught too, and nothing crashes.”                                                       |
| Waiter `1`, customer `1 ` | “Customer 1 seated at table 1.”                                   | “And importantly, those mistakes didn’t cost me my turn. I can still seat Customer 1.”                |

The last line matters most: a failed action doesn’t use up the staff member’s task. Say it once and move on. Showing more error cases adds length without adding anything new.

The victory input file doesn’t contain these extra keystrokes, so type turn 1 by hand when recording, or add the two lines to a copy of the file.

### Decisions and outcomes: one contrast cut (adds about 15 seconds)
Your defeat run already shows the same restaurant with the opposite decisions. Place this right after turn 5, when Customer 1 has just paid.

**On screen:** Cut to the defeat run at the end of turn 13, where the line “Customer 1 leaves unhappy without paying. Order cancelled, table freed.” appears. Then cut to its final screen: 0 paid customers, \$0.00, DEFEAT.

**Say:** “That was Customer 1 when I acted quickly: served by turn 3 and paid 9 dollars. Here is the same customer in a game where the Waiter and Chef just wait. Satisfaction falls 8 points a turn until it hits zero on turn 13, and they walk out without paying. Same customer, same restaurant, and the only difference is my decisions.”

### Keeping your part at about 2:30
1.  **Trim turns 3 and 4.** By then the viewer knows the loop, so one sentence each is enough.
2.  **Speed up the typing.** Play routine keystrokes at double speed in editing and keep normal speed only for the error messages and the payment line.
3.  **Leave the victory screen to Siddhanth.** You show the defeat ending, he shows the victory ending, and neither of you repeats the other.

If the group wants your part held to 2 minutes, keep the invalid action demo and shorten the contrast cut to the single “leaves unhappy” line with one sentence of narration.

------------------------------------------------------------------------

*User prompt: Can you now combine my gameplay explanations into a word-for-word script with screen actions such as \[Run Stage 3\], \[Select staff action\], \[Show Happy Hour discount\] and \[Display final result\]?*

Response: This script covers the whole gameplay segment in one continuous flow, about 4 minutes 30 seconds, with every number taken from running your three builds. I marked a suggested speaker for each part, so change the labels if you and Siddhanth split it differently.

### Part A: Stage 1, the core loop (Aman, about 1:50)
**\[Run Stage 1:** **`java -cp out app.Main`, typed by hand\]**  
**\[Show turn 1 state: queue, tables, staff tasks\]**

“This is Stage 1. Each turn I see the state of the restaurant, then I give one instruction to the Waiter and one to the Chef. Customer 1 has just arrived with full satisfaction, 100.”

**\[Select Waiter action:** **`2 `Take order, customer** **`1`\]**  
**\[Show error: “Customer 1 is not seated, or has already ordered.”\]**

“If I try to take an order from someone still in the queue, the game refuses and tells me why.”

**\[Type** **`abc`\]**  
**\[Show error: “‘abc’ is not a number.”\]**

“Bad input is caught too, and neither mistake costs me my turn.”

**\[Select Waiter action:** **`1 `Seat, customer** **`1`. Chef:** **`2` Wait\]**

“So I seat Customer 1 at table 1. No orders yet, so the Chef waits.”

**\[Turn 2. Highlight sat=92\]**  
**\[Select Waiter action:** **`2 `Take order, customer** **`1`. Chef:** **`1 `Prepare, order** **`1`\]**

“Satisfaction is already down to 92. Every turn spent waiting costs 8 points. The Waiter takes the order, a Salad, and the Chef prepares it the same turn.”

**\[Turn 3. Select Waiter action:** **`3 `Serve, order** **`1`. Chef:** **`2`\]**

“The Salad is ready, so the Waiter serves it. Customer 1 stops losing satisfaction at 84.”

**\[Turn 4. Select Waiter action:** **`1 `Seat, customer** **`2`. Chef:** **`2`\]**

“While Customer 1 eats, Customer 2 arrives and I seat them at table 2.”

**\[Turn 5. Select Waiter action:** **`2`, customer** **`2`. Chef:** **`1`, order** **`2`\]**  
**\[Highlight: “Cashier collects \$9.00 from Customer 1 (satisfaction 84 recorded)”\]**

“The Cashier works automatically. At the end of turn 5 it collects 9 dollars from Customer 1 and the table is free again. Seat, order, cook, serve, eat, pay. That’s the whole loop.”

### Part B: Decisions change the outcome (Aman, about 0:20)
**\[Cut to Stage 1 defeat run, end of turn 13\]**  
**\[Highlight: “Customer 1 leaves unhappy without paying.”\]**

“Here is the same customer in a game where the staff do nothing. Satisfaction falls to zero by turn 13 and they walk out.”

**\[Display final result: 0 paid, \$0.00, DEFEAT\]**

“Same restaurant, different decisions, and the game ends in defeat.”

### Part C: Stage 2, new customer types (Siddhanth, about 0:45)
**\[Run Stage 2. Cut to turn 6\]**  
**\[Highlight: C2 VIP, sat=90, Burger bill \$13.50\]**

“Stage 2 keeps the same loop but changes who walks in. Customer 2 is now a VIP. They are more patient, losing 5 points a turn where a regular loses 8, and they get 10 percent off, so the 15 dollar Burger costs 13.50.”

**\[Cut to turn 14\]**  
**\[Highlight: C4 Critic, sat=52, Pasta 2/2 units, bill \$18.00\]**

“Customer 4 is a Critic, and they are the opposite. They lose 12 points a turn and always order the most expensive dish, the Pasta, which takes the Chef two turns. By the time it’s ready, they’re down to 52. A Critic can pull the average below the target of 60 if I’m slow.”

### Part D: Stage 3, new tools for the manager (Siddhanth, about 1:15)
**\[Run Stage 3:** **`java -cp out app.Main < tests/victory.txt`\]**  
**\[Show turn 1: Happy Hour line and the new Host prompt\]**  
**\[Select Host action: Seat customer 1. Waiter: Take order. Chef: Prepare\]**

“Stage 3 gives the manager new tools. First, the Host. The Host seats Customer 1, which frees the Waiter to take the order in the same turn. In Stage 1 that took two turns.”

**\[Cut to turn 4. Select Happy Hour:** **`1` Switch on now\]**  
**\[Show message: “Happy Hour is ON for this turn and the next (20% off new orders)”\]**

“Second, Happy Hour. I can switch it on once per game, and it lasts two turns. I use it just as the VIP arrives.”

**\[Turn 5. Show Happy Hour discount: Burger bill \$10.80 (Happy Hour price)\]**  
**\[Highlight: “Happy Hour: Customer 2 recovers 95 -\> 100”\]**

“The VIP’s Burger drops to 10.80, because the 20 percent comes off on top of the VIP discount. Waiting customers also recover 15 satisfaction, so the VIP is back to 100.”

**\[Cut to turn 7. Highlight: “Customer 3 orders Salad + Pasta Combo (Order \#3, 3 prep unit(s))”\]**

“Third, combo meals. Customer 3 orders a Salad and Pasta combo at 10 percent off, 24.30. It’s the biggest bill in the game, but it keeps the Chef busy for three turns, so I have to plan around it.”

### Part E: The result (Siddhanth, about 0:20)
**\[Display final result: 5 paid, \$77.10, average satisfaction 83.2, VICTORY\]**

“After 18 turns we have 5 paying customers, 77 dollars 10 in revenue and an average satisfaction of 83.2, which beats all three targets. In Stage 2 the Critic paid with a satisfaction of 52. Here, with the Host, it’s 64. That’s the difference the new features make.”

### Notes for recording
- **Stage 1 input:** Part A must be typed by hand, or run from a copy of the victory file with the two invalid entries added, because the original file doesn’t contain them.
- **Cuts:** Every “Cut to” is a jump in editing. Record each run in full once and trim afterwards.
- **Speaker change:** At the end of Part B, add a line such as “Siddhanth will now show how Stages 2 and 3 change the game.”

I can turn this into a document you can share with the group if that’s useful.

------------------------------------------------------------------------

*User prompt: For the Stage 3 demonstration, I want to highlight the Host seating customers, Happy Hour activation, the VIP discount calculation and invalid action handling. Can you help me decide the best order to demonstrate these features?*

Response: Show them in the order the game produces them: Host, invalid action, Happy Hour, then the VIP discount. All four fit into turns 1, 4 and 5 of a single run, so you need no jumps back and each feature sets up the next.

| **Order** | **Feature**              | **Turn**               | **Why it goes here**                                                                 |
|-----------|--------------------------|------------------------|--------------------------------------------------------------------------------------|
| 1         | Host seating             | Turn 1                 | It is the first new prompt on screen, so viewers will wonder about it immediately.   |
| 2         | Invalid action           | Turn 1, straight after | The Host has just created the perfect setup for one.                                 |
| 3         | Happy Hour activation    | Turn 4                 | The VIP arrives this turn, so switching it on now sets up the discount.              |
| 4         | VIP discount calculation | Turn 5                 | The bill appears with both discounts applied, which is the payoff for steps 1 and 3. |

**1. Host seating (turn 1).** Seat Customer 1 with the Host, then have the Waiter take the order in the same turn. The point to make is that one turn now does what took two in Stage 1.

**2. Invalid action (turn 1).** Before the Waiter takes the order, tell the Waiter to seat Customer 1 as well. I tested this, and the game replies “Customer 1 is not in the waiting queue. Choose again.” This is a better error to show than a typo because it only exists in Stage 3, where two staff can seat. It also lets you say the Waiter keeps their turn and can take the order after all.

**3. Happy Hour activation (turn 4).** Answer `1` at the Happy Hour prompt as the VIP joins the queue. The message confirms it is on for this turn and the next with 20 percent off new orders. Mention that it can be used only once per game, which is why the timing matters.

**4. VIP discount calculation (turn 5).** The VIP’s Burger shows as “\$10.80 (Happy Hour price)”. Walk through it aloud: 15 dollars, less 10 percent for the VIP, is 13.50, and less 20 percent for Happy Hour is 10.80. A text overlay with that sum helps here. The same screen shows the VIP recovering from 95 to 100, which is Happy Hour’s second effect.

**Why not open with the invalid action.** An error message before viewers have seen the Host work correctly has no context. Putting it after the Host’s success takes five seconds and makes sense immediately.

The whole sequence runs about 1 minute 15 seconds if you cut from the end of turn 1 to the start of turn 4. The victory input file doesn’t include the invalid entry, so type turn 1 by hand or add that one line to a copy of the file.

------------------------------------------------------------------------

*User prompt: Here's my revised gameplay script: Segment 1 – Game Overview and the Three Stages  
“Hi, we’re Group 4. This is Restaurant Rush, an 18-turn command-line restaurant game in Java. The player is the manager. You win by ending with at least 4 paid customers, at least \$45 revenue and an average paid satisfaction of at least 60.  
We built it in three stages. Stage 1 is the core game: Regular customers, a Waiter, a Chef, an automatic Cashier and two tables. Stage 2 adds VIP and Critic customers. Stage 3 adds a Host, two combo meals and Happy Hour. Each stage is a complete, runnable copy in its own folder.  
Here’s the game launching from the submitted Stage 3 project.  
At the top is the menu, including the two combos. Each turn shows revenue, paid customers, Happy Hour status, the queue, both tables, every customer’s state and satisfaction, and which tasks each staff member can do this turn.”  
Segment 2 – Gameplay Demonstration (Turns 1–4)  
“Turn 1. Customer 1, a Regular, arrives. We don’t need Happy Hour yet \[2\]. The Host seats customer 1 \[1, 1\]. Because the Host acts first, the Waiter can take the order in the same turn \[2, 1\]: Salad. It’s one prep unit, so the Chef makes it READY straight away \[1, 1\].  
Turn 2. No Happy Hour, nobody new to seat \[2, 2\]. The Waiter serves order 1 \[3, 1\]: READY to SERVED, and customer 1 is now eating. Nothing left for the Chef \[2\].  
Turn 3 is quiet \[2, 2\]: nothing to serve \[4\], nothing to cook \[2\]. After a full turn of eating, customer 1 becomes ready to pay.  
Turn 4. A VIP, customer 2, arrives. We switch Happy Hour on now, before anyone acts \[1\]. It lasts this turn and the next: 20% off new orders. The Host seats the VIP at table 2 \[1, 2\]. The Waiter takes the VIP’s order \[2, 2\]: a \$15 Burger. 10% VIP discount, then 20% Happy Hour, so the bill is \$10.80, locked now. The Chef cooks it \[1, 2\], and it’s READY. At the end of the turn, the Cashier collects \$9 from customer 1 automatically.”  
Segment 5 – Individual Contributions and Key Learning  
“I owned the game loop and the CLI, which only prints and reads; no rule lives there. My challenge was timing Happy Hour. My first version only gave recovery in startTurn(), but the manager switches it on after that has run, so customers recovered once instead of twice. Now activateHappyHour() gives the first recovery. My lesson: for any rule spanning turns, write out what happens at each turn’s start, middle and end before coding. Thanks for watching.”. Can you check whether it flows naturally, explains the outcomes clearly and avoids repeating what Siddhanth will cover?*

Response: Segments 1 and 2 are accurate and nearly ready: I checked every keystroke and figure against the Stage 3 victory run and they all match. Segment 5 is the one that has to change, because it describes work the repo history credits to someone else.

### Segment 5: rewrite before recording
The script says you owned the game loop, the CLI and the Happy Hour timing fix. When we went through the repo earlier today, those commits (`RestaurantCLI`,` Main`,` GameConfig`,` ArrivalSchedule`) were Lee Geng Yun’s, and the group’s contribution table gave that speech to him. Segment 5 is an individual reflection, so a marker who compares it with the commit history will see the mismatch, and Geng Yun will be left without his own material.

Your own contribution has an equally concrete story:

“I wrote the README and the test inputs, and assembled the Stage 3 folder, including the shared `seatCustomer()` method that the Host and Waiter both call. My challenge came in Stage 2. Our Stage 1 input file broke at turn 13, because the Critic’s Pasta wasn’t ready, so every answer after that landed on the wrong prompt. My lesson: treat test files like code, and rerun them whenever the rules change. Thanks for watching.”

If the game loop work really is yours and the history is misleading, tell me and we can keep your version.

### Segment 2: add the outcomes
The narration explains what each staff member does but never mentions satisfaction, which is one of the three win conditions you name in Segment 1. Three small additions fix that:

1.  **End of turn 1:** “Customer 1 is still waiting for food, so they lose 8 satisfaction and drop to 92.”
2.  **Turn 2, after serving:** “Once served, satisfaction is locked, so customer 1 will pay with 92.”
3.  **End of turn 4:** “That’s our first paid customer: 9 dollars in revenue, satisfaction 92 recorded.”

### Flow
- **State names:** “READY to SERVED” reads well but sounds abrupt aloud. Say “the order moves from ready to served.”
- **Turn 3:** It is a quiet turn, so play it at double speed and keep only the last sentence.
- **Segment 1, last paragraph:** The list of eight things on the turn display is hard to follow by ear. Name three (satisfaction, tables, available staff tasks) and point at the rest with the cursor.
- **“Average paid satisfaction”:** “an average satisfaction of at least 60 among paying customers” is easier to say.
- **Keystroke brackets:** Keep them as cues for yourself and don’t read them out.

### Overlap with Siddhanth
Your turn 4 already covers Happy Hour activation and the full VIP discount sum, and his turn 5 shows the “\$10.80 (Happy Hour price)” label on screen. Agree on this split:

| **Topic**  | **You (turns 1 to 4)**                                    | **Siddhanth (turn 5 onwards)**                         |
|------------|-----------------------------------------------------------|--------------------------------------------------------|
| Happy Hour | Switching it on, the 20 percent discount, the \$10.80 sum | The recovery from 95 to 100, and Happy Hour ending     |
| VIP        | The 10 percent discount                                   | Losing 5 satisfaction per turn where a Regular loses 8 |
| Combos     | Only pointing at them on the menu                         | Customer 3’s combo order on turn 7                     |
| Critic     | Nothing                                                   | All of it, from turn 10                                |

He should not recalculate the \$10.80, only point at it and move on.

The invalid action demo you wanted earlier isn’t in this version. If you still want it, turn 1 is the place: have the Waiter try to seat customer 1 after the Host already has, which costs about ten seconds.

------------------------------------------------------------------------

*User prompt: Can you combine all my parts—Segment 1, my portion of Segment 2, and my Segment 5 reflection—into one coherent script? Keep them separate since we'll record each segment individually.*

Response: Here is the full script as three standalone segments, each opening and closing on its own so they can be recorded separately. Bracketed text is a screen action or keystroke cue and is not read aloud.

### Segment 1: Game Overview and the Three Stages (about 1:10)
**\[Show title slide or the repo page with the three stage folders\]**

“Hi, we’re Group 4. This is Restaurant Rush, an 18 turn command line restaurant game written in Java. The player is the manager. You win by ending with at least 4 paid customers, at least 45 dollars in revenue, and an average satisfaction of at least 60 among paying customers.

We built it in three stages. Stage 1 is the core game: Regular customers, a Waiter, a Chef, an automatic Cashier and two tables. Stage 2 adds VIP and Critic customers. Stage 3 adds a Host, two combo meals and Happy Hour. Each stage is a complete, runnable copy in its own folder.”

**\[Run Stage 3:** **`java -cp out app.Main`\]**

“Here’s the game launching from the submitted Stage 3 project.”

**\[Point at the menu, then the turn display\]**

“At the top is the menu, including the two combos. Each turn then shows the state of the restaurant: every customer’s satisfaction, both tables, and which tasks each staff member can do this turn. Let’s play.”

### Segment 2: Gameplay Demonstration, Turns 1 to 4 (about 1:45)
**\[Turn 1 on screen\]**

“Turn 1. Customer 1, a Regular, arrives. We don’t need Happy Hour yet. **\[2\]** The Host seats customer 1. **\[1, 1\]** Because the Host acts first, the Waiter can take the order in the same turn. **\[2, 1\]** It’s a Salad, one prep unit, so the Chef has it ready straight away. **\[1, 1\]** Customer 1 is still waiting for food at the end of the turn, so they lose 8 satisfaction and drop to 92.”

**\[Turn 2\]**

“Turn 2. No Happy Hour, and nobody new to seat. **\[2, 2\]** The Waiter serves order 1. **\[3, 1\]** The order moves from ready to served, and customer 1 is now eating. Once served, their satisfaction is locked at 92. Nothing left for the Chef. **\[2\]**”

**\[Turn 3, at double speed\]**

“Turn 3 is quiet. **\[2, 2, 4, 2\]** After a full turn of eating, customer 1 becomes ready to pay.”

**\[Turn 4\]**

“Turn 4. A VIP, customer 2, arrives. We switch Happy Hour on now, before anyone acts. **\[1\]** It lasts this turn and the next, with 20 percent off new orders. The Host seats the VIP at table 2. **\[1, 2\]** The Waiter takes the VIP’s order, a 15 dollar Burger. **\[2, 2\]** That’s 10 percent off for the VIP, then 20 percent off for Happy Hour, so the bill is 10 dollars 80, locked in now. The Chef cooks it. **\[1, 2\]**”

**\[Highlight: “Cashier collects \$9.00 from Customer 1 (satisfaction 92 recorded)”\]**

“At the end of the turn, the Cashier collects 9 dollars from customer 1 automatically. That’s our first paid customer, with a satisfaction of 92 recorded. Siddhanth will take it from turn 5.”

### Segment 5: Individual Contribution and Key Learning (about 0:40)
**\[Show the README, then the tests folder\]**

“I’m Aman. I wrote the README and the test inputs, and I assembled the Stage 3 folder, including the shared seatCustomer method that the Host and the Waiter both call.

My challenge came in Stage 2. Our Stage 1 input file broke at turn 13, because the Critic’s Pasta wasn’t ready, so every answer after that landed on the wrong prompt. I rewrote the victory and defeat inputs for the new customer line up.

My lesson is to treat test files like code: rerun them whenever the rules change, and check that what’s on GitHub is what you tested. Thanks for watching.”

### Notes
- **Segment 5:** I used your own contributions here, as discussed. Swap the first paragraph back only if the game loop and CLI work is yours.
- **Handover:** Segment 2 ends with Happy Hour on and the VIP’s Burger ready, so Siddhanth’s first line should start from that state.
- **Total time:** Your three parts come to about 3 minutes 35 seconds.

------------------------------------------------------------------------

*User prompt: can you compile the entire script together*

Response: Here are your three parts compiled into one clean script, ready to copy. Keystroke and screen cues are in square brackets.

    RESTAURANT RUSH: AMAN'S SCRIPT (GROUP 4) 
     
    SEGMENT 1: GAME OVERVIEW AND THE THREE STAGES 
     
    [Show title slide or the repo page with the three stage folders] 
     
    Hi, we're Group 4. This is Restaurant Rush, an 18 turn command line 
    restaurant game written in Java. The player is the manager. You win by 
    ending with at least 4 paid customers, at least 45 dollars in revenue, 
    and an average satisfaction of at least 60 among paying customers. 
     
    We built it in three stages. Stage 1 is the core game: Regular 
    customers, a Waiter, a Chef, an automatic Cashier and two tables. 
    Stage 2 adds VIP and Critic customers. Stage 3 adds a Host, two combo 
    meals and Happy Hour. Each stage is a complete, runnable copy in its 
    own folder. 
     
    [Run Stage 3: java -cp out app.Main] 
     
    Here's the game launching from the submitted Stage 3 project. 
     
    [Point at the menu, then the turn display] 
     
    At the top is the menu, including the two combos. Each turn then shows 
    the state of the restaurant: every customer's satisfaction, both 
    tables, and which tasks each staff member can do this turn. Let's play. 
     
     
    SEGMENT 2: GAMEPLAY DEMONSTRATION, TURNS 1 TO 4 
     
    [Turn 1 on screen] 
     
    Turn 1. Customer 1, a Regular, arrives. We don't need Happy Hour yet. 
    [2] The Host seats customer 1. [1, 1] Because the Host acts first, the 
    Waiter can take the order in the same turn. [2, 1] It's a Salad, one 
    prep unit, so the Chef has it ready straight away. [1, 1] Customer 1 is 
    still waiting for food at the end of the turn, so they lose 8 
    satisfaction and drop to 92. 
     
    [Turn 2] 
     
    Turn 2. No Happy Hour, and nobody new to seat. [2, 2] The Waiter serves 
    order 1. [3, 1] The order moves from ready to served, and customer 1 is 
    now eating. Once served, their satisfaction is locked at 92. Nothing 
    left for the Chef. [2] 
     
    [Turn 3, at double speed] 
     
    Turn 3 is quiet. [2, 2, 4, 2] After a full turn of eating, customer 1 
    becomes ready to pay. 
     
    [Turn 4] 
     
    Turn 4. A VIP, customer 2, arrives. We switch Happy Hour on now, before 
    anyone acts. [1] It lasts this turn and the next, with 20 percent off 
    new orders. The Host seats the VIP at table 2. [1, 2] The Waiter takes 
    the VIP's order, a 15 dollar Burger. [2, 2] That's 10 percent off for 
    the VIP, then 20 percent off for Happy Hour, so the bill is 10 dollars 
    80, locked in now. The Chef cooks it. [1, 2] 
     
    [Highlight: Cashier collects $9.00 from Customer 1 (satisfaction 92 
    recorded)] 
     
    At the end of the turn, the Cashier collects 9 dollars from customer 1 
    automatically. That's our first paid customer, with a satisfaction of 
    92 recorded. Siddhanth will take it from turn 5. 
     
     
    SEGMENT 5: INDIVIDUAL CONTRIBUTION AND KEY LEARNING 
     
    [Show the README, then the tests folder] 
     
    I'm Aman. I wrote the README and the test inputs, and I assembled the 
    Stage 3 folder, including the shared seatCustomer method that the Host 
    and the Waiter both call. 
     
    My challenge came in Stage 2. Our Stage 1 input file broke at turn 13, 
    because the Critic's Pasta wasn't ready, so every answer after that 
    landed on the wrong prompt. I rewrote the victory and defeat inputs for 
    the new customer line up. 
     
    My lesson is to treat test files like code: rerun them whenever the 
    rules change, and check that what's on GitHub is what you tested. 
    Thanks for watching. 

This covers only your parts, since I don’t have your teammates’ lines for Segments 3 and 4, the rest of Segment 2, or their reflections. If you meant the whole group’s 15 minute script, paste or attach their sections and I’ll merge everything into one document.
