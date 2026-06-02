// Top-level build file where you can add configuration options common to all sub-projects/modules.
implementation("androidx.compose.material3:material3:1.2.1")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

}