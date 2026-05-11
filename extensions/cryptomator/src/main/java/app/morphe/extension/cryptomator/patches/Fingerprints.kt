package app.morphe.extension.cryptomator.patches

import app.morphe.patcher.Fingerprint

/**
 * Fingerprints for targeting Cryptomator methods.
 *
 * Verified via jadx decompilation of Cryptomator-1.12.3.apk.
 *
 * DoLicenseCheck.execute() returns LicenseCheck and is the central
 * license validation entry point.
 *
 * VaultListPresenter.checkLicense() is a private void method that
 * triggers the license UI flow.
 */
internal object Fingerprints {

    /**
     * DoLicenseCheck.execute() → LicenseCheck
     * Smali: Lorg/cryptomator/domain/usecases/DoLicenseCheck;.execute:()Lorg/cryptomator/domain/usecases/LicenseCheck;
     *
     * Targeting this allows returning a valid LicenseCheck
     * without JWT signature verification.
     */
    val doLicenseCheckExecute = Fingerprint(
        "Lorg/cryptomator/domain/usecases/DoLicenseCheck;"
    )

    /**
     * VaultListPresenter.checkLicense() → void
     * Smali: Lorg/cryptomator/presentation/presenter/VaultListPresenter;.checkLicense:()V
     *
     * Private method that kicks off license validation.
     * No-opping this prevents the license dialog entirely.
     */
    val vaultListPresenterCheckLicense = Fingerprint(
        "Lorg/cryptomator/presentation/presenter/VaultListPresenter;"
    )
}