import java.util.Properties

plugins {
    id("com.jaino.application")
    id("com.jaino.hilt")
}

android {
    val properties = Properties().apply {
        load(file("../local.properties").inputStream())
    }
    namespace = "com.jaino.BeJuRyu"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jaino.BeJuRyu"
        minSdk = 26
        targetSdk =  33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(project(":feature:auth"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.splash)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.work.compiler)

    // Third-Party
    /*implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)
    implementation(libs.bundles.retrofit)*/
    implementation(libs.timber)
    implementation(libs.material)
}