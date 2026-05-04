package org.cryptomator.patches

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.*
import app.morphe.patcher.StringComparisonType
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprint for DoLicenseCheck.execute() method
 * This method performs JWT license verification
 */
object DoLicenseCheckExecuteFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "Lorg/cryptomator/domain/usecases/LicenseCheck;",
    parameters = listOf(),
    filters = listOf(
        // Match: invoke-direct {p0, v0}, Lorg/cryptomator/domain/usecases/DoLicenseCheck;->useLicenseOrRetrieveFromDb
        methodCall(
            definingClass = "Lorg/cryptomator/domain/usecases/DoLicenseCheck;",
            name = "useLicenseOrRetrieveFromDb",
        ),
        // Match: move-result-object v0
        opcode(Opcode.MOVE_RESULT_OBJECT),
        // Match: iput-object v0, p0, ...->license
        opcode(Opcode.IPUT_OBJECT),
        // Match: CharMatcher.whitespace()
        methodCall(
            definingClass = "Lcom/google/common/base/CharMatcher;",
            name = "whitespace",
        ),
    ),
    custom = { _, classDef ->
        classDef.type == "Lorg/cryptomator/domain/usecases/DoLicenseCheck;"
    }
)

/**
 * Fingerprint for VaultListPresenter.checkLicense() method
 * This method triggers the license check on app start
 */
object VaultListPresenterCheckLicenseFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf(),
    filters = listOf(
        // Match: iget-object v0, p0, ...->licenseCheckUseCase
        opcode(Opcode.IGET_OBJECT),
        // Match: const-string v1, ""
        opcode(Opcode.CONST_STRING),
        // Match: invoke-virtual {v0, v1}, ...->withLicense
        methodCall(
            definingClass = "Lorg/cryptomator/domain/usecases/DoLicenseCheckUseCase;",
            name = "withLicense",
        ),
    ),
    custom = { _, classDef ->
        classDef.type == "Lorg/cryptomator/presentation/presenter/VaultListPresenter;"
    }
)