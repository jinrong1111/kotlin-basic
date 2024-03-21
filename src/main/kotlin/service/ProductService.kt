package service

import api.ProductApi
import model.Product

class ProductService(private val productApi: ProductApi) {
    suspend fun getProducts(): List<Product> {
        return productApi.getProducts()
    }
}