pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Design Color Palette"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:model")
include(":core:common")
include(":feature:palette")
include(":feature:harmony")
include(":feature:image")
include(":feature:saved")
include(":feature:sliders")
include(":feature:settings")
