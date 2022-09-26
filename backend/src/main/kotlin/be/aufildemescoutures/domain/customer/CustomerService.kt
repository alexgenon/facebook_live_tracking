package be.aufildemescoutures.domain.customer

import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.customer.Customer
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class CustomerService {
    private var LOG = Logger.getLogger(javaClass)

    @Inject
    @field:Default
    lateinit var customerRepository: CustomerRepository

    // Listens to all new comment and greedily collects the new Customer
    @ConsumeEvent(LiveEvent.newComment)
    fun newComment(comment: Comment) {
        LOG.debug("New comment $comment, adding its customer to the customer repository")
        customerRepository.newCustomer(comment.user)
    }

    fun findCustomer(name: String): Customer? = customerRepository.getByName(name)
}
