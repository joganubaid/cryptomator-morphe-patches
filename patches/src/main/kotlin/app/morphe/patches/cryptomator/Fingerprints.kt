package app.morphe.patches.cryptomator

import app.morphe.patcher.Fingerprint

/**
 * Fingerprints for targeting Cryptomator 1.12.3 license check methods.
 *
 * ⚠️  These fingerprints target specific classes/methods in the Cryptomator APK.
 * If the target app version changes, update these signatures to match.
 */
internal object Fingerprints {

    /**
     * Matches: app.cryptomator.ui.managers.VaultListPresenter.checkLicense() → boolean
     *
     * This is the license validation check called when the vault list is displayed.
     * On no-op, the original method body is replaced with `return true`.
     */
    val checkLicense = Fingerprint(
        "Lapp/cryptomator/ui/managers/VaultListPresenter;",
        "checkLicense",
        "()Z"
    )
}