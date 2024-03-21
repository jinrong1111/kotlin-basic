package productInfo.repository

import productInfo.model.Inventory
import productInfo.service.InventoryService

class InventoryRepository(private val inventoryService: InventoryService) {
    suspend fun getInventories(): List<Inventory> {
        return inventoryService.getInventories()
    }
}