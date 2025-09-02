import dev.adriankuta.kahootquiz.configureDetektForNonUiModule
import dev.adriankuta.kahootquiz.configureLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused") // This is called as a string in the gradle plugin block
internal class AndroidLibraryConvention : Plugin<Project> {
    override fun apply(target: Project) {
        target.configureLibrary()
        target.configureDetektForNonUiModule()
    }
}
