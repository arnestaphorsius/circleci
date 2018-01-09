package com.xebia

import scala.util._

import akka.actor._
import akka.stream._
import akka.http.scaladsl.Http

object Main extends App with ExampleRoutes {

  val port = Properties.envOrElse("PORT", "8080").toInt

  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  Http(system).bindAndHandle(routes, "0.0.0.0", port).foreach(
    binding => system.log.info("Bound to " + binding.localAddress)
  )
}
