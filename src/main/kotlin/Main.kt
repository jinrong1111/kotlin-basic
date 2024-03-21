import kotlinx.coroutines.runBlocking
import productInfo.manager.ProductManager
import productInfo.repository.InventoryRepository
import productInfo.repository.ProductRepository
import productInfo.service.InventoryService
import productInfo.service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() {
    try {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productService = retrofit.create(ProductService::class.java)
        val inventoryService = retrofit.create(InventoryService::class.java)

        val productRepository = ProductRepository(productService)
        val inventoryRepository = InventoryRepository(inventoryService)

        val productManager = ProductManager(productRepository, inventoryRepository)

        runBlocking {
            val displayProducts = productManager.displayAllProducts()
            displayProducts.forEach { displayProduct ->
                println("SKU: ${displayProduct.sku}, Name: ${displayProduct.name}, Price: ${displayProduct.price}, Inventory: ${displayProduct.inventory}, Image: ${displayProduct.image}")
            }
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }
}
