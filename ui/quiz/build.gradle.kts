plugins {
    alias(libs.plugins.kahootquiz.android.library.compose)
    alias(libs.plugins.kahootquiz.android.library.hilt)
}

android {
    namespace = "dev.adriankuta.kahootquiz.ui.quiz"
}

dependencies {
    implementation(projects.domain)
}
