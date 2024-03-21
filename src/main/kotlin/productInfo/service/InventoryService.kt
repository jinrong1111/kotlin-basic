package productInfo.service

import productInfo.model.Inventory
import retrofit2.http.GET

interface InventoryService {
    @GET("inventories")
    suspend fun getInventories(): List<Inventory>
}
