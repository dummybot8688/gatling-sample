package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FixedDurationLoadSimulation extends Simulation{
  val httpConf = http.baseUrl("http://da075b19-xpergiadev-caserv-8133-1735115031.us-east-2.elb.amazonaws.com/")
    .header("Accept", "application/json")

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def userMemory() = {
    tryMax(3){exec(
      http("Using Memory")
        .get("use-memory")
        .check(status.is(200))
    )
    }
  }

  def getSpecificGame() = {
    exec(
      http("Get Specific Game")
        .get("videogames/2")
        .check(status.is(200))
    )
  }

  val scn = scenario("Fixed Duration Load Simulation")
    .forever(){
      exec(userMemory())
        .pause(5)
    }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(10),
      rampUsers(10) during (30 second)
    ).protocols(httpConf)
  ).maxDuration(1 minute)
}
