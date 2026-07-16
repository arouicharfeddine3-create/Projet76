// Top-level build file
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // أضف classpath لـ KSP (يجب أن يتوافق مع إصدار Kotlin)
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.0-1.0.13")
        // أو استخدم الصيغة: classpath("com.google.devtools.ksp:ksp-gradle-plugin:1.9.0-1.0.13")
        // تأكد من وجود الإصدار المناسب حسب إصدار Kotlin 1.9.0
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
