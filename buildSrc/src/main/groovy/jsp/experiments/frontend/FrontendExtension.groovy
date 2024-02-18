package jsp.experiments.frontend

import org.gradle.api.Incubating
import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

@Incubating
interface FrontendExtension {
    DirectoryProperty getFrontendDir()
    void frontendDir(File dir)
    void frontendDir(Directory dir)

    Property<String> getCleanTask()
    void cleanTask(String task)

    Property<String> getBuildTask()
    void buildTask(String task)

    Property<String> getDevServerTask()
    void devServerTask(String task)

    Property<String> getTestTask()
    void testTask(String task)

    RegularFileProperty getNpmPath()
    void npmPath(File path)
    void npmPath(RegularFile path)
}