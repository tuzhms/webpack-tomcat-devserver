package jsp.experiments.dev.server

import org.apache.catalina.startup.Tomcat
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

abstract class DevServerTask extends DefaultTask {
    @Inject
    DevServerTask() { group = 'devServer' }

    @TaskAction
    void devServer() {
        def catalinaBase = project.layout.buildDirectory.dir('tomcat').get().asFile
        if (!catalinaBase.exists()) catalinaBase.mkdirs()

        def appBasePath = 'src/main/webapp'
        def appFullPath = project.layout.projectDirectory.dir(appBasePath).asFile.absolutePath

        def classesDir = project.layout.buildDirectory.dir('classes/java/main').get().asFile.toURI()
        def classLoader = new URLClassLoader(new URL[]{classesDir.toURL()}, this.class.classLoader)

        Tomcat tomcat = new Tomcat()
        try {
            tomcat.server.parentClassLoader = classLoader
            tomcat.server.catalinaBase = catalinaBase
            tomcat.server.catalinaHome = catalinaBase
            tomcat.port = 8080

            def context = tomcat.addWebapp "", appFullPath

            tomcat.addServlet '', 'proxy', new ProxyServlet(logger, 'http://localhost:3000', '/fresh')
            context.addServletMappingDecoded '/fresh/*', 'proxy'

            tomcat.start()
            tomcat.server.await()
        } catch (Exception e) {
            tomcat.stop()
            throw e
        } finally {
            tomcat.destroy()
        }
    }
}
