plugins {
    id("com.lovelysystems.gradle") version ("0.0.6")
}

lovely {
    gitProject()
    dockerProject("lovelysystems/jenkins")
    with(dockerFiles) {
        from("src/main/resources")
        from("localdev/Dockerfile") 
    }
}

group = "com.lovelysystems"

repositories {
    jcenter()
    mavenCentral()
    maven {
        setUrl("https://dl.bintray.com/kotlin/ktor")
    }
}

tasks {

    val writeVersion by creating {
        val out = file("VERSION.txt")
        outputs.file(out)
        out.writeText(project.version.toString())
    }

    val localDevDown by creating {
        group = "Development"
        description = "Stops local development Docker containers"
        doLast {
            exec {
                commandLine(
                    "docker-compose",
                    "-f",
                    "localdev/docker-compose.yml",
                    "down"
                )
            }
        }
    }

    val localDev by creating {
        group = "Development"
        description = "Starts local development based on Docker containers"
        dependsOn(localDevDown, "buildDockerImage")
        doLast {
            exec {
                commandLine(
                    "docker-compose",
                    "-f",
                    "localdev/docker-compose.yml",
                    "up",
                    "-d"
                )
            }
        }
    }
}
