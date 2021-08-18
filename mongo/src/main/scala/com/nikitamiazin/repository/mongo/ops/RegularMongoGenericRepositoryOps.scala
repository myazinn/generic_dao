package com.nikitamiazin.repository.mongo.ops

import com.nikitamiazin.repository.core.domain.ExtractId
import com.nikitamiazin.repository.mongo.MongoCollection

import scala.concurrent.Future
import scala.reflect.ClassTag

class RegularMongoGenericRepositoryOps[T: ExtractId : Companion : ClassTag](collection: MongoCollection[T])
  extends MongoGenericRepositoryOps[T] {

  println(s"regular repo for ${implicitly[ClassTag[T]].runtimeClass.getSimpleName} initiated")

  protected val companion: Companion[T] = implicitly[Companion[T]]

  override def save(t: T): Future[Unit] = {
    println(s"regular repo save($t) to ${companion.collection}")
    Future.successful(collection += ExtractId[T].id(t).toString -> t)
  }

  override def get(id: String): Future[Option[T]] = {
    println(s"regular repo get($id) from ${companion.collection} using access keys ${companion.accessKeys}")
    Future.successful(collection.get(id))
  }

}
