import java.util.Properties

plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.filipeoliveira.emojicenter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.filipeoliveira.emojicenter"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        debug {
            isDebuggable = true
        }

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.register("updateBuildConfigFromLocalProperties") {
    doLast {
        val localPropertiesFile = file("${rootProject.rootDir}/local.properties")
        if (localPropertiesFile.exists()) {
            val properties = Properties()
            localPropertiesFile.inputStream().use { stream ->
                properties.load(stream)
            }

            // Define the properties you want to read from local.properties
            val apiKey = properties.getProperty("myApiSecretKey")
            val anotherProperty = properties.getProperty("anotherProperty")

            // Update BuildConfig fields
            android.buildTypes.forEach { type ->
                type.buildConfigField("String", "MY_API_KEY", "\"$apiKey\"")
                type.buildConfigField("String", "ANOTHER_PROPERTY", "\"$anotherProperty\"")
            }
        }
    }
}

tasks.named("preBuild") {
    dependsOn("updateBuildConfigFromLocalProperties")
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit - API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    //Hilt - Used for Dependence Injection
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1") //Do not change to kps, causing crash

    //Compose
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Retrofit - API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    //Navigation - Compose
    implementation("androidx.navigation:navigation-compose:2.7.3")

    //Room Dabatase
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2") // Do not change to kps, causing crash

}

