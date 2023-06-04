plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "com.jaino.analysis"

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
    implementation(libs.bundles.camerax)
    implementation(libs.glide)
    implementation(libs.timber)
    implementation(libs.kotlin.datetime)
    implementation(libs.androidx.fragment)
    implementation(libs.bundles.navigation)
    implementation(libs.material)
    implementation(libs.progressView)
    implementation(libs.kakao.share)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.livedata)
}