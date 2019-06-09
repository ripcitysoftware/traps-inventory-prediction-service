package com.flowfactor.inventorypredictionservice.controller

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactFolder
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = ["server.port=9090"])
@Provider("inventoryPredictionProvider")
@PactFolder("src/test/resources/pacts")
class InventoryPredictionControllerTest {


  @BeforeEach
  fun setupTestTarget(context: PactVerificationContext) {
    context.target = HttpTestTarget("localhost", 9090, "/")
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider::class)
  fun pactVerificationTestTemplate(context: PactVerificationContext) {
    context.verifyInteraction();
  }

  @State("Inventory for Sku 2 in store 1 exists")
  fun inventoryForSkuExists() {
    // nothing to do, real service is used
  }

}