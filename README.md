# 🧩 Patches
Cryptomator Morphe Patches

## ❓ About

Cryptomator Morphe Patches unlock premium features in the [Cryptomator](https://github.com/cryptomator/android) Android app.

&nbsp;
## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->
> **[v1.1.0](https://github.com/joganubaid/cryptomator-morphe-patches/releases/tag/v1.1.0)**&nbsp;&nbsp;•&nbsp;&nbsp;`main`&nbsp;&nbsp;•&nbsp;&nbsp;2 patches total
<details open>
<summary>📦 Cryptomator&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.12.3 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Check License No-Op](#check-license-no-op) | Makes VaultListPresenter.checkLicense() a no-op to skip license check |  |
| [License Bypass](#license-bypass) | Bypasses Cryptomator license verification to enable premium features |  |

</details>

<!-- PATCHES_END -->

## 🔧 Supported App Versions

| App | Version | Package |
|-----|---------|---------|
| Cryptomator | 1.12.3 | org.cryptomator |

## 🚀 How to use

### Using Morphe Manager (Recommended)

1. Download [Morphe Manager](https://github.com/MorpheApp/morphe-manager)
2. Add this repository as a patch source:
   - URL: `https://github.com/joganubaid/cryptomator-morphe-patches`
   - Or click: [Add to Morphe](https://morphe.software/add-source?github=joganubaid/cryptomator-morphe-patches)
3. Select the desired patches and patch the app

### Building from source

```bash
# Clone the repository
git clone https://github.com/joganubaid/cryptomator-morphe-patches.git
cd cryptomator-morphe-patches

# Build patches
./gradlew clean :patches:buildAndroid
```

## 📋 Requirements

- Java 21
- Gradle 8.x
- Android SDK

## 📜 License

Cryptomator Morphe Patches are licensed under the [GNU General Public License v3.0](LICENSE)

## ⚠️ Disclaimer

This software is provided for educational and security research purposes only.
The original Cryptomator app is property of Skymatic GmbH.
Please purchase a legitimate license to support the developers if you use this app regularly.
