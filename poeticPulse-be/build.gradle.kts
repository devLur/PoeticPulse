plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.sonarqube") version "4.4.1.3373"
	checkstyle
	jacoco
}

group = "com.devlu"
version = "0.0.1-SNAPSHOT"

sonar {
  properties {
    property("sonar.projectKey", "devLur_PoeticPulse")
    property("sonar.organization", "devlur")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

checkstyle {
	toolVersion = "10.17.0"
	configFile = rootProject.file("config/checkstyle/google_checkstyle.xml")
	maxErrors = 0
	maxWarnings = 0
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

val openFeignVersion = "13.2.1"
val testContainer = "1.16.0"
val flapdoodleEmbeddedMongo = "4.13.1"
val testcontainers = "1.19.8"
val wiremock = "3.7.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.github.openfeign:feign-okhttp:$openFeignVersion")
	implementation("io.github.openfeign:feign-jackson:$openFeignVersion")
	implementation("io.github.openfeign:feign-slf4j:$openFeignVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:testcontainers:$testcontainers")
	testImplementation("org.testcontainers:junit-jupiter:$testcontainers")
	testImplementation("org.testcontainers:mongodb:$testcontainers")
	testImplementation("org.wiremock:wiremock-standalone:$wiremock")
	testImplementation("io.github.openfeign:feign-mock:$openFeignVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
	dependsOn("test")
	reports{
		html.required = true
		html.outputLocation = layout.buildDirectory.dir("reports/jacoco")
	}
}

tasks.jacocoTestCoverageVerification{
	violationRules{
		rule {
			limit {
				minimum = "0.85".toBigDecimal()
			}
		}
	}
}