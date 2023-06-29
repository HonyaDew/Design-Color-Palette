plugins {
    id(Deps.Plugins.javaLibraty)
    id(Deps.Plugins.kotlinJvm)
    id(Deps.Plugins.ksp)
    id(Deps.Plugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(Deps.Json.serialization)
}
