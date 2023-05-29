plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.room)
    implementation(libs.androidx.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}