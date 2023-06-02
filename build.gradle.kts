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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}