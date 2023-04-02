plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.datastore"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    implementation(project(":core:model"))
    implementation(libs.bundles.kotlin)
}