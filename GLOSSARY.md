# Barcode Helper

A phone app for shoppers that works out the true number of a badly printed grocery barcode from the number printed beneath it, then shows a clean barcode a shop scanner can read.

## Language

### The barcode

**Barcode**:
A retail EAN-13 symbol on a product: the Bars with the Printed Number beneath them.
_Avoid_: Code, label, tag

**Bars**:
The machine-readable stripe pattern of a Barcode.
_Avoid_: Lines, stripes

**Printed Number**:
The 13 human-readable digits printed beneath the Bars.
_Avoid_: Digits text, caption, label number

**Check Digit**:
The 13th digit of a Barcode number, computed from the first twelve, which lets any 13-digit number be tested for validity.
_Avoid_: Checksum digit, control digit

**Valid Number**:
A 13-digit number whose Check Digit is correct.
_Avoid_: Correct number, good number

**Damaged Barcode**:
A Barcode whose Bars a Shop Scanner cannot read because of poor printing, while its Printed Number is still legible.
_Avoid_: Bad barcode, broken barcode, unreadable barcode

**Shop Scanner**:
The checkout or handheld scanner in a shop that fails on a Damaged Barcode and is later shown the Rendered Barcode.
_Avoid_: Reader, till scanner

### Guessing

**Scan**:
One continuous attempt, from the camera becoming active until an Outcome is produced.
_Avoid_: Session, run, attempt

**Reading**:
One attempt at perceiving the Printed Number, which may be incomplete or wrong.
_Avoid_: OCR output, recognition result

**Doubtful Digit**:
A digit in a Reading that could plausibly be a different digit.
_Avoid_: Uncertain digit, unreliable digit

**Repair**:
Replacing a single Doubtful Digit with the one value that turns the number into a Valid Number.
_Avoid_: Correction, fix, auto-correct

**Candidate**:
A Valid Number consistent with the Readings so far, possibly after one Repair.
_Avoid_: Possibility, option, hypothesis

**Agreement**:
Separate Readings producing the same Candidate.
_Avoid_: Consensus, voting

**Decoded Barcode**:
A Valid Number read directly from the Bars by the phone, without relying on the Printed Number.
_Avoid_: Bar read, direct scan

### Outcome

**Guess**:
The single Candidate (or Decoded Barcode) the app presents as the true number of the Barcode.
_Avoid_: Prediction, suggestion, match, result

**No Guess**:
The Outcome when the app cannot name exactly one Candidate with enough Agreement before the Scan ends.
_Avoid_: Error, failure, miss

**Outcome**:
How a Scan ends: either a Guess or No Guess. Never a ranked list or a confidence level.
_Avoid_: Result (reserve "Result Screen" for the display)

**Result Screen**:
The full-screen display of an Outcome, dismissed by the user, after which a new Scan begins.
_Avoid_: Popup, dialog, modal

**Rendered Barcode**:
The clean EAN-13 the app draws for a Guess, intended to be read by a Shop Scanner.
_Avoid_: Generated barcode, fixed barcode
