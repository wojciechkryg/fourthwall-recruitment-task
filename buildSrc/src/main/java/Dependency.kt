object Dependency {

    object AndroidX {
        const val activity = "androidx.activity:activity-ktx:${Version.activity}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activity}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val compose = "androidx.compose.ui:ui:${Version.compose}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Version.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Version.compose}"
        const val composeMaterialIcons =
            "androidx.compose.material:material-icons-core:${Version.compose}"
        const val composeMaterialIconsExtended =
            "androidx.compose.material:material-icons-extended:${Version.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val composeToolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
        const val core = "androidx.core:core-ktx:${Version.core}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    }

    object Google {
        const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        const val material = "com.google.android.material:material:${Version.material}"
    }

    object KotlinX {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    }

    object Test {
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val junit = "junit:junit:${Version.junit}"
        const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
    }
}