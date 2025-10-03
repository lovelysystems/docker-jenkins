plugins {
    id("com.lovelysystems.gradle") version ("1.2.0")
}

lovely {
    gitProject()
    dockerProject("ghcr.io/lovelysystems/docker-jenkins")
    with(dockerFiles) {
        from("docker") 
    }
}

group = "com.lovelysystems"

tasks {

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
