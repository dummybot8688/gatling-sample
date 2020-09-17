package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicLoadSimulation extends Simulation{
  //val httpConf = http.baseUrl("http://da075b19-xpergiadev-caserv-8133-1735115031.us-east-2.elb.amazonaws.com/")
  //  .header("Accept", "application/json")

  val httpConf = http.baseUrl("http://localhost:8080/")
    .header("Accept", "application/json")
  def userMemory() = {
    exec(
      http("Using Memory")
        .get("use-memory")
        .check(status.is(200))
    )
  }

  def clearMemory() = {
    exec(
      http("Clearing memory")
        .get("clear-memory")
        .check(status.is(200))
    )
  }

  val scn = scenario("Basic Load Simulation")
    .exec(userMemory())
    .pause(5 seconds)

  // Basic Load Simulation
  setUp(
    scn.inject(
      //nothingFor(20 seconds),
      //atOnceUsers(150),
      //rampUsers(800) during (10 minutes)
    ).protocols(httpConf),
    //scn1.inject(
    //  nothingFor(11 minutes),
    //  rampUsers(100) during (5 minutes)
   // ).protocols(httpConf)
  )//.maxDuration(5 minutes)


}
