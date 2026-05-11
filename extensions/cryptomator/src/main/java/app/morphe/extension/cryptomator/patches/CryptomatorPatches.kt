package app.morphe.extension.cryptomator.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.extensions.InstructionExtensions.findInstruction
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.extensions.InstructionExtensions.removeInstruction
import app.morphe.patcher.methodCall
import app.morphe.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

/**
 * Cryptomator license check bypass.
 *
 * VaultListPresenter.checkLicense() is called to verify the user has a valid license.
 * This patch hooks the method and immediately returns true (license valid),
 * preventing any license enforcement.
 */
internal fun licenseBypassPatch(
    checkLicenseFingerprint: Fingerprint
) = bytecodePatch(
    name = "License Bypass",
    description = "Bypasses Cryptomator license verification to enable premium features",
) {
    execute {
        checkLicenseFingerprint.method.addInstruction(
            0,
            "const/4 v0, 0x1",
        )
        checkLicenseFingerprint.method.addInstruction(
            1,
            "return v0",
        )
    }
}

/**
 * Cryptomator license check no-op.
 *
 * Similar to License Bypass but targets the no-op approach:
 * makes VaultListPresenter.checkLicense() return immediately without
 * performing any license validation logic.
 */
internal fun licenseNoOpPatch(
    checkLicenseFingerprint: Fingerprint
) = bytecodePatch(
    name = "Check License No-Op",
    description = "Makes VaultListPresenter.checkLicense() a no-op to skip license check",
) {
    execute {
        // Clear existing instructions
        val instructions = checkLicenseFingerprint.method.implementation!!.instructions
        val insns = instructions.toList()
        for (insn in insns) {
            instructions.remove(insn)
        }

        // Get return register from method signature
        val proto = (checkLicenseFingerprint.method as? MethodReference)?.returnType ?: "Z"
        val isBoolean = proto == "Z"

        if (isBoolean) {
            checkLicenseFingerprint.method.addInstruction(
                0,
                "const/4 v0, 0x1",
            )
            checkLicenseFingerprint.method.addInstruction(
                1,
                "return v0",
            )
        } else {
            checkLicenseFingerprint.method.addInstruction(
                0,
                "return-void",
            )
        }
    }
}