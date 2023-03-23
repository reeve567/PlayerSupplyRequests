plugins {
	id("java")
}

group = "dev.reeve"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
	implementation("com.rabbitmq:amqp-client:5.16.0")
	implementation("com.google.code.gson:gson:2.10.1")
	
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	
	// bungeecord
	implementation("net.md-5:bungeecord-api:1.8.8-R0.4")
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}