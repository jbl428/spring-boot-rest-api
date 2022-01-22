tasks {
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-test:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.3")
    runtimeOnly("com.h2database:h2:1.4.200")
}