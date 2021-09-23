<div align=center>
   <p style="font-size: 50px; font-weight: 600"> Daydream </p>
   <p>Daydream은 Tuinity의 Fork 버전으로 높은 성능 및 다양한 API 사용을 목표로 제작되었습니다.</p>
   <a href="https://caramel.moe">
      <img alt="caramel.moe" src="https://img.shields.io/badge/made%20by.-caramel.moe-red">
   </a>   
   <a href="https://github.com/LemonCaramel/Daydream-API/actions/workflows/daydream-api-build.yml">
      <img alt="Build Status" src="https://img.shields.io/github/workflow/status/LemonCaramel/Daydream-API/Build%20Daydream%20API">
   </a>
   <a href="https://discord.gg/f9qGtYF">
      <img alt="Discord" src="https://img.shields.io/badge/discord-discord.gg%2Ff9qGtYF-%237289da">
   </a>
</div>

- 모든 패치에는 `// Daydream` 주석이 필요합니다.
    - 필요에 따라 `// Daydream start` 와 `// Daydream end` 를 사용할 수도 있습니다.
- 코드는 브랜치 생성 후 작성하여 PR 을 넣은 후 검수 받아야합니다.
- 이 코드는 API만 포함되므로, 서버 측 패치는 Daydream-Server Repository 에서 Pull Request 해야합니다.

---

Maven 에서 "Daydream-API" 를 사용하는 경우:
```xml
<repository>
   <id>github</id>
   <name>GitHub Apache Maven Daydream-API Packages</name>
   <url>https://maven.pkg.github.com/LemonCaramel/Daydream-API</url>
   <releases><enabled>true</enabled></releases>
</repository>

<dependency>
    <groupId>moe.caramel</groupId>
    <artifactId>daydream-api</artifactId>
    <version>1.16.5-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

위 저장소를 사용할 경우 `{USER_HOME}/.m2/settings.xml` 의 설정이 필요합니다.
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>github</id>
            <username>유저 이름</username>
            <password>토큰</password>
        </server>
    </servers>
</settings>
```
자세한 설명은 [여기](https://github.com/TobseF/github-plugin-registry-example#enable-authentication)를 참고해주세요.
