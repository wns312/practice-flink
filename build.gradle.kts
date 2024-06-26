plugins {
    kotlin("jvm") version "1.9.23"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "jyk"
version = "1.0-SNAPSHOT"

val flinkVersion: String by project
val flinkShadowJar = "flinkShadowJar"

configurations {
    create(flinkShadowJar) {
        exclude(group = "org.apache.flink", module = "force-shading")
        exclude(group = "com.google.code.findbugs", module = "jsr305")
        exclude(group = "org.slf4j")
        exclude(group = "org.apache.logging.log4j")
    }
}

sourceSets {
    main {
        compileClasspath += configurations.getByName(flinkShadowJar)
        runtimeClasspath += configurations.getByName(flinkShadowJar)
    }
    test {
        compileClasspath += configurations.getByName(flinkShadowJar)
        runtimeClasspath += configurations.getByName(flinkShadowJar)
    }

}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.apache.flink:flink-streaming-java:$flinkVersion")
//    implementation("org.apache.flink:flink-java:$flinkVersion")
    implementation("org.apache.flink:flink-clients:${flinkVersion}")
//    implementation("org.apache.flink:flink-connector-kafka:${flinkVersion}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
    testImplementation(kotlin("test"))
//    testImplementation("org.apache.flink:flink-test-utils:$flinkVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.12")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}