<h1 align="center">
    BirdFlight artifact (apk | ipa/dsym) Upload 
</h1>

## Gradle Plugin  

This is a small gradle plugin to upload your apk to birdflight (Testbirds)  

## Usage  

#### build.gradle (top-level)  

```
buildscript {

    repositories {
      mavenCentral()
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
    compatiblity = "YOUR_APP_COMPATIBILITY"
    appBuildFileFullQualified = "/Users/.../YOURAPP/app/build/outputs/apk/debug/app-debug.apk"
    notes = "YOUR NOTES"
    isPublic = "true|false"
}
```

##### the upload

./gradlew bfupload
