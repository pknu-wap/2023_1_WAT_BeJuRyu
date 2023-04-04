import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id("com.jaino.application")
    id("com.jaino.hilt")
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
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.splash)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.work.compiler)

    // Third-Party
    implementation(libs.kakao.login)
    implementation(libs.timber)
    implementation(libs.material)
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
