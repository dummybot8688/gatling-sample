package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class RampUsersLoadSimulation extends Simulation{
  val httpConf = http.baseUrl("http://da075b19-xpergiadev-caserv-8133-293958300.us-east-2.elb.amazonaws.com/")
    .header("Accept", "application/json")

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def getSpecificGame() = {
    exec(
      http("Get Specific Game")
        .get("videogames/2")
        .check(status.is(200))
    )
  }

  def userMemory() = {
    exec(
      http("Using Memory")
        .get("use-memory")
        .check(status.is(200))
    )
  }

  val scn = scenario("Ramp Users Load Simulation")
    .exec(userMemory())
    .pause(5)

  // Ramp Users Load Simulation
  setUp(
    scn.inject(
      nothingFor(5 seconds),
      constantUsersPerSec(10) during (10 seconds),
      rampUsersPerSec(1) to (5) during (20 seconds)
    ).protocols(httpConf)
  )
}
