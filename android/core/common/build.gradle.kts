plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
}

android {
    namespace = "com.jaino.common"
}

dependencies {
    implementation(libs.kotlin.datetime)
    implementation(libs.timber)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}