group = "org.cryptomator"

patches {
    about {
        name = "Cryptomator Patches"
        description = "Patches to unlock premium features in Cryptomator"
        source = "git@github.com:joganubaid/cryptomator-morphe-patches.git"
        author = "joganubaid"
        contact = "joganubaid@example.com"
        website = "https://github.com/joganubaid/cryptomator-morphe-patches"
        license = "GPLv3"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    implementation(libs.gson)
    implementation(libs.morphe.patches.library)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("app.morphe.util.PatchListGeneratorKt")
    }
    publish {
        dependsOn("generatePatchesList")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}