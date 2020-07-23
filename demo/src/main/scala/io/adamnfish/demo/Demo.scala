package io.adamnfish.demo

import sttp.client._
import sttp.client.asynchttpclient.future.AsyncHttpClientFutureBackend

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


object Demo {
  implicit val sttpBackend = AsyncHttpClientFutureBackend()


  def main(args: Array[String]): Unit = {
    println("starting")
    val future = for {
      results <- Future.traverse(List(1, 2, 3, 4, 5))(request)
    } yield s"done: $results"
    val result = Await.result(future, 11.seconds)
    println(result)
  }

  def request(n: Int): Future[Response[Either[String, String]]] = {
    basicRequest
      .get(uri"http://localhost:7000/$n")
      .send()
  }
}
