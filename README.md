###Understanding Gatling

This repo is companion repo to the tutorial provided by [James Willet's Gatling Guide](https://www.james-willett.com/gatling-load-testing-complete-guide/#1-installation-of-gatling-from-website-download).

The steps illustrated in the tutorial are including in this repo, to provision users to pull the repo and execute the tests without setting up the project.

>Prerequisites:
* Java Open JDK 8
* Scala 2.12.* (Gatling doesn't work with < 2.12 or > 2.12)
* Maven
* IntelliJ

>Please Note:
To perform quick test, you need to clone [Video Game DB](https://github.com/james-willett/VideoGameDB) and run locally to run the below simulations:
*  BasicLoadSimulation
*  RampUsersLoadSimulation
*  FixedDurationLoadSimulation
>Replace the endpoints with the application, you would like to perform load test on.