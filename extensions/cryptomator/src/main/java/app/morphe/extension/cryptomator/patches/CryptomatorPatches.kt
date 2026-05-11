package app.morphe.extension.cryptomator.patches

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch

/**
 * Cryptomator license bypass - extension side.
 *
 * This stub registers the fingerprint definitions. The actual
 * patch implementations live in patches/src/main/kotlin.
 */
internal val licenseBypass by lazy {
    bytecodePatch(
        name = "License Bypass",
        description = "Bypasses Cryptomator license verification to enable premium features",
    ) {
        dependsOn(bytecodePatch(Fingerprints.doLicenseCheckExecute) {
            execute {
                // Replace method body to return a valid LicenseCheck
                // LicenseCheck is an interface with mail() → String
                // We use a helper approach: const-string a dummy email
                // and return it via the interface
                method.implementation!!.clearInstructions()
                method.implementation!!.addInstruction(
                    "const-string v0, \"bypassed@license.morphe\""
                )
                method.implementation!!.addInstruction(
                    "return-object v0"
                )
            }
        })
    }
}

internal val checkLicenseNoOp by lazy {
    bytecodePatch(
        name = "Check License No-Op",
        description = "Makes VaultListPresenter.checkLicense() a no-op to skip license check",
    ) {
        dependsOn(bytecodePatch(Fingerprints.vaultListPresenterCheckLicense) {
            execute {
                // Replace method body with immediate return (void method)
                method.implementation!!.clearInstructions()
                method.implementation!!.addInstruction("return-void")
            }
        })
    }
}