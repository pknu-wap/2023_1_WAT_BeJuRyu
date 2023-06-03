plugins {
    alias(libs.plugins.android.library)
}

android{
    namespace = "com.jaino.designsystem"
    compileSdk = 33
}

dependencies {
    implementation(libs.material)
    implementation(libs.kakao.share)
}