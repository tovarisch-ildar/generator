plugins {
    application
    java
    kotlin("jvm") version "1.5.10"
    id("org.openapi.generator") version "5.1.1"
    id("com.google.cloud.tools.jib") version "3.1.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClassName = "ru.jb.po.POGeneratorKT"
}

openApiGenerate {
    generatorName.value("java")
    inputSpec.value("$rootDir/src/main/resources/youtrack.openapi.json")
    outputDir.value("$buildDir/generated")
    apiPackage.value("ru.youtrack.api")
    invokerPackage.value("ru.youtrack.invoker")
    modelPackage.value("ru.youtrack.model")
    configOptions.value(
        mapOf(
            "dateLibrary" to "java8"
        )
    )

}

jib {
    from {
        image = "openjdk:11"
    }

    to {
        image = "ildaryap/generator:1"
    }

}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("io.swagger:swagger-annotations:1.6.3")
    implementation("io.gsonfire:gson-fire:1.8.5")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
}

tasks.named("compileKotlin") {
    dependsOn(":openApiGenerate")
}

sourceSets {
    main {
        java {
            srcDir(files("${openApiGenerate.outputDir.get()}/src/main/java"))
        }
    }
}