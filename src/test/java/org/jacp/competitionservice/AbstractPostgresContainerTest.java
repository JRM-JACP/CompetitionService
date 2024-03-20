package org.jacp.competitionservice;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

public abstract class AbstractPostgresContainerTest {
    private static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.4-alpine")
                .withExposedPorts(5432)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5434), new ExposedPort(5432)))))
                .withDatabaseName("competition")
                .withUsername("postgres")
                .withPassword("postgres")
                .withEnv("COMPETITION_DB_USER", "docker")
                .withEnv("COMPETITION_DB_PASSWORD", "docker")
                .withEnv("COMPETITION_DB_NAME", "competition")
                .withCopyFileToContainer(MountableFile.forHostPath("db/init/init-script.sql"), "/docker-entrypoint-initdb.d/init-script.sql");
        postgreSQLContainer.start();
    }
}
