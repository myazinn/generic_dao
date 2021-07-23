package com.nikitamiazin.repository.example.repository

import com.nikitamiazin.repository.core.{GenericRepository, MongoCollection}
import com.nikitamiazin.repository.example.domain.LegacySnapshotableUser
import com.nikitamiazin.repository.mongo.MongoGenericRepositoryImpl

trait LegacySnapshotableUserRepository extends GenericRepository[LegacySnapshotableUser]

object LegacySnapshotableUserRepository {

  def apply(collection: MongoCollection[LegacySnapshotableUser]): LegacySnapshotableUserRepository =
    new Impl()(collection)

  class Impl(implicit collection: MongoCollection[LegacySnapshotableUser])
    extends MongoGenericRepositoryImpl[LegacySnapshotableUser] with LegacySnapshotableUserRepository

}
