<div align="center">

## Daydream

<p>Daydream은 Paper의 Fork로 높은 성능 및 다양한 API 사용을 목표로 제작되었습니다.</p>

[![caramel.moe](https://img.shields.io/badge/made%20by.-caramel.moe-red)](https://caramel.moe)
[![Build Status](https://img.shields.io/github/actions/workflow/status/LemonCaramel/Daydream-API/daydream-api-build-1.19.yml)](https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build-1.19.yml)
[![Discord](https://img.shields.io/discord/534586842079821824.svg?label=use%20server&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/f9qGtYF)

KOREAN, [ENGLISH](README_EN.md)

</div>

- 모든 패치에는 `// Daydream` 주석이 필요합니다.
    - 필요에 따라 `// Daydream start` 와 `// Daydream end` 를 사용할 수도 있습니다.
- 새로운 패치는 브랜치 생성(또는 포크) 후에 추가하여 Pull Request를 열고 검수 받아야합니다.
- 이 저장소는 API만 포함되므로, 서버 측 패치는 Daydream Repository 에서 Pull Request 해야합니다.

---

## 라이선스

모든 API 패치는 Patch 헤더에 라이선스가 명시되어 있지 않는 한, MIT 라이선스가 부여됩니다. (서버의 경우 GPL-3.0)

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
