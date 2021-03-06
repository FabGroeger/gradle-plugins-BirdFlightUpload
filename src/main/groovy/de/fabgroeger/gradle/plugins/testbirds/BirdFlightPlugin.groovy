package de.fabgroeger.gradle.plugins.testbirds

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class BirdFlightPlugin implements Plugin<Project> {

    private static final String EXTENSION_NAME = 'birdflightuploadExtension'
    private static final String TASK_NAME = 'bfupload'

    void apply(Project project) {

        AppExtension androidExtension = project.extensions.getByType(AppExtension.class)

        BirdFlightPluginExtension extension = project.extensions.create(EXTENSION_NAME, BirdFlightPluginExtension, project)

        androidExtension.applicationVariants.all { variant ->

            String taskName = "${TASK_NAME}${variant.name.capitalize()}"
            println "Trying to add Task: ${taskName}"


            project.task("${taskName}", type: BirdFlightUploadTask) { BirdFlightUploadTask task ->

                task.conventionMapping.apiKey = { extension.apiKey }
                task.conventionMapping.appKey = { extension.appKey }
                task.conventionMapping.id = { extension.id }
                task.conventionMapping.appVersion = { extension.appVersion }
                task.conventionMapping.compatiblity = { extension.compatiblity }
                task.conventionMapping.notes = { extension.notes + "\n" + variant.outputs[0].outputFile.name }
                task.conventionMapping.isPublic = { extension.isPublic }
                task.conventionMapping.appBuildFileFullQualified = { variant.outputs[0].outputFile }
                task.conventionMapping.TB_UPLOAD_URL = { extension.TB_UPLOAD_URL }
                task.conventionMapping.TB_PROJ_ID_PLACER = { extension.TB_PROJ_ID_PLACER }
                task.conventionMapping.TB_APP_KEY_PLACER = { extension.TB_APP_KEY_PLACER }

                task.conventionMapping.httpProxyHost = { extension.httpProxyHost }
                task.conventionMapping.httpProxyPort = { extension.httpProxyPort }
                task.conventionMapping.httpsProxyHost = { extension.httpsProxyHost }
                task.conventionMapping.httpsProxyPort = { extension.httpsProxyPort }

                println "\nTask ${TASK_NAME}${variant.name.capitalize()} added for artifact:"
                println variant.name.capitalize()
                println variant.outputs[0].outputFile
                println "\n\n"

            }

        }

    }
}