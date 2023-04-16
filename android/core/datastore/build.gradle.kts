plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.datastore"
}

dependencies {

    implementation(project(":core:model"))
    implementation(libs.bundles.kotlin)
    implementation(libs.androidx.security)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}