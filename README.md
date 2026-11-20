# Barcode Helper

Kotlin Multiplatform + Compose Multiplatform app that guesses the true number of a badly printed EAN-13 barcode. See `docs/v1-spec.md` and `docs/adr/`.

- Bundle ID / Android package: `it.manumax78.barcodehelper`
- iPhone only, iOS 17+, portrait only. Android minSdk 26, portrait only.
- UI strings live in `composeApp/src/commonMain/composeResources/values/strings.xml` (add `values-it/` for Italian later).

## Layout

| Module | Purpose |
| --- | --- |
| `composeApp` | Shared Kotlin: Compose UI and pure guessing logic (JVM-testable). Platform code only for camera/recognition (later). |
| `androidApp` | Android application shell (`MainActivity`). |
| `iosApp` | Xcode project (SwiftUI shell hosting the Compose view controller). |

## Requirements

JDK 17+, Android SDK (platform 37, `local.properties` with `sdk.dir=...` or `ANDROID_HOME`), Xcode 26+ with an iOS 17+ simulator.

## Run the shared tests (JVM)

```sh
./gradlew :composeApp:testAndroidHostTest
```

## Android

```sh
./gradlew :androidApp:installDebug   # with an emulator running (or a device attached)
adb shell am start -n it.manumax78.barcodehelper/.MainActivity
```

Or open the project in Android Studio and run the `androidApp` configuration.

## iOS

Open `iosApp/iosApp.xcodeproj` in Xcode, pick an iPhone simulator, and Run. The "Compile Kotlin Framework" build phase invokes Gradle (`embedAndSignAppleFrameworkForXcode`) to build the shared framework. Set your Team in Signing & Capabilities for a physical device.

Command line:

```sh
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -sdk iphonesimulator \
  -destination 'platform=iOS Simulator,name=iPhone 16' build
```
