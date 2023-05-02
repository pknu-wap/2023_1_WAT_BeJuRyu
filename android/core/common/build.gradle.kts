plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
}

android {
    namespace = "com.jaino.common"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.kotlin.datetime)
    implementation(libs.timber)
    implementation(libs.glide)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}