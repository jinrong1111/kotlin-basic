package productInfo.manager

import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import productInfo.model.Inventory
import productInfo.repository.InventoryRepository
import productInfo.service.InventoryService

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
    }