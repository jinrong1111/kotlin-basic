package productInfo.repository

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import productInfo.model.Inventory
import productInfo.service.InventoryService

class InventoryRepositoryTest {

    @Test
    fun `test getInventories`() {
        val inventoryService = mockk<InventoryService>()
        val inventoryRepository = InventoryRepository(inventoryService)

        val expectedInventories = listOf(
            Inventory("sku1", "region1", 50),
            Inventory("sku1", "region2", 80),
            Inventory("sku2", "region3", 30)
        )

        coEvery { inventoryService.getInventories() } returns expectedInventories

        val inventories = runBlocking { inventoryRepository.getInventories() }
        assertEquals(expectedInventories, inventories)
    }
}