plugins {
	id("java")
}

group = "dev.reeve"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	mavenLocal()
	maven("https://packages.confluent.io/maven/")
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
	
	implementation("com.google.code.gson:gson:2.10.1")
	
	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	
	// message brokers
	implementation("com.rabbitmq:amqp-client:5.16.0")
	implementation("org.apache.kafka:kafka-clients:7.0.1-ccs")
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}