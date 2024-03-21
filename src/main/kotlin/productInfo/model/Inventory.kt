package productInfo.model

import com.google.gson.annotations.SerializedName

data class Inventory(
    @SerializedName("SKU") val sku: String,
    val region: String,
    val quantity: Int
)