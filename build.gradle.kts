plugins {
    id("com.android.application") version "9.2.0"
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
}
