---
status: accepted
---

# The Printed Number is the evidence, not the Bars

The failure we are solving is a Damaged Barcode: the Shop Scanner cannot read the Bars, but the Printed Number is still legible. So guesses are built from Readings of the Printed Number, and the Bars are not combined with them. The one exception is a shortcut: if the phone happens to decode the Bars into a Valid Number (a Decoded Barcode, since a close-up phone camera can succeed where a Shop Scanner fails), that is presented immediately as the Guess.

## Considered Options

- **Combine partially decoded Bars with the Printed Number.** Rejected for v1: the Bars are the damaged part, partial bar data is hard to use, and it adds a second evidence source to reconcile.

## Consequences

- A Barcode whose Printed Number is also damaged ends in No Guess.
- A wrong Decoded Barcode is possible in principle, since a bar misread passes the Check Digit about one time in ten. This is accepted for the shortcut; revisit if field samples show it happening.
