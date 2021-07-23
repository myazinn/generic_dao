package com.nikitamiazin.repository.example.domain

import com.nikitamiazin.repository.core.domain.Snapshotable
import com.nikitamiazin.repository.legacy


case class RegularUser(id: String, age: Int)

case class LegacySnapshotableUser(id: String, age: Int) extends legacy.domain.Snapshotable

case class SnapshotableUser(id: String, age: Int)

object SnapshotableUser {
  implicit val snapshotable: Snapshotable[SnapshotableUser] = _.id
}