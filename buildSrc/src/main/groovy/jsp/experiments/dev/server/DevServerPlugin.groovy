package jsp.experiments.dev.server


import org.gradle.api.Plugin
import org.gradle.api.Project

class DevServerPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register("start", DevServerTask) {
            dependsOn('classes')
        }
    }
}
