package jsp.experiments.frontend

import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

import javax.inject.Inject

abstract class DefaultFrontendExtension implements FrontendExtension {
    private final DirectoryProperty frontendDir = objectFactory.directoryProperty()
    private final Property<String> cleanTask = objectFactory.property(String)
    private final Property<String> buildTask = objectFactory.property(String)
    private final Property<String> devServerTask = objectFactory.property(String)
    private final Property<String> testTask = objectFactory.property(String)
    private final RegularFileProperty npmPath = objectFactory.fileProperty()

    @Inject
    abstract ObjectFactory getObjectFactory()

    @Override
    DirectoryProperty getFrontendDir() {
        return frontendDir
    }

    @Override
    void frontendDir(File dir) {
        frontendDir.set(dir)
    }

    @Override
    void frontendDir(Directory dir) {
        frontendDir.set(dir)
    }

    @Override
    Property<String> getCleanTask() {
        return cleanTask
    }

    @Override
    void cleanTask(String task) {
        cleanTask.set(task)
    }

    @Override
    Property<String> getBuildTask() {
        return buildTask
    }

    @Override
    void buildTask(String task) {
        buildTask.set(task)
    }

    @Override
    Property<String> getDevServerTask() {
        return devServerTask
    }

    @Override
    void devServerTask(String task) {
        devServerTask.set(task)
    }

    @Override
    Property<String> getTestTask() {
        return testTask
    }

    @Override
    void testTask(String task) {
        testTask.set(task)
    }

    @Override
    RegularFileProperty getNpmPath() {
        return npmPath
    }

    @Override
    void npmPath(File path) {
        npmPath.set(path)
    }

    @Override
    void npmPath(RegularFile path) {
        npmPath.set(path)
    }
}
