package com.pientaa.hibernatedemo.config

import io.kotest.core.spec.Spec
import io.kotest.extensions.testcontainers.perSpec
import org.flywaydb.core.Flyway
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object TestContainersUtil {
    private val POSTGRES_IMAGE: DockerImageName = DockerImageName.parse("postgres:12")

    val testFlyway: Flyway
        get() = Flyway.configure()
            .dataSource("jdbc:tc:postgresql:12:///test_db", "test_user", "test_password")
            .load()

    fun Spec.attachBasicPostgresContainer(): PostgreSQLContainer<Nothing> {
        val postgresContainer = PostgreSQLContainer<Nothing>(POSTGRES_IMAGE).apply {
            withDatabaseName("test_db")
            withUsername("test_user")
            withPassword("test_password")
        }

        listener(postgresContainer.perSpec())
        return postgresContainer
    }
}