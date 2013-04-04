package com.janrain.a2

import akka.actor.ActorSystem
import com.scalapenos.riak.RiakClient
import spray.json.DefaultJsonProtocol
import scala.concurrent.Await
import scala.concurrent.duration._

object Nuker extends App with DefaultJsonProtocol {

	import com.scalapenos.riak.RiakMapReduce._
	import scala.concurrent.Future._
	import scala.concurrent.ExecutionContext.Implicits._

	implicit val actorSystem = ActorSystem()
	val riakClient = RiakClient(actorSystem)

	for (bucketName ← args.toList) {
		val bucket = riakClient.bucket(bucketName)

		val deletes = for {
			keys ← riakClient.mapReduce(InputBucket(bucketName)).result[List[List[String]]]
			deletes ← sequence(for (key ← keys) yield {
				key match {
					case List(_, k) ⇒ bucket.delete(k)
				}
			})
		} yield deletes
		println(s"deleting: $bucketName...")
		Await.result(deletes, 5.minutes)
	}

	actorSystem.shutdown()
}
