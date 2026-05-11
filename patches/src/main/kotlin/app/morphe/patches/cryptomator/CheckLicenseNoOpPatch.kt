/*
 * Copyright 2026 joganubaid
 *
 * Cryptomator Morphe Patches are licensed under the GNU General Public License v3.0,
 * with additional terms under GPLv3 Section 7. See LICENSE for details.
 */

package app.morphe.patches.cryptomator

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch

/**
 * Check License No-Op
 *
 * Makes VaultListPresenter.checkLicense() a no-op to skip license check entirely.
 * This is an alternative to License Bypass that simply short-circuits the check
 * rather than fabricating a positive result.
 */
val checkLicenseNoOpPatch = bytecodePatch(
    name = "Check License No-Op",
    description = "Makes VaultListPresenter.checkLicense() a no-op to skip license check",
    default = true,
) {
    dependsOn(bytecodePatch(Fingerprints.checkLicense) {
        execute {
            // Replace entire method body with: return true
            // This makes checkLicense() always report "licensed"
            method.implementation!!.clearInstructions()
            method.implementation!!.addInstruction(
                "const/4 v0, 0x1",
            )
            method.implementation!!.addInstruction(
                "return v0",
            )
        }
    })
}