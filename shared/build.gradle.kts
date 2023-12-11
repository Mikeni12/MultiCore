plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    id("maven-publish")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "mx.mikeni.multicore"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    publishing {
        singleVariant("debug") {
            withSourcesJar()
            withJavadocJar()
        }
        singleVariant("release") {

        }
    }
}

afterEvaluate {
    android.libraryVariants.forEach { variant ->
        publishing.publications.create(variant.name, MavenPublication::class.java) {
            from(components.findByName(variant.name))

            groupId = "com.mikeni"
            artifactId = "multicore"
            version = "1.0.0"
        }
    }
}
