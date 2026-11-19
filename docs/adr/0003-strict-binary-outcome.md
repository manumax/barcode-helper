---
status: accepted
---

# Strict binary Outcome: one unique Guess or No Guess

A wrong barcode shown with confidence is worse than no answer, so an Outcome is only ever a Guess or No Guess. No ranked alternatives and no confidence level in v1. A Guess requires all of:

- exactly one Candidate remains, and it is a Valid Number;
- at most one Repair was applied, and only to a Doubtful Digit;
- the same Candidate comes from at least 3 separate Readings (Agreement);
- no tie-breaking by Italian prefix (80–83), because it would make the rule non-strict and fail on imported goods.

If the Scan runs to its timeout (about 10 seconds) without meeting this, the Outcome is No Guess.

## Why these numbers

- A random misreading passes the Check Digit about 1 time in 10, so a single Reading that "looks valid" would often be a confident wrong answer. Requiring Agreement across 3 Readings makes that rare.
- The Check Digit weights (1 and 3) are both invertible mod 10, so when the position of one unknown digit is known, exactly one value makes the number valid. With two unknown digits ten pairs pass, and the Check Digit can no longer choose. One Repair is the largest budget the Check Digit alone can resolve uniquely.

## Consequences

- Hard barcodes will often end in No Guess; that is intended.
- A confidence meter or ranked alternatives can be added later without changing the Candidate logic, but would be a new product decision.
- "v1 done" is measured as zero wrong Guesses over the field samples; the success rate is secondary.
