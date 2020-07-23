package io.adamnfish.demo

import scalaj.http.Http
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Demo2 {
  def main(args: Array[String]): Unit = {
    println("starting")
    val future = for {
      _ <- request(1)
      results <- Future.traverse(List(1, 2, 3, 4, 5))(request)
    } yield s"done: $results"
    val result = Await.result(future, 11.seconds)
    println(result)
  }

  def request(n: Int) =
    Future(Http(s"http://localhost:7000/$n").asString)
}
