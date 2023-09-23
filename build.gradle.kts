buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.ClassPath.androidGradlePlugin)
        classpath(Deps.ClassPath.kotlinGradlePlugin)
        classpath(Deps.ClassPath.kspGradlePlugin)
        classpath(Deps.ClassPath.serializationGradlePlugin)
        classpath(Deps.ClassPath.gmsGradlePlugin)
        classpath(Deps.ClassPath.crashlyticsGradlePlugin)
    }
}

allprojects{
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
    tasks.withType<Test>() {
        useJUnitPlatform()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}

