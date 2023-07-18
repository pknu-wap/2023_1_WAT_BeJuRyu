plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "com.jaino.setting"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.fragment)
    implementation(libs.timber)
    implementation(libs.material)
    implementation(libs.processphoenix)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}