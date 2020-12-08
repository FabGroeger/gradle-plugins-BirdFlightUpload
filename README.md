<h1 align="center">
    BirdFlight artifact APK Upload 
</h1>

## Gradle Plugin  

This is a small gradle plugin to upload your apk to birdflight (Testbirds)  

## Usage  

#### mavenLocal

Add the jar to your local maven repo (https://github.com/FabGroeger/gradle-plugins-BirdFlightUpload/releases)

#### build.gradle (top-level)  

```
buildscript {

    repositories {
      maven {
            url "https://dl.bintray.com/fabgroeger/gradle-plugins-BirdFlightUpload/"
        }
      ...
    }
    dependencies {
        classpath 'de.fabgroeger.gradle.plugins:birdflightupload:4.2'
        ....
    }
}
```

#### build.gralde (app-specific)  

```
apply plugin: 'birdflightupload'

// Configure the extension
birdflightuploadExtension {
    apiKey = "YOUR_API_KEY"
    appKey = "YOUR_APP_KEY"
    id = "YOUR_APP_VERSION_ID"
    appVersion = "YOUR_APP_VERSION"
    notes = "YOUR NOTES"
    isPublic = "true|false"
    //optionally
    //httpProxyHost = "proxyhost.net"
    //httpProxyPort = 8080
    //httpsProxyHost = "proxyhost.net"
    //httpsProxyPort = 8080
}
```

#### the upload

The plugin will generate tasks for all variants of your android app.  
Simply append the variant to the task

```
./gradlew bfuploadFlavorDebug
```
