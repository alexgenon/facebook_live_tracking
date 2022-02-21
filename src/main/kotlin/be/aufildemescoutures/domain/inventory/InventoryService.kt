package be.aufildemescoutures.domain.inventory

import be.aufildemescoutures.domain.inventory.InventoryItem
import javax.enterprise.context.ApplicationScoped

typealias Inventory = Map<Int,InventoryItem>

@ApplicationScoped
class InventoryService {
    // One Inventory for each live, an inventory is simply a map of inventory items
    // (map as, long term, it will be a key value store)
    val inventories = mutableMapOf<String, Inventory>()
    var activeLive: Inventory? = null

    public fun newLive(liveId: String, inventory: Inventory?) = inventories.put(liveId, inventory?:mutableMapOf())

    companion object{
        fun createInventory(size: Int) = (1 .. size).map { InventoryItem(it,"",0,1,1) }
    }
}
