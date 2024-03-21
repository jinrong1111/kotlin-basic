package productInfo.manager

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import productInfo.model.Inventory
import productInfo.model.Product
import productInfo.model.ProductType
import productInfo.repository.InventoryRepository
import productInfo.repository.ProductRepository

class ProductManagerTest{
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
    @Test
    fun `test displayAllProducts`() {
        val productRepository = mockk<ProductRepository>()
        val products = listOf(
            Product("sku1", "Product 1", 10.0, ProductType.NORMAL, "image1.jpg"),
            Product("sku2", "Product 2", 20.0, ProductType.HIGH_DEMAND, "image2.jpg")
        )
        coEvery { productRepository.getProducts() } returns products
        val inventoryRepository = mockk<InventoryRepository>()
        val inventories = listOf(
            Inventory("sku1", "region1", 50),
            Inventory("sku1", "region2", 80),
            Inventory("sku2", "region3", 30)
        )
        coEvery { inventoryRepository.getInventories() } returns inventories

        val productManager = ProductManager(productRepository, inventoryRepository)
        val displayProducts = runBlocking { productManager.displayAllProducts() }

        assertEquals(2, displayProducts.size)
        assertEquals("sku1", displayProducts[0].sku)
        assertEquals("Product 1", displayProducts[0].name)
        assertEquals(10.0, displayProducts[0].price)
        assertEquals(130, displayProducts[0].inventory)
        assertEquals("image1.jpg", displayProducts[0].imageUrl)
        assertEquals("sku2", displayProducts[1].sku)
        assertEquals("Product 2", displayProducts[1].name)
        assertEquals(30.0, displayProducts[1].price)
        assertEquals(30, displayProducts[1].inventory)
        assertEquals("image2.jpg", displayProducts[1].imageUrl)
    }


}