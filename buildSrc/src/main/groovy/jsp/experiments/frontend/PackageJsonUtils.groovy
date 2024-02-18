package jsp.experiments.frontend

import groovy.json.JsonSlurper
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile

class PackageJsonUtils {
    static RegularFile packageJson(Directory frontendDir) {
        if (!frontendDir.asFile.exists())
            throw new FrontendException("Не найдена директория указанная в frontend.frontendDir: $frontendDir")
        if (!frontendDir.asFile.isDirectory())
            throw new FrontendException("Указанный в frontend.frontendDir путь не является директорией: $frontendDir")
        def packageJson = frontendDir.file 'package.json'

        if (!packageJson.asFile.exists())
            throw new FrontendException("В директории frontend.frontendDir не найден package.json: $packageJson")

        packageJson
    }

    static Map<String, ?> packageJsonScripts(Directory frontendDir) {
        new JsonSlurper().parse(packageJson(frontendDir).asFile).scripts
    }
}
