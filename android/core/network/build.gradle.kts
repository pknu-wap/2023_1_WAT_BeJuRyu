plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
    id("com.jaino.hilt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    namespace = "com.jaino.network"
}

dependencies {
    implementation(project(":core:model"))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.kotlin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}