package jsp.experiments.frontend

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property

class FrontendPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def taskName = {
            String name = it instanceof Property<String> ? it.get() : it
            "npmRun${capitalize(name)}"
        }

        def extension = project.extensions.create(FrontendExtension, 'frontend', DefaultFrontendExtension) as DefaultFrontendExtension
        extension.cleanTask.convention('clean')
        extension.buildTask.convention('build')
        extension.devServerTask.convention('start')
        extension.testTask.convention('test')

        project.tasks.register('npmInstall', NpmTask) {
            command = ['install']

            mustRunAfter(taskName(extension.cleanTask.get()))
        }

        project.afterEvaluate {
            PackageJsonUtils.packageJsonScripts(extension.frontendDir.get()).each { script ->
                project.tasks.register(taskName(script.key), NpmTask) {
                    command = ['run', script.key]
                }
            }

            project.tasks.named(taskName(extension.buildTask)).get()
                    .dependsOn(taskName(extension.cleanTask), 'npmInstall')

            project.tasks.named(taskName(extension.devServerTask)).get()
                    .dependsOn(taskName(extension.cleanTask), 'npmInstall')

            project.tasks.named('clean').get()
                    .dependsOn(taskName(extension.cleanTask))

            project.tasks.named('war').get()
                    .dependsOn(taskName(extension.buildTask))

            project.tasks.named('test').get()
                    .dependsOn(taskName(extension.testTask))
        }
    }

    private static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name
        }
        char[] chars = name.toCharArray()
        chars[0] = Character.toUpperCase(chars[0])
        return new String(chars)
    }
}