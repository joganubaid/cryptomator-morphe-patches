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
 * License Bypass
 *
 * Bypasses Cryptomator license verification to enable premium features.
 * This patch hooks into the checkLicense() method and forces it to return true,
 * effectively making the app believe the user has a valid license.
 */
val licenseBypassPatch = bytecodePatch(
    name = "License Bypass",
    description = "Bypasses Cryptomator license verification to enable premium features",
    default = true,
) {
    dependsOn(bytecodePatch(Fingerprints.checkLicense) {
        execute {
            // Replace entire method body with: return true
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