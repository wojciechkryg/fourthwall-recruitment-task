plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(Dependency.coil)
    implementation(Dependency.loggingInterceptor)
    implementation(Dependency.moshi)
    kapt(Dependency.moshiCodegen)
    implementation(Dependency.retrofit)
    implementation(Dependency.retrofitConverter)
    implementation(Dependency.AndroidX.activity)
    implementation(Dependency.AndroidX.activityCompose)
    implementation(Dependency.AndroidX.appCompat)
    implementation(Dependency.AndroidX.compose)
    implementation(Dependency.AndroidX.composeFoundation)
    implementation(Dependency.AndroidX.composeMaterial)
    implementation(Dependency.AndroidX.composeMaterialIcons)
    implementation(Dependency.AndroidX.composeMaterialIconsExtended)
    implementation(Dependency.AndroidX.composeToolingPreview)
    implementation(Dependency.AndroidX.core)
    implementation(Dependency.AndroidX.hiltNavigationCompose)
    implementation(Dependency.AndroidX.lifecycleRuntime)
    implementation(Dependency.AndroidX.lifecycleViewModel)
    implementation(Dependency.AndroidX.lifecycleViewModelCompose)
    implementation(Dependency.AndroidX.navigationCompose)
    implementation(Dependency.Google.hilt)
    kapt(Dependency.Google.hiltCompiler)
    implementation(Dependency.Google.material)
    implementation(Dependency.KotlinX.coroutines)
    implementation(Dependency.KotlinX.coroutinesAndroid)
    testImplementation(Dependency.Test.coroutines)
    testImplementation(Dependency.Test.junit)
    testRuntimeOnly(Dependency.Test.junitEngine)
    testImplementation(Dependency.Test.mockk)
    androidTestImplementation(Dependency.Test.composeJunit)
    androidTestImplementation(Dependency.Test.espresso)
    androidTestImplementation(Dependency.Test.junitExt)
    debugImplementation(Dependency.AndroidX.composeTooling)
}

kapt {
    correctErrorTypes = true
}