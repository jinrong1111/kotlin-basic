package productInfo.manager

import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import productInfo.model.Inventory
import productInfo.model.ProductType
import productInfo.repository.InventoryRepository

class ProductManagerTest {
    @Test
    fun `test calculateTotalQuantity`() {
        val inventoryRepository = mockk<InventoryRepository>()

        val inventories = listOf(
            Inventory("sku1", "region1", 50),
            Inventory("sku1", "region2", 80),
            Inventory("sku2", "region3", 30)
        )

        val productManager = ProductManager(mockk(), inventoryRepository)

        val totalQuantitySku1 = productManager.calculateTotalQuantity("sku1", inventories)
        assertEquals(130, totalQuantitySku1)

        val totalQuantitySku2 = productManager.calculateTotalQuantity("sku2", inventories)
        assertEquals(30, totalQuantitySku2)
}
    @Test
    fun `test calculatePrice`() {
        val inventoryRepository = mockk<InventoryRepository>()

        val originalPrice = 100.0

        val productManager = ProductManager(mockk(), inventoryRepository)

        assertEquals(originalPrice, productManager.calculatePrice(ProductType.NORMAL, 50, originalPrice))
        assertEquals(originalPrice, productManager.calculatePrice(ProductType.HIGH_DEMAND, 150, originalPrice))
        assertEquals(originalPrice * 1.2, productManager.calculatePrice(ProductType.HIGH_DEMAND, 80, originalPrice))
        assertEquals(originalPrice * 1.5, productManager.calculatePrice(ProductType.HIGH_DEMAND, 20, originalPrice))
    }
}