JRails and Books
================
This project contains two subprojects: jrails, which contains the framework code 
for the JRails library, and books, which contains the code for the books server.

## Gradle
Gradle is the current "state of the art" build and dependency tool in java.
This project uses a somewhat complex but very common setup for gradle. The top level 
project "p4" has a settings.gradle and a build.gradle as well as the "gradle wrapper,"
which will install gradle onto your system if it has not done so already. Each
project then also has its own build.gradle file that defines its own dependencies and gradle
tasks.

To see a list of gradle tasks run `./gradlew tasks`. 

To build and run the server run `./gradlew run`.

To run your unit tests run `./gradlew test`

## Gradle and Intellij
To import a gradle project into intellij follow the instructions [here](https://www.jetbrains.com/help/idea/gradle.html#).

Once imported you should see an elephant symbol and the word gradle on the right side
of the Intellij window. Clicking it will allow you to run gradle builds and tests through the IDE.

## Details Description
Implemented a web server that uses a model-view-controller architecture in Java, achieving the basic functions of MVC web app frameworks similar as Ruby on Rails.

Built a web framework, including a class JServer with a method that starts up an HTTP server on specific port,listens for user requests, and routes any request received then sends the result back to the web browser.  Designedthe controller methods in order to handle requests and returns a HTML page in response.

Implemented the primitive database functions by reflection, including storing, finding, listing all and destroyingdata in database.Wrote the test code to make sure the functions of the framework were completed.
