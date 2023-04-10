plugins {
    id("com.jaino.feature")
}

android {
    namespace = "com.jaino.navigation"
}

dependencies {
    implementation(libs.bundles.navigation)
    implementation(libs.nav.feature)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}