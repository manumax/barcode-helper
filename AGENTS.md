# Barcode Helper

Kotlin Multiplatform app that guesses the true number of a badly printed EAN-13 barcode from its printed digits. Read `docs/v1-spec.md` before building or planning anything.

## Agent skills

### Issue tracker

GitHub Issues on `manumax/barcode-helper`, via the `gh` CLI. See `docs/agents/issue-tracker.md`.

### Triage labels

Default five-role vocabulary (`needs-triage`, `needs-info`, `ready-for-agent`, `ready-for-human`, `wontfix`). See `docs/agents/triage-labels.md`.

### Domain docs

Single-context: root `GLOSSARY.md` plus `docs/adr/`. See `docs/agents/domain.md`.

## Verification

Before calling work done, run the app on both the Android emulator and the iOS simulator and exercise the changed behavior by hand (e.g. via the mobile-mcp tools). Unit tests and compiling are not enough.
