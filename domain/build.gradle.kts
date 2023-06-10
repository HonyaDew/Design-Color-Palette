plugins {
    id(Deps.Plugins.javaLibraty)
    id(Deps.Plugins.kotlinJvm)
    id(Deps.Plugins.ksp)
    id(Deps.Plugins.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Deps.Json.serialization)
}
