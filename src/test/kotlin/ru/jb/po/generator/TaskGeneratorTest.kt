package ru.jb.po.generator

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import ru.youtrack.api.DefaultApi
import ru.youtrack.model.Project
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension

@ExtendWith(SystemStubsExtension::class)
internal class TaskGeneratorTest {

    @SystemStub
    val environmentVariables = EnvironmentVariables()

    @BeforeEach
    fun setUp() {
        environmentVariables.set("MAX_TASKS", "5")
        environmentVariables.set("MAX_COMMENTS", "5")
        environmentVariables.set("YOUTRACK_URL", "http://localhost:8080/api")
        environmentVariables.set("YOUTRACK_TOKEN", "token")
    }

    @Test
    fun testRun() {
        val mock = mock(DefaultApi::class.java)
        val project = Project()
        `when`(mock.adminProjectsGet(any(), any(), any())).thenReturn(listOf(project))

        TaskGenerator(mock).run();

        verify(mock, atLeastOnce()).adminProjectsGet("id,name", null, null)
        verify(mock, atLeastOnce()).issuesPost(any(), any(), any())
    }
}