plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.safe.args)
}

android {
    namespace = "com.github.faening.movieapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.github.faening.movieapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.0.18"
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
}

dependencies {
    // Common
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraint.layout)
    implementation(libs.material)
    implementation(libs.core.ktx)

    // Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)

    // Firebase
    implementation(platform(libs.firebase))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)

    // SplashScreen (For Android 12+)
    implementation(libs.splashscreen)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.androidx.navigation.compose)

    // Glide
    implementation(libs.glide)

    // Circle Image View
    implementation(libs.circle.image.view)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)

    // Ok Http
    implementation(platform(libs.ok.http.bom))
    implementation(libs.ok.http)
    implementation(libs.ok.http.logging.interceptor)

    // Simple Search View
    implementation(libs.simple.search.view)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}

kapt {
    correctErrorTypes = true
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
