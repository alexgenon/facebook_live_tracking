package be.aufildemescoutures.domain.customer

import be.aufildemescoutures.domain.core.customer.Customer
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerRepository {
    private val customers = mutableSetOf<Customer>()
    fun getAllCustomers() = customers 
    fun getByName(name:String) = customers.find { it.fullName() == name}
    fun newCustomer(customer: Customer) = customers.add(customer)
}
