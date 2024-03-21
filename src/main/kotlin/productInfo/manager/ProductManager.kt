package productInfo.manager

import productInfo.model.Inventory
import productInfo.repository.InventoryRepository
import productInfo.repository.ProductRepository

class ProductManager(
    private val productRepository: ProductRepository,
    private val inventoryRepository: InventoryRepository
) {
    fun calculateTotalQuantity(sku: String, inventories: List<Inventory>): Int {
        return inventories.filter { it.sku == sku }.sumOf { it.quantity }
    }

}
