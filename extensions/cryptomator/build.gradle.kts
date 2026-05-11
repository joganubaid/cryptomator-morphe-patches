plugins {
    alias(libs.plugins.android.library) apply false
}

dependencies {
    compileOnly(libs.annotation)
    compileOnly(libs.morphe.extensions.library)
}

android {
    defaultConfig {
        minSdk = 26
    }
}