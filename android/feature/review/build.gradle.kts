plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "com.jaino.review"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.fragment)
    implementation(libs.material)
    implementation(libs.glide)
    implementation(libs.timber)
}