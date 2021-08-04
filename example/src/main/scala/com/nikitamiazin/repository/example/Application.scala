package com.nikitamiazin.repository.example

import com.nikitamiazin.repository.example.domain._
import com.nikitamiazin.repository.example.repository._
import com.nikitamiazin.repository.mongo.MongoCollection

import scala.collection.mutable

object Application extends App {

  val regularUserCollection: MongoCollection[RegularUser] = mutable.Map.empty
  val snapshotableUserCollection: MongoCollection[SnapshotableUser] = mutable.Map.empty
  val legacySnapshotableUserCollection: MongoCollection[LegacySnapshotableUser] = mutable.Map.empty

  val regularUserRepo = RegularUserRepository(regularUserCollection)
  val snapshotableUserRepo = SnapshotableUserRepository(snapshotableUserCollection)
  val legacySnapshotableUserRepo = LegacySnapshotableUserRepository(legacySnapshotableUserCollection)

  println()
  regularUserRepo.save(RegularUser("regularId", 1))
  snapshotableUserRepo.save(SnapshotableUser("snapshotableId", 2))
  legacySnapshotableUserRepo.save(LegacySnapshotableUser("legacySnapshotableId", 3))

  println()
  println("let's play a bit")
  val id = "unique_id"
  println(regularUserRepo.get(id))
  println(regularUserRepo.save(RegularUser(id, 1)))
  println(regularUserRepo.get(id))
  println(regularUserRepo.delete(id))
  println(regularUserRepo.get(id))
}
