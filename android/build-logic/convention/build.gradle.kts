plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.build)
    compileOnly(libs.kotlin.gradle)
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.jaino.application"
            implementationClass = "com.jaino.convention.AndroidApplicationPlugin"
        }
        create("android-feature") {
            id = "com.jaino.feature"
            implementationClass = "com.jaino.convention.AndroidFeaturePlugin"
        }
        create("android-kotlin") {
            id = "com.jaino.kotlin"
            implementationClass = "com.jaino.convention.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "com.jaino.hilt"
            implementationClass = "com.jaino.convention.AndroidHiltPlugin"
        }
        create("kotlin-serialization") {
            id = "com.jaino.serialization"
            implementationClass = "com.jaino.convention.KotlinSerializationPlugin"
        }
    }
}
