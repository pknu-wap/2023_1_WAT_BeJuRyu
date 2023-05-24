import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.jaino.application")
    id("com.jaino.hilt")
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
}

android {

    namespace = "com.jaino.app"

    defaultConfig {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        versionName = libs.findVersion("appVersion").get().requiredVersion
        versionCode = checkNotNull(libs.findVersion("versionCode").get().requiredVersion).toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", getLocalProperty("KAKAO_NATIVE_APP_KEY"))
        resValue("string", "KAKAO_NATIVE_APP_KEY", "kakao${getLocalProperty("KAKAO_NATIVE_APP_KEY")}")

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

fun getLocalProperty(property: String): String {
    return gradleLocalProperties(rootDir).getProperty(property)
}

dependencies {

    implementation(project(":feature:auth"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:analyze"))
    implementation(project(":feature:dictionary"))
    implementation(project(":feature:review"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.splash)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.work.compiler)
    implementation(libs.bundles.navigation)

    // Third-Party
    implementation(libs.kakao.login)
    implementation(libs.timber)
    implementation(libs.material)
    implementation(platform(libs.firebase))
    implementation(libs.bundles.firebase)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}
