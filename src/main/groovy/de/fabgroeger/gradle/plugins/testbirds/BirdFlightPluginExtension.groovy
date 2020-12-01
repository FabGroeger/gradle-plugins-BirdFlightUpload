package de.fabgroeger.gradle.plugins.testbirds

import org.gradle.api.Project

class BirdFlightPluginExtension {
    String TB_UPLOAD_URL = "https://www.birdflightapp.com/apps/{{TB_PROJECT_ID}}/app-builds/new.json?apikey={{API_KEY}}"
    String apiKey = ""
    String appKey = ""
    String id = "0"
    String appVersion = "0"
    String compatiblity = "Android"
    String notes = ""
    //seriazlized as 'public' only
    String isPublic = "true"
    final String TB_PROJ_ID_PLACER = "{{TB_PROJECT_ID}}"
    final String TB_APP_KEY_PLACER = "{{API_KEY}}"

    Project project

    BirdFlightPluginExtension(Project project) {
        this.project = project
    }


}