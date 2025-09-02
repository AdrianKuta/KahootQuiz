import com.android.build.gradle.LibraryExtension
import dev.adriankuta.kahootquiz.configureCompose
import dev.adriankuta.kahootquiz.configureDetektForComposeModuleExceptions
import dev.adriankuta.kahootquiz.configureInstrumentation
import dev.adriankuta.kahootquiz.configureLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

@Suppress("unused") // This is called as a string in the gradle plugin block
internal class AndroidLibraryComposeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureLibrary()

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureCompose(extension)
            configureInstrumentation()
            configureDetektForComposeModuleExceptions()
        }
    }
}

