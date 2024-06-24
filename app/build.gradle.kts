

plugins {
    kotlin("kapt")
    alias(libs.plugins.pluginAndroidApplication)
    alias(libs.plugins.pluginJetbrainsKotlinAndroid)
    alias(libs.plugins.pluginDaggerHilt)
    alias(libs.plugins.pluginDevKsp)
    alias(libs.plugins.pluginUndercouchDownload)
    alias(libs.plugins.pluginSerialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.pluginNavigationSafeArgs)
    alias(libs.plugins.pluginSecretGradle)
}

val versionMajor = 0
val versionSprint = 13
val versionSprintRevision = 7

android {
    namespace = "com.example.lab4_frameworkmobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab4_frameworkmobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-disable-log.pro"
            )
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true

        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-disable-log.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    secrets {
        // Optionally specify a different file name containing your secrets.
        // The plugin defaults to "local.properties"
        propertiesFileName = "secrets.properties"

        // A properties file containing default secret values. This file can be
        // checked in version control.
        defaultPropertiesFileName = "local.properties"

        // Configure which keys should be ignored by the plugin by providing regular expressions.
        // "sdk.dir" is ignored by default.
        ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
        ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
    }
}

dependencies {

    //Libs
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
    //Android
    implementation(libs.bundles.android)
    //Navigation
    implementation(libs.bundles.navigation)
    androidTestImplementation(libs.androidxNavigationTesting)
    //Hilt
    implementation(libs.daggerHilt)
    kapt(libs.daggerHiltCompiler)
    //Lifecycle
    implementation(libs.bundles.lifecycle)
    //Coroutines
    implementation(libs.bundles.coroutines)
    //Biometric
    implementation(libs.androidxBiometric)
    // Room
    implementation(libs.room.ktx.v261)
    ksp(libs.room.compiler.v261)
    //Google
    implementation(libs.play.services.location)
}

kapt {
    correctErrorTypes = true
}
