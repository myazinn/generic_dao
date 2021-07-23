package com.nikitamiazin.repository.mongo.ops

import com.nikitamiazin.repository.core.MongoCollection
import com.nikitamiazin.repository.core.domain.{Regular, Snapshotable}
import com.nikitamiazin.repository.core.ops.GenericRepositoryOps
import com.nikitamiazin.repository.legacy.domain.{Snapshotable => LegacySnapshotable}

import scala.reflect.ClassTag

// doesn't really needed (you can use GenericRepositoryOps directly) but it improves implicit resolution
// please don't add new methods here unless it's really mongo specific things
// add them to `GenericRepository`
trait MongoGenericRepositoryOps[T] extends GenericRepositoryOps[T]

object MongoGenericRepositoryOps extends LowPriorityMongoGenericRepositoryOpsInstances {
  def legacySnapshotableToSnapshotable[T <: LegacySnapshotable]: Snapshotable[T] = _.id

  implicit def fromSnapshotable[T: Snapshotable : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] =
    new SnapshotableMongoGenericRepositoryOps(collection)

  implicit def fromLegacySnapshotable[T <: LegacySnapshotable : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] = {
    implicit val snap: Snapshotable[T] = legacySnapshotableToSnapshotable[T]
    new SnapshotableMongoGenericRepositoryOps(collection)
  }
}

trait LowPriorityMongoGenericRepositoryOpsInstances {
  implicit def fromRegular[T: Regular : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] =
    new RegularMongoGenericRepositoryOps(collection)
}
