plugins {
    id("com.android.application") version "9.2.0"
    kotlin("plugin.compose") version "2.1.0"
}

android {
    namespace = "my.supa.lamp"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.supa.lamp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.activity:activity-compose:1.9.3")
}
