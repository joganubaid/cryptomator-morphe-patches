package app.morphe.extension.cryptomator.patches

import app.morphe.patcher.Fingerprint

/**
 * Fingerprints for targeting Cryptomator methods.
 *
 * ⚠️  Update the signature strings below to match the exact class/method
 *     signatures in your target Cryptomator APK version.
 *     Decompile with apktool/jadx to find the correct paths.
 */
internal object Fingerprints {

    /**
     * Matches VaultListPresenter.checkLicense() → boolean
     * Adjust the class path if Cryptomator's package structure differs.
     */
    val checkLicense = Fingerprint("Lorg/cryptomator/ui/vaultlist/VaultListPresenter;.checkLicense:()Z")
}