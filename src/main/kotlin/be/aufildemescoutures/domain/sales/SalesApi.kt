package be.aufildemescoutures.domain.sales

import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("sales")
class SalesApi {
    @Inject
    @field:Default
    lateinit var inventoryService: InventoryService

    @GET
    fun getSales() = inventoryService.currentSalesStatus()

    @GET
    @Path("as_string")
    fun getSalesAsString() = inventoryService
        .currentSalesStatus()
        .map { item -> "${item.sku.id}: ${item.buyList.map { it.fullName() }.joinToString(",")} ${item.waitList.map {it.fullName()}.joinToString(","," - ")}" }
        .joinToString("\n")
}
