package app.morphe.patches.cryptomator

import app.morphe.patcher.Fingerprint

/**
 * Fingerprints for targeting Cryptomator 1.12.3 license check methods.
 *
 * ✅ Verified via jadx decompilation of Cryptomator-1.12.3.apk
 *
 * Key targets:
 * - DoLicenseCheck.execute() returns LicenseCheck (throws BackendException)
 *   This is where license JWT validation happens.
 * - VaultListPresenter.checkLicense() is private void, triggers license UI.
 */
internal object Fingerprints {

    /**
     * DoLicenseCheck.execute() → LicenseCheck
     *
     * Decompiled:
     *   public LicenseCheck execute() throws BackendException { ... }
     *
     * Smali class path: Lorg/cryptomator/domain/usecases/DoLicenseCheck;
     *
     * This is the core license validation entry point. DoLicenseCheck
     * validates a JWT signed with Google's ECDSA key against a known public key.
     * Intercept here to return a mock LicenseCheck instead of real verification.
     */
    val doLicenseCheckExecute = Fingerprint(
        "Lorg/cryptomator/domain/usecases/DoLicenseCheck;"
    )

    /**
     * VaultListPresenter.checkLicense() → void
     *
     * Decompiled:
     *   private final void checkLicense() { ... }
     *
     * Smali class path: Lorg/cryptomator/presentation/presenter/VaultListPresenter;
     *
     * This private method calls DoLicenseCheck and handles the result:
     * - On success: checks for app updates, dismisses dialog
     * - On error: launches LicenseCheckActivity with deep link intent
     *
     * No-opping this method prevents the entire license check UI flow.
     */
    val vaultListPresenterCheckLicense = Fingerprint(
        "Lorg/cryptomator/presentation/presenter/VaultListPresenter;"
    )
}