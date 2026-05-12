package app.morphe.extension.cryptomator

import app.morphe.patcher.extensions.HookProvider
import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.extensions.InstructionExtensions.hookMethod
import app.morphe.patcher.patch.bytecodePatch

/**
 * Cryptomator extension hooks.
 *
 * Provides runtime hook points for the Morphe patcher framework.
 * These hooks are loaded by the Morphe Manager app when the patch is applied.
 */
internal object CryptomatorExtension : HookProvider {

    override fun getHooks() = listOf(
        licenseCheckHook
    )

    /**
     * Hooks DoLicenseCheck.execute() to return a valid LicenseCheck
     * without performing JWT signature verification.
     */
    private val licenseCheckHook = bytecodePatch(
        name = "License Check Hook",
        description = "Hooks license validation to always return valid"
    ) {
        hookMethod(
            className = "Lorg/cryptomator/domain/usecases/DoLicenseCheck;",
            methodName = "execute",
            methodSignature = "()Lorg/cryptomator/domain/usecases/LicenseCheck;"
        ) {
            // Return a mock LicenseCheck that has a valid email
            addInstruction(0, "const-string v0, \"bypassed@license.morphe\"")
            addInstruction(1, "return-object v0")
        }
    }
}