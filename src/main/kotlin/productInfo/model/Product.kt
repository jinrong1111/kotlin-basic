package productInfo.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("SKU") val sku: String,
    val name: String,
    val price: Double,
    val type: ProductType,
    val image: String
)