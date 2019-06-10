package com.flowfactor.inventorypredictionservice.service

import com.flowfactor.inventorypredictionservice.client.WeatherClient
import com.flowfactor.inventorypredictionservice.dto.ItemInventoryDTO
import com.flowfactor.inventorypredictionservice.dto.StoreLocation
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.HashMap
import java.util.concurrent.ThreadLocalRandom
import javax.annotation.PostConstruct

@Service
class InventoryPredictionService(private val weatherClient: WeatherClient) {

    private var storeLocations: MutableMap<Long, StoreLocation> = HashMap<Long, StoreLocation>()
    private var itemsByStore: MutableMap<StoreLocation, Map<Long, ItemInventoryDTO>> = HashMap<StoreLocation, Map<Long,ItemInventoryDTO>>()
    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    fun getAnticipatedInventoryForSkuAndBranch(storeId: String,sku: String) : ItemInventoryDTO {
        val storeLocation = storeLocations.get(storeId.toLong())
        val map = itemsByStore.get(storeLocation)
        val itemInventoryDTO = map!!.get(sku.toLong())!!
        return itemInventoryDTO
    }

    @PostConstruct
    private fun initializeInventory() {
        itemsByStore = HashMap<StoreLocation, Map<Long, ItemInventoryDTO>>()

        //create stores with inventory
        for (i in 1..5) {
            val weather = weatherClient.getWeatherByLocation("London")
            logger.info { weather }
            val store = StoreLocation(java.lang.Long.valueOf(i.toLong()), "London",weather.consolidatedWeather[i-1].weatherStateName)
            storeLocations.put(store.storeId, store)

            //create some items for the store
            val umbrellas = ItemInventoryDTO(1L, "Umbrella", ThreadLocalRandom.current().nextInt(0, 100))
            val shovels = ItemInventoryDTO(2L, "Shovel", ThreadLocalRandom.current().nextInt(0, 100))
            val itemInventoryMap = HashMap<Long, ItemInventoryDTO>()
            itemInventoryMap[umbrellas.sku!!] = umbrellas
            itemInventoryMap[shovels.sku!!] = shovels

            itemsByStore.put(store, itemInventoryMap)
        }
        logger.info("Finished initializing stores with inventory.")
    }
}