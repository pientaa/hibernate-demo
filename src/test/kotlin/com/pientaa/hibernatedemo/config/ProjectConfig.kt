package com.pientaa.hibernatedemo.config

import com.pientaa.hibernatedemo.config.TestContainersUtil.attachBasicPostgresContainer
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import java.io.File
import java.sql.Connection

internal object TestContainersProjectConfig : AbstractProjectConfig() {
    override fun extensions(): List<Extension> =
        listOf(
            SpringExtension,
            SpringTestExtension(SpringTestLifecycleMode.Test),
            TestContainerListener,
            DatabaseCleanUpListener
        )
}

object TestContainerListener : TestListener {
    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        spec.attachBasicPostgresContainer()
    }
}

object DatabaseCleanUpListener : TestListener {
    private val connection: Connection = TestContainersUtil.testFlyway.configuration.dataSource.connection
    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        clearAllTables(connection)
        super.afterTest(testCase, result)
    }

    override suspend fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        runImportSql(connection)
    }
}

private fun clearAllTables(connection: Connection) {
    connection.createStatement().execute(
        """
                DO LANGUAGE plpgsql ${'$'}${'$'}
                    DECLARE statements CURSOR FOR SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
                    BEGIN
                        FOR s IN statements LOOP
                                EXECUTE 'TRUNCATE TABLE ' || s.table_name || ' CASCADE;';
                            END LOOP;
                    END ${'$'}${'$'};
            """.trimIndent()
    )
}

private fun runImportSql(connection: Connection) {
    connection.createStatement()
        .execute(File("src/main/resources/import.sql").readText())
}
