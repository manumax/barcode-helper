# Barcode Helper: v1 spec

Terms in **bold** are defined in [GLOSSARY.md](../GLOSSARY.md). Reasoning for the key decisions is in [docs/adr](./adr).

## Purpose

In a shop, a **Damaged Barcode** won't scan at checkout. The user points the phone at it, the app works out the true number from the **Printed Number**, and shows a **Rendered Barcode** the **Shop Scanner** can read.

## In scope

- **EAN-13** only (Italian grocery goods; UPC-A is EAN-13 with a leading 0). EAN-8, Code 128, QR and others are out: they end in **No Guess**.
- iPhone first (iOS 17+, portrait only). Android follows with the same shared code.
- On-device only. English-only UI, with strings in resource files so Italian can be added later.
- App name "Barcode Helper"; bundle ID and Android package `it.manumax78.barcodehelper`.

## Out of scope

History, saved lists, sharing, product lookup, networking, confidence levels or ranked alternatives, an in-app diagnostics mode, App Store distribution.

## Behaviour

1. The camera runs continuously from launch. There is no shutter button.
2. Fast path: if the phone decodes the **Bars** into a **Valid Number** (a **Decoded Barcode**), it is shown immediately as the **Guess**.
3. Otherwise each camera frame yields a **Reading** of the **Printed Number**. Readings are combined into **Candidates** following [ADR-0003](./adr/0003-strict-binary-outcome.md): unique, valid, at most one **Repair**, at least 3 Readings in **Agreement**.
4. After about 10 seconds without a **Guess**, the Outcome is **No Guess**.
5. The **Result Screen** is full screen and never auto-dismisses. The user taps or swipes down to dismiss it, and a new **Scan** starts immediately.

## Screens

- **Scanning:** live camera, a guide frame ("put the number inside the box") that also limits the recognition area, and a torch button.
- **Guess:** large black-on-white **Rendered Barcode** with the 13 digits underneath. Screen brightness is forced to maximum while shown and restored on dismiss. A haptic tap (no sound) on appearance.
- **No Guess:** a short message, "Can't guess this barcode". Haptic tap as well.
- **Camera permission denied:** a clear message with an "Open Settings" button.

## Architecture

```
shared (Kotlin Multiplatform, Compose Multiplatform)
├── Ean13           check digit, validity, bar encoding
├── Resolver        Readings -> Candidates (one Repair, uniqueness)
├── ScanState       Agreement, timeout, restart after dismiss
└── UI              Rendered Barcode drawing, Result Screens, scanning chrome
platform (small interface: frame in -> decoded bars + text candidates out)
├── iOS     AVFoundation + Vision
└── Android CameraX + ML Kit (built second)
```

## Testing

- Shared logic is unit-tested on the JVM, test-first (check digit, encoding, Repair, uniqueness, Agreement, timeout).
- The owner photographs Damaged Barcodes in shops, successes and failures, with the true number noted ([ADR-0004](./adr/0004-nothing-stored-field-samples-taken-manually.md)). The photos become fixtures replayed through the real recognizer in the iOS simulator and Android emulator.
- "v1 done": works end to end on the owner's iPhone in a real shop, with zero wrong Guesses across the field samples.

## Build order

1. Scaffold the KMP project and `git init`; it builds on the iOS simulator and the Android emulator.
2. Shared logic, test-first.
3. Rendered Barcode and the Result Screens.
4. iOS camera and Vision recognition, then the first run on the iPhone.
5. Field test, collect fixtures, tune.
6. Android camera and ML Kit.

## Environment (checked 2026-11-19)

- Xcode 27.2. Android SDK, `adb` and Android Studio are installed. No Android device was connected.
- The Mac has a free Apple "Personal Team" and a work company team. The app will use **another** personal team, whose ID the owner supplies at step 4. A free team means the iPhone install expires every 7 days and must be re-run from Xcode.
- The iOS simulator has no camera, so recognition is verified on a physical iPhone or from photo fixtures.

## Open items

- **Team ID:** supplied by the owner at build-order step 4.
- **Android phone:** assumed none for now, so Android is verified only on the emulator with photo fixtures.
