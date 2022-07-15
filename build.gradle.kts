import io.papermc.paperweight.util.constants.PAPERCLIP_CONFIG
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.patcher") version "1.3.7"
}

val caramelMavenPublicUrl = "https://repo.caramel.moe/repository/maven-public";
val paperMavenPublicUrl = "https://papermc.io/repo/repository/maven-public/"
repositories {
    mavenCentral()
    maven(paperMavenPublicUrl) {
        content { onlyForConfigurations(PAPERCLIP_CONFIG) }
    }
}

dependencies {
    remapper("net.fabricmc:tiny-remapper:0.8.2:fat")
    paperclip("io.papermc:paperclip:3.0.2")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }
    tasks.withType<Test> {
        testLogging {
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
            events(TestLogEvent.STANDARD_OUT)
        }
    }

    repositories {
        mavenCentral()
        maven(caramelMavenPublicUrl) // Daydream
        maven(paperMavenPublicUrl)
    }

    configure<PublishingExtension> {
        repositories.maven {
            name = "maven"
            url = uri("https://repo.caramel.moe/repository/maven-snapshots/")
            credentials {
                username = System.getenv("DEPLOY_ID")
                password = System.getenv("DEPLOY_PW")
            }
        }
    }
}

paperweight {
    serverProject.set(project(":daydream-dummy"))

    remapRepo.set(paperMavenPublicUrl)
    decompileRepo.set(paperMavenPublicUrl)

    usePaperUpstream(providers.gradleProperty("paperCommit")) {
        withPaperPatcher {
            apiPatchDir.set(layout.projectDirectory.dir("patches"))
            apiOutputDir.set(layout.projectDirectory.dir("Daydream-API"))
        }

        reobfPackagesToFix.add("moe.caramel")
    }
}
