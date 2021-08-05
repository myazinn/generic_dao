package com.nikitamiazin.repository.example.repository

import com.nikitamiazin.repository.core.GenericRepository
import com.nikitamiazin.repository.example.domain.LegacySnapshotableUser
import com.nikitamiazin.repository.mongo.ops.Companion
import com.nikitamiazin.repository.mongo.{MongoCollection, MongoGenericRepositoryImpl}

trait LegacySnapshotableUserRepository extends GenericRepository[LegacySnapshotableUser]

object LegacySnapshotableUserRepository {

  def apply(collection: MongoCollection[LegacySnapshotableUser]): LegacySnapshotableUserRepository =
    new Impl()(collection, Impl)

  class Impl(implicit collection: MongoCollection[LegacySnapshotableUser], companion: Companion[LegacySnapshotableUser])
    extends MongoGenericRepositoryImpl[LegacySnapshotableUser] with LegacySnapshotableUserRepository

  object Impl extends Companion[LegacySnapshotableUser] {

    override def collection: String = "legacy_user_collection"

    override def accessKeys: Seq[String] = Seq("legacy_user_key_1", "legacy_user_key_2")

  }

}
