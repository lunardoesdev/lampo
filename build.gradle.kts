plugins {
    id("com.android.application") version "9.2.0"
}

android {
    namespace = "my.supa.lamp"
    compileSdk = 34
    buildToolsVersion = "36.0.0"

    defaultConfig {
        applicationId = "my.supa.lamp"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

}

