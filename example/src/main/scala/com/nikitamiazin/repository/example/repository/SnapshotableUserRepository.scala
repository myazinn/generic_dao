package com.nikitamiazin.repository.example.repository

import com.nikitamiazin.repository.core.GenericRepository
import com.nikitamiazin.repository.example.domain.SnapshotableUser
import com.nikitamiazin.repository.mongo.{MongoCollection, MongoGenericRepositoryImpl}

trait SnapshotableUserRepository extends GenericRepository[SnapshotableUser]

object SnapshotableUserRepository {

  def apply(collection: MongoCollection[SnapshotableUser]): SnapshotableUserRepository =
    new Impl()(collection)

  class Impl(implicit collection: MongoCollection[SnapshotableUser])
    extends MongoGenericRepositoryImpl[SnapshotableUser] with SnapshotableUserRepository

}