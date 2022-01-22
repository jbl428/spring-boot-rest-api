tasks {
    bootRun {
        mainClass.set("com.uju.springbootrestapi.ApiApplicationKt")
    }
    bootJar {
        enabled = true
    }
    jar {
        enabled = true
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.6.3")
    implementation("org.springframework.boot:spring-boot-devtools")
}