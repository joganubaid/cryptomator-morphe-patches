/*
 * Copyright 2026 joganubaid
 *
 * Cryptomator Morphe Patches are licensed under the GNU General Public License v3.0,
 * with additional terms under GPLv3 Section 7. See LICENSE for details.
 */

package app.morphe.patches.cryptomator

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

/**
 * License Bypass
 *
 * Bypasses Cryptomator license verification to enable premium features.
 *
 * Verified target via jadx decompilation:
 *   DoLicenseCheck.execute() → LicenseCheck
 *
 * Strategy: Replace entire method body with a const-string returning
 * a dummy email, satisfying the LicenseCheck interface contract.
 * This prevents JWT signature verification from running.
 */
val licenseBypassPatch = bytecodePatch(
    name = "License Bypass",
    description = "Bypasses Cryptomator license verification to enable premium features",
    default = true,
) {
    dependsOn(bytecodePatch(Fingerprints.doLicenseCheckExecute) {
        execute {
            // Replace entire method body: return a string (mock email)
            // LicenseCheck interface has mail(): String
            // doLicenseCheckExecute will be matched by Fingerprint class reference,
            // then we replace with return of a constant dummy value
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