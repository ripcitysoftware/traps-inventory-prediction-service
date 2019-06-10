# inventory-prediction-service
This is a service, written in Kotlin that calls out to a third party service
to determine  weather information
Using:
https://www.metaweather.com/api/


Some of the data for inventory prediction has been canned for the purpose
of the Flow Factor Conference workshop

#Prerequisites
 IDE: IntelliJ  or any other IDE with Kotlin support
 Plugin : Kotlin plugin

# To build and run locally
`mvn clean install`
Right click on `InventoryPredictionServiceApplication` and hit Run

#Call endpoint
`curl -Lik http://localhost:8080/prediction/1/2` corresponding to `/{storeId}/{sku}`