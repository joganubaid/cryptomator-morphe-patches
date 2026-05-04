package org.cryptomator.domain.usecases;

/**
 * Patched LicenseCheck implementation that always returns a valid license.
 * This class is injected into Cryptomator to bypass license verification.
 */
public class PatchedLicenseCheck implements LicenseCheck {
    public PatchedLicenseCheck() {
    }

    @Override
    public String mail() {
        return "patched@cryptomator.local";
    }
}