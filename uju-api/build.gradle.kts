tasks {
    bootRun {
        mainClass.set("com.uju.springbootrestapi.ApiApplicationKt")
    }
    bootJar {
        mainClass.set("com.uju.springbootrestapi.ApiApplicationKt")
        enabled = true
    }
    jar {
        enabled = true
    }
}
dependencies {
    api(project(":uju-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.6.3")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.2")
}