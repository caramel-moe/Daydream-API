<div align="center">

## Daydream

<p>Daydream is Paper's Fork with the aim of high performance and using various API.</p>

[![caramel.moe](https://img.shields.io/badge/made%20by.-caramel.moe-red)](https://caramel.moe)
[![Build Status](https://img.shields.io/github/workflow/status/LemonCaramel/Daydream-API/Build%20Daydream%20API%20(1.18))](https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build-1.18.yml)
[![Discord](https://img.shields.io/discord/534586842079821824.svg?label=use%20server&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/f9qGtYF)

[KOREAN](README.md), ENGLISH

</div>

- All patches require `// Daydream` annotations.
    - You can also use `// Daydream start` and `// Daydream end` as needed.
- New patches should be added after branch creation (or fork) to open the Pull Request and be inspected.
- Since this repository contains only APIs, server-side patches must be requested in the Daydream Repository.

---

## License

All API patches are licensed under the MIT license, unless otherwise noted in the patch headers. (Server is GPL-3.0)

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
    <version>1.19.4-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

Gradle KTS
```kotlin
repositories {
    // caramel.moe Repository
    maven("https://repo.caramel.moe/repository/maven-public/")
    // PaperMC Repository
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    // Daydream API
    compileOnly("moe.caramel", "daydream-api", "1.19.4-R0.1-SNAPSHOT")
}
```
