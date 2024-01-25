// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // Gradle Secrets
        // https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin?hl=pt-br#kotlin
        classpath(libs.secrets.gradle)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.secrets.gradle) apply false
}