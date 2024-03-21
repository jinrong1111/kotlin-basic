package productInfo.manager

import productInfo.model.Inventory
import productInfo.model.ProductType
import productInfo.repository.InventoryRepository
import productInfo.repository.ProductRepository

class ProductManager(
    private val productRepository: ProductRepository,
    private val inventoryRepository: InventoryRepository
) {
    fun calculateTotalQuantity(sku: String, inventories: List<Inventory>): Int {
        return inventories.filter { it.sku == sku }.sumOf { it.quantity }
    }

    fun calculatePrice(type: ProductType, totalQuantity: Int, originalPrice: Double): Double {
        return when (type) {
            ProductType.NORMAL -> originalPrice
            ProductType.HIGH_DEMAND -> {
                when {
                    totalQuantity > 100 -> originalPrice
                    totalQuantity in 31..100 -> originalPrice * 1.2
                    else -> originalPrice * 1.5
                }
            }
        }
    }

}
