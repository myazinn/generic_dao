package com.nikitamiazin.repository.mongo.ops

import com.nikitamiazin.repository.core.domain.{Regular, Snapshotable}
import com.nikitamiazin.repository.mongo.MongoCollection

import scala.concurrent.Future
import scala.reflect.ClassTag

class SnapshotableMongoGenericRepositoryOps[T: Snapshotable : ClassTag](collection: MongoCollection[T]) extends MongoGenericRepositoryOps[T] {

  println(s"snapshotable repo for ${implicitly[ClassTag[T]].runtimeClass.getSimpleName} initiated")

  override def save(t: T): Future[Unit] = {
    println(s"snapshotable repo save($t)")
    Future.successful(collection += Regular[T].id(t) -> t)
  }

  override def get(id: String): Future[Option[T]] = {
    println(s"snapshotable repo get($id)")
    Future.successful(collection.get(id))
  }

}