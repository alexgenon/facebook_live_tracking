package be.aufildemescoutures.domain.sales

import be.aufildemescoutures.domain.live_tracking.core.customer.Customer
import be.aufildemescoutures.domain.live_tracking.core.product.Sku

data class InventoryItem(
    val sku: Sku, val quantity: Int, var itemsLeft: Int,
    val buyList: MutableList<Customer> = mutableListOf<Customer>(),
    val waitList: MutableList<Customer> = mutableListOf<Customer>()
) {
    fun itemBooked(buyer: Customer): Unit {
        if (itemsLeft > 0) {
            itemsLeft = itemsLeft - 1
            buyList.add(buyer)
        } else {
            throw UnsupportedOperationException("Cannot have negative items left")
        }
    }

    fun addToWaitList(buyer: Customer) {
        waitList.add(buyer)
    }

    fun cancelSale(buyer: Customer){
        buyList.removeIf { it == buyer }
        waitList.removeIf { it == buyer }
        TODO("Transfer from wait list to buy list")
    }

    // Quick and dirty constructor
    constructor(id: Int) : this(Sku(id, "", 0), 1, 1)
}
