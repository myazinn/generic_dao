package com.nikitamiazin.repository.example.repository

import com.nikitamiazin.repository.core.GenericRepository
import com.nikitamiazin.repository.example.domain.SnapshotableUser
import com.nikitamiazin.repository.mongo.ops.Companion
import com.nikitamiazin.repository.mongo.{MongoCollection, MongoGenericRepositoryImpl}

trait SnapshotableUserRepository extends GenericRepository[SnapshotableUser]

object SnapshotableUserRepository {

  def apply(collection: MongoCollection[SnapshotableUser]): SnapshotableUserRepository =
    new Impl()(collection)

  // unfortunately we have to place companion object before class definition otherwise implicit resolution doesn't work
  // looks like it's a bug in Scala compiler
  // https://github.com/scala/bug/issues/12440
  implicit object Impl extends Companion[SnapshotableUser] {
    override def collection: String = "snapshotable_user_collection"

    override def accessKeys: Seq[String] = Seq("snapshotable_user_key_1", "snapshotable_user_key_2")
  }

  class Impl(implicit collection: MongoCollection[SnapshotableUser])
    extends MongoGenericRepositoryImpl[SnapshotableUser] with SnapshotableUserRepository

}