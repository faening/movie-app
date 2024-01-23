plugins {
    alias(libs.plugins.androidapplication)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.googleservices)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.github.faening.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.faening.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {
    // Common
    implementation(libs.android.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Firebase
    implementation(platform(libs.firebase))
    implementation(libs.firebaseanalytics)
    implementation(libs.firebaseauth)
    implementation(libs.firebasedatabase)
    implementation(libs.firebasestorage)

    // SplashScreen (For Android 12+)
    implementation(libs.splashscreen)

    // Dagger Hilt
    implementation(libs.hilt)
    kapt(libs.hiltcompiler)

    // Lifecycle
    implementation(libs.lifecycleviewmodel)
    implementation(libs.lifecyclelivedata)

    // Navigation
    implementation(libs.navigationfragment)
    implementation(libs.navigationui)

    // Glide
    implementation(libs.glide)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxtest)
    androidTestImplementation(libs.androidxtestespresso)
}

kapt {
    correctErrorTypes = true
}