<h1 align="center">
    BirdFlight artifact (apk | ipa/dsym) Upload 
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
      mavenLocal()
      ...
    }
    dependencies {
        classpath group: 'de.fabgroeger.gradle.plugins', name: 'testbirdsappupload', version: '2.0-SNAPSHOT'
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
}
```

##### the upload

The plugin will generate tasks for all variants of your android app.  
Simply append the variant to the task

```
./gradlew bfuploadFlavorDebug
```
