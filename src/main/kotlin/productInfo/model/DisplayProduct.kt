package productInfo.model

data class DisplayProduct(
    val sku: String,
    val name: String,
    val price: Double,
    val inventory: Int,
    val image: String
)