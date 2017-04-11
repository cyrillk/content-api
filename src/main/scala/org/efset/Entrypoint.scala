package org.efset

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Entrypoint extends App {

  implicit val system = ActorSystem("content-api")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    get {
      pathPrefix("data") {
        pathPrefix(Segment) { distributionId =>
          pathEnd {
            println(s"distributionId = $distributionId")
            complete(StatusCodes.OK)
          }
        }
      } ~
        pathPrefix("score") {
          pathPrefix(Segment) { distributionId =>
            pathEnd {
              println(s"distributionId = $distributionId")
              complete(StatusCodes.OK)
            }
          }
        }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", ContextConfig.appPort)

  println(s"Server online at http://localhost:${ContextConfig.appPort}/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}