package com.nikitamiazin.repository.example.domain

import com.nikitamiazin.repository.core.domain.Snapshotable
import com.nikitamiazin.repository.legacy
import com.nikitamiazin.repository.mongo.ops.Companion


case class RegularUser(id: String, age: Int)

object RegularUser extends Companion[RegularUser] {
  override def collection: String = "regular_user_collection"

  override def accessKeys: Seq[String] = Seq("regular_user_key_1", "regular_user_key_2")
}

case class LegacySnapshotableUser(id: String, age: Int) extends legacy.domain.Snapshotable

case class SnapshotableUser(id: String, age: Int)

object SnapshotableUser {
  implicit val snapshotable: Snapshotable[SnapshotableUser] = _.id
}