plugins {
    id("com.jaino.feature")
    id("com.jaino.hilt")
}

android {
    namespace = "com.jaino.auth"

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
    implementation(libs.androidx.fragment)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.kakao.login)
    implementation(libs.timber)
}