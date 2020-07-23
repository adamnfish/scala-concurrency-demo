package io.adamnfish.server

import java.util.concurrent.{CompletableFuture, Executors}
import java.util.{Timer, TimerTask}

import io.javalin.Javalin

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.jdk.FutureConverters._
import scala.util.Try

object Server {
  implicit val ec = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())


  def main(args: Array[String]): Unit = {
    val app = Javalin.create()
    app.start(7000)

    app.get("/1", { ctx =>
      ctx.result(respond("1"))
    })
    app.get("/2", { ctx =>
      ctx.result(respond("2"))
    })
    app.get("/3", { ctx =>
      ctx.result(respond("3"))
    })
    app.get("/4", { ctx =>
      ctx.result(respond("4"))
    })
    app.get("/5", { ctx =>
      ctx.result(respond("5"))
    })

    Runtime.getRuntime.addShutdownHook(new Thread(() => {
      println("[INFO] Stopping...")
      app.stop()
    }))
  }

  def respond(endpoint: String): CompletableFuture[String] = {
    println(s"processing $endpoint")
    delay(2.seconds.toMillis)("done").asJava.toCompletableFuture
  }

  def delay[T](delay: Long)(block: => T): Future[T] = {
    val promise = Promise[T]()
    val t = new Timer()
    t.schedule(new TimerTask {
      override def run(): Unit = {
        promise.complete(Try(block))
      }
    }, delay)
    promise.future
  }
}
