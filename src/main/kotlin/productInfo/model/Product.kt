package productInfo.model

data class Product(
    val sku: String,
    val name: String,
    val price: Double,
    val type: ProductType,
    val imageUrl: String
)