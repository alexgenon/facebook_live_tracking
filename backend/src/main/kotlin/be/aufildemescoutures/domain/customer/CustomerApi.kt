package be.aufildemescoutures.domain.customer

import org.jboss.logging.Logger
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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
