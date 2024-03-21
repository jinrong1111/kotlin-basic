package productInfo.repository

import productInfo.model.Product
import productInfo.service.ProductService

class ProductRepository(private val productService: ProductService) {
    suspend fun getProducts(): List<Product> {
        return productService.getProducts()
    }
}