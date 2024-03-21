package model

data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val price: Double,
    val type: ProductType,
    val image: String
)