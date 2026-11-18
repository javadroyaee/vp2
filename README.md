# Avang VPN

Minimal, fast, and automated Android VPN client UI.

## Build

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## GitHub Actions

Push a tag like `v1.0.0` to trigger automatic build + release.

```bash
git tag v1.0.0
git push origin v1.0.0
```

## Note

This is a UI skeleton. To make it a real VPN client,
integrate libXray or AndroidLibXrayLite.