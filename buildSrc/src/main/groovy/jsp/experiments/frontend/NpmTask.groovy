package jsp.experiments.frontend

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.process.internal.ExecActionFactory

import javax.inject.Inject

abstract class NpmTask extends DefaultTask {
    @Input
    List<String> command;

    @Inject
    NpmTask() { group = 'npm' }

    @Inject
    abstract ExecActionFactory getExecActionFactory()

    @TaskAction
    void runNpm() {
        FrontendExtension frontendExtension = project.extensions.getByName("frontend") as FrontendExtension
        def frontendDir = frontendExtension.frontendDir.get()
        PackageJsonUtils.packageJson frontendDir

        def out = new ByteArrayOutputStream();
        def fullCommand = ['npm']
        fullCommand.addAll(command)

        def execAction = execActionFactory.newExecAction()
        execAction.setWorkingDir(frontendDir)
        execAction.setCommandLine(fullCommand)
        execAction.setStandardOutput(out)
        def result = execAction.execute()

        println out
        result.assertNormalExitValue()
    }
}
