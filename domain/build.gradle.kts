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

dependencies {
    implementation(Deps.Json.serialization)
}
