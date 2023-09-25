import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The Daydream project directory is not a properly cloned Git repository.
         
         In order to build Daydream from source you must clone
         the Daydream repository using Git, not download a code
         zip from GitHub.
         
         See https://github.com/caramel-moe/Daydream-API/blob/master/CONTRIBUTING.md
         for further information on building and modifying Daydream.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "daydream"

for (name in listOf("Daydream-API", "Daydream-Dummy")) {
    val projName = name.toLowerCase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
