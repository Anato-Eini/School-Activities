plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.client"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.client"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.fragment:fragment:1.7.1")
    implementation ("androidx.datastore:datastore-preferences-rxjava2:1.0.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.20")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("org.postgresql:postgresql:42.7.3")
}