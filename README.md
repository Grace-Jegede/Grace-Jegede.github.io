# perfit-kotlin-multiplatform

Built using Kotlin Multiplatform generator in IntelliJ IDEA.

This is a full stack web application with both server and client.

Server runs using Kotlin/JVM while client runs with Kotlin/JS.

Client easily runs in a web browser because Kotlin is transpiled to JavaScript, which is loaded directly in HTML.

Server side runs by compiling Kotlin on the Java Virtual Machine.

Server and Client communicate via Kotlin Multiplatform (Ktor).

Run the application in the IDE by selecting Gradle (sidebar on the top right) --> Tasks --> application --> run (hopefully this run configuration is already set up after cloning this repo).

Navigate to http://localhost:8080/ in your web browser while the app is running.

The webpage includes a text input field (generated using React, not JavaFX) that dynamically responds to different input.

## Notes for Deploying App on Heroku:

Root URL for the app is https://perfit-fitting-room.herokuapp.com/

Heroku searches for a Gradle Stage task by default. This needs to be modified using: $ heroku config:set GRADLE_TASK="installDist" --app perfit-fitting-room

Port environment variable is handled in perfit-kotlin-multiplatform/src/jvmMain/resources/application.conf
