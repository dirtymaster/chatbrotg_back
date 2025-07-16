plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.hibernate.orm") version "6.5.2.Final"
    id("org.graalvm.buildtools.native") version "0.10.2"
    id("org.openapi.generator") version "6.0.1"
}

group = "com.radmize"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    implementation("org.mindrot:jbcrypt:0.4")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.openapitools:openapi-generator:5.3.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


//hibernate {
//    enhancement {
//        enableAssociationManagement = true
//    }
//}

//graalvmNative {
//    binaries {
//        named("main") {
//            buildArgs.add("-H:ReflectionConfigurationFiles=reflect-config.json")
//        }
//    }
//}

// openapi generator
openApiGenerate {
    generatorName.set("java") // Выбор генератора, например, д��я генерации Java-сервера
    inputSpec.set("$projectDir/src/main/resources/openapi.yaml") // Укажите путь к вашему OpenAPI спецификации
    outputDir.set("$projectDir/build/generated") // Путь к директории, куда будет сгенерирован код
    apiPackage.set("com.radmize.chatbrotg.api") // Укажите нужный пакет для сгенерированного API-кода
    modelPackage.set("com.radmize.chatbrotg.model") // Укажите нужный пакет для моделей
    invokerPackage.set("com.radmize.chatbrotg.example.invoker") // Укажите нужный пакет для invoker-объектов

    additionalProperties.set(
        mapOf(
            "dateLibrary" to "java8", // Использование java.time
            "java8" to "true" // Подключение поддержки Java 8
        )
    )
    typeMappings.set(mapOf("OffsetDateTime" to "Instant"))
    importMappings.set(mapOf("java.time.OffsetDateTime" to "java.time.Instant"))

}

sourceSets {
    main {
        java {
            srcDir("$projectDir/build/generated/src/main/java")
        }
    }
}

tasks {
    compileJava {
        dependsOn("openApiGenerate")
    }
}
