package de.fabgroeger.gradle.plugins.testbirds

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.MultipartEntity
import org.apache.http.entity.mime.content.InputStreamBody
import org.apache.http.entity.mime.content.StringBody
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

class BirdFlightUploadTask extends DefaultTask {

    @Input
    String apiKey

    @Input
    String appKey

    @Input
    String id

    @Input
    String appVersion

    @Input
    String compatiblity

    @Input
    String notes

    @Input
    String isPublic

    @Input
    File appBuildFileFullQualified

    @Input
    String TB_UPLOAD_URL

    @Input
    String TB_PROJ_ID_PLACER

    @Input
    String TB_APP_KEY_PLACER

    String TB_BASE_URL = "https://www.birdflightapp.com"

    @TaskAction
    void uploadInternal(){

        //check config iOS/Android
        if(getCompatiblity() != "Android" && getCompatiblity() != "iOS"){
            throw new RuntimeException("Compatiblility is neither 'Android' nor 'iOS' - what is the type?")
        }

        //check for defined files ("...or may use full qualified path")


        //upload
        String modUrl = getTB_UPLOAD_URL().replace(getTB_APP_KEY_PLACER(), getApiKey())
        modUrl = modUrl.replace(getTB_PROJ_ID_PLACER(), getAppKey())
        print "new URL: $modUrl"
        sendMultiPartFile(modUrl)

    }

    void sendMultiPartFile(modUrl) {

        def http = new HTTPBuilder(modUrl)

        http.request(Method.POST) { req ->

            requestContentType: "multipart/form-data"

            MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

            multiPartContent.addPart("version", new StringBody(getAppVersion()));
            multiPartContent.addPart("notes", new StringBody(getNotes()));
            multiPartContent.addPart("compatibility", new StringBody(getCompatiblity()));
            multiPartContent.addPart("public", new StringBody(getIsPublic()));
            multiPartContent.addPart("id", new StringBody(getId()));

            multiPartContent.addPart("appBuildFile", new InputStreamBody(appBuildFileFullQualified.newInputStream(), appBuildFileFullQualified.name))

            req.setEntity(multiPartContent)


            response.success = { resp ->
                // response handling
                println resp.statusLine.statusCode

                if (resp.statusLine.statusCode == 200) {
                    String respStr = "${resp?.entity?.content?.text}"
                    print "Success: ${respStr}"

                    def jsonSlurper = new JsonSlurper()

                    println "slupers: "
                    def object = jsonSlurper.parseText(respStr)

                    println "\n\nArtifact URL:  ${TB_BASE_URL}${object.files.url}"

                }
                else{
                    print"Error: [${resp?.entity?.content?.text}"
                }
            }
        }
    }

}