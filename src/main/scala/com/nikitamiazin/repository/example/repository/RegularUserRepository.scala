package com.nikitamiazin.repository.example.repository

import com.nikitamiazin.repository.core.{GenericRepository, MongoCollection}
import com.nikitamiazin.repository.example.domain.RegularUser
import com.nikitamiazin.repository.mongo.MongoGenericRepositoryImpl

import scala.concurrent.Future

trait RegularUserRepository extends GenericRepository[RegularUser] {

  def delete(id: String): Future[Unit]

}

object RegularUserRepository {

  def apply(collection: MongoCollection[RegularUser]): RegularUserRepository =
    new Impl()(collection)

  class Impl(implicit collection: MongoCollection[RegularUser])
    extends MongoGenericRepositoryImpl[RegularUser] with RegularUserRepository {

    override def delete(id: String): Future[Unit] = {
      println(s"delete RegularUser by id [$id]")
      Future.successful(collection -= id)
    }

  }

}
