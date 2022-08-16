package be.aufildemescoutures.mock

import io.quarkus.arc.DefaultBean
import io.quarkus.arc.profile.IfBuildProfile
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

data class MockConfiguration (val minMessageDelay:Long, val maxMessageDelay: Long, val totalNumber: Int?)

@Dependent
class MockConfigurationProvider{
    @Produces
    @DefaultBean
    fun getDevConfig()=MockConfiguration(1000, 5000,null)

    @Produces
    @IfBuildProfile("test")
    fun getTestConfig()=MockConfiguration(100,200,150)
}
