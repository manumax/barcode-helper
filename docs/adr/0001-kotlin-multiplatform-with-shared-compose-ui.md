---
status: accepted
---

# Kotlin Multiplatform with shared Compose UI

The app targets iOS and Android, with iOS built and validated first. We use Kotlin Multiplatform with Compose Multiplatform for the UI, so the Result Screen, the Rendered Barcode drawing and all guessing logic are written once. Only the camera preview and the text/barcode recognition are platform-specific, behind one small interface (iOS: AVFoundation + Vision; Android: CameraX + ML Kit).

## Considered Options

- **Native UI per platform (SwiftUI + Jetpack Compose), shared logic only.** Rejected: the UI is a handful of screens, so duplicating it costs more than it buys.
- **iOS only.** Rejected: the owner wants Android as well.

## Consequences

- Guessing logic (check digit, Candidates, Agreement, scan state) is pure Kotlin and unit-testable on the JVM, independent of any camera.
- A real Android field test needs a physical phone; the emulator camera is useless for text recognition.
- Camera and recognition code must be written twice, once per platform.
