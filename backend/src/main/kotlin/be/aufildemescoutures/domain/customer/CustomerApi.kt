package be.aufildemescoutures.domain.customer

import org.jboss.logging.Logger
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("customers")
class CustomerApi {
    private var LOG= Logger.getLogger(javaClass)

    @Inject
    @field:Default
    lateinit var customerService: CustomerService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun allCustomers()=customerService.customerRepository.getAllCustomers()

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun customerWithName(name:String) = customerService.customerRepository.getByName(name)
}
