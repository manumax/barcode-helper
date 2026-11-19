---
status: accepted
---

# Nothing stored; field samples are photographed manually

The app runs fully on-device with no network, and stores nothing: no scan history, no saved frames, no product lookup, no diagnostics mode. To build a test set, the owner photographs Damaged Barcodes in shops with the normal Camera app (both Scans that worked and ones that failed) and writes down the true number. Those photos become fixtures that are replayed through the real recognizer in the simulator or emulator.

## Considered Options

- **In-app diagnostics switch** that saves the analysed frames and what was read for each Scan. Rejected for now to keep v1 simple.

## Consequences

- A photo taken with the Camera app can differ from what the app actually saw (focus, exposure, framing), so a fixture may not reproduce a live failure exactly. Revisit with in-app frame capture if tuning stalls.
