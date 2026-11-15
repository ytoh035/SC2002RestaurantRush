# SC2002 Restaurant Rush – AI Collaboration Log

## Part 1: Structuring Segment 2 (Gameplay Demonstration & Key Outcomes)

**User Prompt:**

For our SC2002 video demo, I am covering the second half of Segment 2 (turns 5 to 11 of our Stage 3 victory run, plus the final victory and defeat outcomes at the end). We want to show Happy Hour recovery, a combo meal order, bad input rejection, staff constraint enforcement, and the Critic ordering Pasta. How should I pace the talking points so they match the exact key presses on screen without dragging?

**Response:**

Since you're demonstrating the actual gameplay, I think the best approach is to follow the turn sequence rather than explaining each feature separately. This will allow the narration to match what is happening on screen and make the demonstration easier to follow.

I would break it down from Turn 5 to Turn 11, highlighting the main feature or action at each point. We should also include the key presses directly in the talking points so that you know exactly when to explain each action.

Here's a suggested breakdown:

| Turn / Step | Talking Points & Key Presses |
|---|---|
| **Turn 5** | Explain the start-of-turn Happy Hour recovery (+15 satisfaction, bringing the VIP from 95 back to 100). Serve the VIP's Burger `[3, 2]`, skip the Chef `[2]`, and mention that Happy Hour expires after this turn. |
| **Turn 6** | Point out that the Happy Hour prompt no longer appears because it can only be used once per game. Pass all actions `[2, 4, 2]` while the VIP finishes eating. |
| **Turn 7** | Seat Customer 3 `[1, 3]`, take their combo order (Salad + Pasta, $24.30, 3 preparation units) `[2, 3]`, and start the first preparation unit `[1, 3]`. Highlight the Cashier automatically collecting $10.80 at 100 satisfaction. |
| **Turn 8** | Demonstrate validation and constraints. Enter non-numeric input `[abc]` to show rejection. Attempt to serve the unready combo `[3, 3]` while it is only 1/3 prepared. Explain that the action is rejected and the Waiter keeps the task. Wait `[4]` while the Chef advances unit 2 `[1, 3]`. |
| **Turn 9** | Host and Waiter wait `[2, 4]` while the Chef completes unit 3 `[1, 3]`, marking the combo as READY. |
| **Turn 10** | Highlight the one-task-per-turn constraint when the Critic arrives. The Host seats the Critic `[1, 4]`, allowing the Waiter to serve Customer 3's combo `[3, 3]` before further patience is lost. |
| **Turn 11** | The Host waits `[2]`. The Critic orders Pasta `[2, 4]` — the most expensive single dish rather than a combo — and the Chef starts cooking `[1, 4]` before transitioning to the end-of-game results. |

For the final part, you can briefly compare the two terminal outcomes:

Victory: Five paid customers, $77.10 revenue, average satisfaction of 83.2, and a victory result.
Defeat: When the manager waits every turn, customers eventually lose patience and leave, resulting in defeat.

I would keep each explanation fairly short and let the gameplay itself demonstrate the functionality. This should make the segment feel more like a walkthrough rather than a lengthy explanation of the code.

**User Prompt:**

This looks good. I think the important thing is to make sure the key presses and explanations happen at the right time, especially for the invalid serving attempt and the Critic arriving. I also want to avoid explaining things that are already obvious from the screen.

**Response:**

Agreed. For those parts, focus on explaining the reasoning behind the action rather than simply describing what the screen shows.

For example, when the combo is not ready, explain why the Waiter cannot serve it yet and that an unsuccessful action does not consume the staff member's task. When the Critic arrives, highlight why the Host needs to seat them first while the Waiter handles the already-prepared combo.

The rest can remain brief. This way, you cover the important functionality without slowing down the demonstration.

---

## Part 2: Drafting my individual contribution and key learning

**User Prompt:**

For my individual contribution section, I worked mainly on the Customer, Order and Restaurant classes. I want to explain how making Customer abstract from Stage 1 helped us introduce VIPCustomer and CriticCustomer in Stage 2 without having to change the original customer structure.

One challenge I faced was in Stage 3 when we introduced ComboMeal. Initially, I had to consider how combos should be stored in the Menu. If I placed them in the same list as regular dishes, the getMostExpensive() method could end up selecting the $24.30 combo instead of the most expensive single dish. This would affect the Critic's behaviour because the Critic is supposed to order the most expensive single dish. We eventually kept combos in a separate list.

I need to explain my contributions and one challenge I faced in around 30 seconds. How should I structure this reflection?

**Response:**

I think you have a good example to use because it shows both your implementation work and a design decision you had to make when introducing a new feature.

I would structure your reflection around three points:

- **Your contribution:** Mention that you worked on Customer, Order and Restaurant.
- **Design evolution:** Explain how making Customer abstract from Stage 1 made it easier to introduce VIP and Critic subclasses in Stage 2.
- **Challenge and learning:** Explain the problem with storing ComboMeals alongside regular dishes and how separating the lists preserved the Critic's original behaviour.

I would spend slightly more time on the Stage 3 challenge because it demonstrates that you considered how a new feature could affect existing functionality.

**User Prompt:**

I think the combo issue is probably the most important part because it shows that adding a new feature isn't like straightforward. Should I explain exactly what would have happened if both types of items were stored in the same list?

**Response:**

Yes, I would include that because it makes your design decision much clearer.

You can explain that if ComboMeals were stored alongside regular dishes, getMostExpensive() might select the $24.30 combo instead of the most expensive individual dish. This would unintentionally change the Critic's ordering behaviour.

Then explain that keeping combos in a separate list avoids this issue and allows the existing rule to remain intact.

For the learning takeaway, you could connect this to the importance of checking how a new feature might affect existing classes and their behaviour.

**User Prompt:**

I want the reflection to sound natural and not too technical, but I still want to mention the actual method and the reason we used a separate list. Can you help me bring everything together without making it too long?

**Response:**

I would keep the explanation conversational and focus on the reasoning behind your implementation. You don't need to explain the entire class structure; just mention how the initial abstraction helped in Stage 2 and how you protected the existing Critic behaviour in Stage 3.

Here are the key points to cover:

- You worked on the Customer, Order and Restaurant classes.
- Making Customer abstract from Stage 1 allowed VIP and Critic to be introduced as subclasses in Stage 2.
- In Stage 3, ComboMeals needed a separate list to prevent getMostExpensive() from selecting a combo instead of a single dish.
- Your main takeaway is to identify and test existing rules that could be affected before modifying shared classes.

This should fit comfortably into approximately 30 seconds while showing both your technical contribution and what you learned from the project.

**Final spoken reflection:**

"I wrote the customers, orders and the restaurant. Because Customer was abstract from Stage 1, VIP and Critic in Stage 2 were just two subclasses. My challenge came in Stage 3: if the combos had gone into the same list as the dishes, getMostExpensive() would have picked the $24.30 combo, and the Critic would silently start ordering combos. So combos have their own list. My lesson: before changing a class others depend on, list the rules it could break and test each one."
