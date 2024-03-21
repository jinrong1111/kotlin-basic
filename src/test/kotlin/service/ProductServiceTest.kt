package service

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import model.Product
import model.ProductType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductServiceTest {
    @Test
    fun `test getProducts API`() {
        val productService = mockk<ProductService>()
        val expectedProducts = listOf(
            Product("1","a","Electronic Watch", 299.99, ProductType.NORMAL, "image1.jpg"),
            Product("2", "b","Sports Shoes", 499.6, ProductType.NORMAL, "image2.jpg")
        )
        coEvery { productService.getProducts() } returns expectedProducts

        val actualProducts = runBlocking { productService.getProducts() }

        assertEquals(expectedProducts, actualProducts)
    }

}