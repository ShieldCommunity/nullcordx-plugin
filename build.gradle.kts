plugins {
    id("java")
}

group = "net.shieldcommunity"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://repo.shieldcommunity.net/repository/maven-public/")
    maven(url = "https://libraries.minecraft.net/")
}

dependencies {
    compileOnly(libs.nullcordx)

    compileOnly(rootProject.libs.lombok)
    annotationProcessor(rootProject.libs.lombok)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.processResources {
    val props = mapOf("version" to rootProject.version)

    inputs.properties(props)
    filteringCharset = "UTF-8"

    filesMatching("bungee.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveBaseName.set("NullCordXPlugin")
    archiveClassifier.set("")
    archiveVersion.set("")
}