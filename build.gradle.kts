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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
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

