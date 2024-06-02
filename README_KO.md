<div align="center">

## Daydream (API Only)

<p>Daydream은 Paper의 Fork로 다양한 API 사용을 목표로 제작되었습니다.</p>

[![caramel.moe](https://img.shields.io/badge/made%20by.-caramel.moe-red)](https://caramel.moe)
[![Build Status](https://img.shields.io/github/actions/workflow/status/LemonCaramel/Daydream-API/daydream-api-build-1.20.yml)](https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build-1.20.yml)
[![Discord](https://img.shields.io/discord/534586842079821824.svg?label=use%20server&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/f9qGtYF)

[ENGLISH](README.md), KOREAN

</div>

## 라이선스

모든 **API 패치**는 Patch 헤더에 라이선스가 명시되어 있지 않은 한 MIT 라이선스가 부여됩니다.

Daydream은 Upstream 프로젝트로부터 라이선스를 상속받습니다.

[PaperMC/Paper](https://github.com/PaperMC/Paper)

## API

### Dependency 정보
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
    <version>1.20.6-R0.1-SNAPSHOT</version>
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
    compileOnly("moe.caramel", "daydream-api", "1.20.6-R0.1-SNAPSHOT")
}
```
