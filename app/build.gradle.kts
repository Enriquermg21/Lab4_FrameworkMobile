

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
}

kapt {
    correctErrorTypes = true
}
