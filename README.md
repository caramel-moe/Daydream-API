<div align="center">

## Daydream (API Only)

<p>Daydream is Paper's Fork with the aim of using various API.</p>

[![caramel.moe](https://img.shields.io/badge/made%20by.-caramel.moe-red)](https://caramel.moe)
[![Build Status](https://img.shields.io/github/actions/workflow/status/LemonCaramel/Daydream-API/daydream-api-build-1.21.yml)](https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build-1.21.yml)
[![Discord](https://img.shields.io/discord/534586842079821824.svg?label=use%20server&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/f9qGtYF)

ENGLISH, [KOREAN](README_KO.md)

</div>

## License

All **API patches** are licensed under the MIT license, unless otherwise noted in the patch headers.

Daydream inherits its licensing from upstream projects.

[PaperMC/Paper](https://github.com/PaperMC/Paper)

## API

### Dependency Information
Maven
```xml
<repository>
    <id>caramel-repo</id>
    <url>https://repo.caramel.moe/repository/maven-public/</url>
</repository>
<repository>
    <id>papermc</id>
    <url>https://papermc.io/repo/repository/maven-public/</url>
</repository>

<dependency>
    <groupId>moe.caramel</groupId>
    <artifactId>daydream-api</artifactId>
    <version>1.21.3-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

Gradle
```kotlin
repositories {
    // caramel.moe Repository
    maven("https://repo.caramel.moe/repository/maven-public/")
    // PaperMC Repository
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    // Daydream API
    compileOnly("moe.caramel", "daydream-api", "1.21.3-R0.1-SNAPSHOT")
}
```
