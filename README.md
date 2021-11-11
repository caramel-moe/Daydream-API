<div align="center">

## Daydream

<p>Daydream은 Paper 및 Airplane의 Fork로 높은 성능 및 다양한 API 사용을 목표로 제작되었습니다.</p>

[![caramel.moe](https://img.shields.io/badge/made%20by.-caramel.moe-red)](https://caramel.moe)
[![Build Status](https://img.shields.io/github/workflow/status/LemonCaramel/Daydream-API/Build%20Daydream%20API%20(1.17))](https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build-1.17.yml)
[![Discord](https://img.shields.io/discord/534586842079821824.svg?label=use%20server&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/f9qGtYF)

KOREAN, [ENGLISH](README_EN.md)

</div>

- 모든 패치에는 `// Daydream` 주석이 필요합니다.
    - 필요에 따라 `// Daydream start` 와 `// Daydream end` 를 사용할 수도 있습니다.
- 새로운 패치는 브랜치 생성(또는 포크) 후에 추가하여 Pull Request를 열고 검수 받아야합니다.
- 이 저장소는 API만 포함되므로, 서버 측 패치는 Daydream Repository 에서 Pull Request 해야합니다.

---

## 라이선스

모든 API 패치는 Patch 헤더에 라이선스가 명시되어 있지 않는 한, MIT 라이선스가 부여됩니다.

Daydream은 Upstream 프로젝트로부터 라이선스를 상속받습니다.

[PaperMC/Paper](https://github.com/PaperMC/Paper), [Airplane](https://github.com/TECHNOVE/Airplane)

## API

### Dependency 정보
Maven
```xml
<repository>
    <id>caramel-repo</id>
    <url>https://repo.caramel.moe/repository/maven-public/</url>
</repository>

<dependency>
    <groupId>moe.caramel</groupId>
    <artifactId>daydream-api</artifactId>
    <version>1.17.1-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

Gradle KTS
```kotlin
repositories {
    // caramel.moe Repository
    maven("https://repo.caramel.moe/repository/maven-public/")
}

dependencies {
    // Daydream API
    compileOnly("moe.caramel", "daydream-api", "1.17.1-R0.1-SNAPSHOT")
}
```