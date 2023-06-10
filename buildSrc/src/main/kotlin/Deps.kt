object Deps {

    object Plugins {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val javaLibraty = "java-library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
        const val serialization = "org.jetbrains.kotlin.plugin.serialization"
        const val ksp = "com.google.devtools.ksp"

    }

    object ClassPath {
        const val versionGradle = "7.4.1"
        const val versionKotlin = "1.8.10"
        const val versionKsp = "1.8.21-1.0.11"
        const val versionSerialization = "1.5.1"

        const val androidGradlePlugin = "com.android.tools.build:gradle:$versionGradle"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$versionKotlin"
        const val kspGradlePlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$versionKsp"
        const val serializationGradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:$versionSerialization"
    }

    object Core{
        object Version {
            const val coreKtx = "1.9.0"
            const val appCompat = "1.4.1"
            const val activityCompose = "1.4.0"
            const val androidMaterial = "1.6.0"
            const val composeCompiler = "1.4.7"
            const val accompanist = "0.30.1"
        }

        const val coreKts = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
        const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"
        const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}"
    }

    object Lifecycle{
        const val version = "2.5.1"
        const val versionEx = "2.2.0"

        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$versionEx"
    }

    object Compose {
        const val version = "1.4.0-beta01"
        const val versionMaterial = "1.0.1"

        const val composeMaterial = "androidx.compose.material3:material3:$versionMaterial"
        const val composeMaterialWindow = "androidx.compose.material3:material3-window-size-class:$versionMaterial"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
    }

    object Navigation {
        const val version = "2.5.1"

        const val navigationCompose = "androidx.navigation:navigation-compose:$version"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    }

    object Room{
        const val version = "2.5.0"

        const val runtime = "androidx.room:room-runtime:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Json{
        object Version{
            const val gson = "2.8.8"
            const val moshi = "1.14.0"
            const val serialization = "1.5.1"
        }
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.serialization}"
    }

    object Koin {
        const val version = "3.3.3"
        const val versionCompose = "3.4.2"

        const val koin = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$versionCompose"
    }

    object Test {
        object Version {
            const val junit = "1.1.1"
            const val androidxJunit = "1.1.3"
            const val roboeletric = "4.6.1"
            const val mockk = "1.12.4"
            const val okHttp3MockWebServer = "4.9.2"
            const val espressoCore = "3.4.0"
        }
        const val junit = "junit:junit:${Version.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Version.junit}"
        const val roboeletric = "org.robolectric:robolectric:${Version.roboeletric}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
        const val okHttp3MockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.okHttp3MockWebServer}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
        const val composeDebugTooling = "androidx.compose.ui:ui-tooling:${Compose.version}"
        const val composeDebugManifest= "androidx.compose.ui:ui-test-manifest:${Compose.version}"
    }

}