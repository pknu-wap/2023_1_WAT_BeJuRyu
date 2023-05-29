plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.account"

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
    implementation(libs.timber)
    implementation(libs.androidx.fragment)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.material)
    implementation(libs.processphoenix)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}