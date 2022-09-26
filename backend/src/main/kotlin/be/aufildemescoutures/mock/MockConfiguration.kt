package be.aufildemescoutures.mock

import io.quarkus.arc.DefaultBean
import io.quarkus.arc.profile.IfBuildProfile
import org.jboss.logging.Logger
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

data class MockConfiguration(val minMessageDelay: Long, val maxMessageDelay: Long, val totalNumber: Int?)

@Dependent
class MockConfigurationProvider {
    private val LOG = Logger.getLogger(javaClass)
    @Produces
    @DefaultBean
    fun getDevConfig(): MockConfiguration {
        val mockConfiguration = MockConfiguration(1000, 5000, null)
        LOG.info("Mock Configuration for Dev mode: $mockConfiguration")
        return mockConfiguration
    }

    @Produces
    @IfBuildProfile("test")
    fun getTestConfig(): MockConfiguration {
        val mockConfiguration = MockConfiguration(100, 200, 150)
        LOG.info("Mock Configuration for test mode: $mockConfiguration")
        return mockConfiguration
    }
}

