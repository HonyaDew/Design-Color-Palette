plugins {
    id(Deps.Plugins.javaLibraty)
    id(Deps.Plugins.kotlinJvm)
    id(Deps.Plugins.ksp)
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
    implementation(project(":core:model"))

    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.mockitoCore)
    testImplementation(Deps.Test.mockitoKotlin)
    testImplementation(Deps.Test.mockitoInline)
    testImplementation(Deps.Test.suspendTests)
    testRuntimeOnly(Deps.Test.mockitoRuntimeOnly)
}
