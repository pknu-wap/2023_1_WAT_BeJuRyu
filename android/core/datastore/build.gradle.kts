plugins {
    id("com.jaino.feature")
    id("com.jaino.serialization")
    id("com.jaino.hilt")
    alias(libs.plugins.google.protobuf)
}

android {
    namespace = "com.jaino.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }

    dependencies {
        implementation(project(":core:model"))
        implementation(libs.bundles.kotlin)
        implementation(libs.bundles.datastore)
        implementation(libs.androidx.security)
        implementation(libs.timber)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.junit)
        androidTestImplementation(libs.androidx.test.espresso)
    }
}

