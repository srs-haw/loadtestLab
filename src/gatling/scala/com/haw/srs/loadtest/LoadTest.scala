package com.haw.srs.loadtest

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps

class LoadTest extends Simulation {

  val baseURL      = "http://localhost:8080"

  val rampUpTimeSecs = 20
  val testTimeSecs   = 60
  val noOfUsers      = 1000
  val URI          = "/process?minMs=500&maxMs=1000"

//  val rampUpTimeSecs = 60
//  val testTimeSecs   = 360
//  val noOfUsers      = 8000
//  val URI            = "/process?minMs=5000&maxMs=10000"

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseURL)

  object HelloWorldResource {
    val get: ChainBuilder = exec(http("HelloWorld")
      .get(URI))
      //.basicAuth("user", "24gh39ugh0"))
  }

  val myScenario: ScenarioBuilder = scenario("RampUpUsers")
    .exec(HelloWorldResource.get)

  setUp(myScenario.inject(
         rampUsers(noOfUsers)
        .during(rampUpTimeSecs.seconds)
    ).protocols(httpProtocol))
     .maxDuration(testTimeSecs.seconds)
//     .assertions(global.successfulRequests.percent.is(100))
}