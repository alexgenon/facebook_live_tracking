package be.aufildemescoutures.domain.sales

import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import be.aufildemescoutures.domain.live_tracking.core.product.Sku
import io.quarkus.vertx.ConsumeEvent
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

typealias Inventory = Map<Int, InventoryItem>

/*
Inventory service is a kind of fourre-tout right now
He's also acting as product catalog (the SKU is in the InventoryItem)
and as a sales record.
This is to keep the call flow simple and avoid minimalist services while we don't need them right now.
 */
@ApplicationScoped
class InventoryService {
    val LOG = Logger.getLogger(javaClass)

    /* Items specific for the active Live */
    var activeLive: Inventory? = null
    var salesHistory = mutableListOf<SaleItem>()

    fun newLive(liveId: String, itemsCount: Int) {
        LOG.info("Live $liveId starting now, inventory size is $itemsCount")
        activeLive = createBasicInventory(itemsCount)
    }

    @ConsumeEvent(LiveEvent.buyBusName)
    fun buyItem(comment: Comment) {
        try {
            val sku = getSkuFromId(comment.item)
            val saleItem = SaleItem(comment.timestamp, comment.user, sku)
            itemBooked(saleItem)
        } catch (e: Throwable) {
            salesHistory.add(
                SaleItem(
                    comment.timestamp,
                    comment.user,
                    Sku.NotFoundSku(comment.item),
                    SaleStatus.FAILED
                )
            )
            LOG.error("Error processing purchase of $comment")
        }
    }

    // right now the product catalog is in the inventory
    // not the best design but working around it by first asking the SKU and not considering the inventory status
    // as if the output would come from a distinct product catalog service.
    private fun getSkuFromId(skudId: Int):Sku = getInventoryItem(skudId).sku
    private fun getInventoryItem(id:Int):InventoryItem  {
        val item = activeLive?.get(id)
        if(item==null){
            val error = "Item requested item $id not found in inventory"
            LOG.error(error)
            throw InventoryException(error)
        }
        return item
    }

    private fun itemBooked(saleItem: SaleItem): InventoryItem {
        try {
            val item = getInventoryItem(saleItem.sku.id)
            if(item.itemsLeft >0) {
                LOG.debug("Item requested ${item.sku}, ${item.itemsLeft} left")
                item.itemBooked(saleItem.buyer)
                salesHistory.add(saleItem)
            } else {
                LOG.debug("Item requested ${item.sku} but out of stock, put on Waiting list")
                item.addToWaitList(saleItem.buyer)
                salesHistory.add(saleItem.copy(status = SaleStatus.WAITING_LIST))
            }
            return item
        } catch (inventoryException:InventoryException) {
            salesHistory.add(saleItem.copy(status = SaleStatus.FAILED))
            throw inventoryException
        }
    }

    fun currentSalesStatus() = activeLive?.values.orEmpty()

    companion object {
        fun createBasicInventory(size: Int) =
            (1..size)
                .map { it to InventoryItem(it) }
                .toMap()
    }
}

class InventoryException(s: String,cause: Throwable?=null) : Throwable() {
}
