dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.version.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
