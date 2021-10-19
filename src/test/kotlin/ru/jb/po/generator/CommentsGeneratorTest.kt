package ru.jb.po.generator

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import ru.youtrack.api.DefaultApi
import ru.youtrack.model.Issue
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension
import kotlin.test.Test

@ExtendWith(SystemStubsExtension::class)
internal class CommentsGeneratorTest {

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
        val issue = Issue()
        `when`(mock.issuesGet(any(), any(), any(), any())).thenReturn(listOf(issue))

        CommentsGenerator(mock).run()

        verify(mock, atLeastOnce()).issuesGet(null, "id", null, null)
        verify(mock, atLeastOnce()).issuesIdCommentsPost(any(), any(), any(), any())
    }

}