import io.papermc.paperweight.util.constants.PAPERCLIP_CONFIG

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.patcher") version "1.2.0"
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
    remapper("net.fabricmc:tiny-remapper:0.6.0:fat")
    paperclip("io.papermc:paperclip:2.0.1")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
    }
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(16)
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
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
    serverProject.set(project(":Daydream-Dummy"))

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
