plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.home"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.fragment)
    implementation(libs.bundles.navigation)
    implementation(libs.material)
    implementation(libs.timber)
    implementation(libs.systemUiController)
}