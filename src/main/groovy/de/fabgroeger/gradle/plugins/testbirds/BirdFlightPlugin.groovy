package de.fabgroeger.gradle.plugins.testbirds

import org.gradle.api.Plugin
import org.gradle.api.Project

class BirdFlightPlugin implements Plugin<Project> {

    private static final String EXTENSION_NAME = 'birdflightuploadExtension'
    private static final String TASK_NAME = 'bfupload'

    void apply(Project project) {

        BirdFlightPluginExtension extension = project.extensions.create(EXTENSION_NAME, BirdFlightPluginExtension, project)

        project.task(TASK_NAME, type: BirdFlightUploadTask) { BirdFlightUploadTask task ->
                task.conventionMapping.apiKey = { extension.apiKey }
                task.conventionMapping.appKey = { extension.appKey }
                task.conventionMapping.id = { extension.id }
                task.conventionMapping.appVersion = { extension.appVersion }
                task.conventionMapping.compatiblity = { extension.compatiblity }
                task.conventionMapping.notes = { extension.notes }
                task.conventionMapping.isPublic = { extension.isPublic }
                task.conventionMapping.appBuildFile = { extension.appBuildFile }
                task.conventionMapping.appBuildFileFullQualified = { extension.appBuildFileFullQualified }
                task.conventionMapping.TB_UPLOAD_URL = { extension.TB_UPLOAD_URL }
                task.conventionMapping.TB_PROJ_ID_PLACER = { extension.TB_PROJ_ID_PLACER }
                task.conventionMapping.TB_APP_KEY_PLACER = { extension.TB_APP_KEY_PLACER }
        }
    }
}