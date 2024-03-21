package productInfo.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import productInfo.model.Product
import productInfo.model.ProductType
import productInfo.service.ProductService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductRepositoryTest {

    @Test
    fun `test getProducts`() {
        val productService = mockk<ProductService>()
        val productRepository = ProductRepository(productService)

        val expectedProducts = listOf(
            Product("sku1", "Product 1", 10.0, ProductType.NORMAL, "image1.jpg"),
            Product("sku2", "Product 2", 20.0, ProductType.HIGH_DEMAND, "image2.jpg")
        )

        coEvery { productService.getProducts() } returns expectedProducts

        val products = runBlocking { productRepository.getProducts() }
        assertEquals(expectedProducts, products)
    }
}
