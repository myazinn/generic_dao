package com.nikitamiazin.repository.mongo.ops

import com.nikitamiazin.repository.core.domain.{ExtractId, ExtractVersion}
import com.nikitamiazin.repository.core.ops.GenericRepositoryOps
import com.nikitamiazin.repository.legacy.domain.{Snapshotable => LegacySnapshotable}
import com.nikitamiazin.repository.mongo.MongoCollection

import scala.reflect.ClassTag

// doesn't really needed (you can use GenericRepositoryOps directly) but it improves implicit resolution
// please don't add new methods here unless it's really mongo specific things
// add them to `GenericRepository`
trait MongoGenericRepositoryOps[T] extends GenericRepositoryOps[T]

object MongoGenericRepositoryOps extends LowPriorityMongoGenericRepositoryOpsInstances {
  def legacySnapshotableToSnapshotable[T <: LegacySnapshotable]: ExtractVersion[T] = new ExtractVersion[T] {

    override type Version = String

    override def version(t: T): Version = t.version

  }

  implicit def snapshotableMongoGenericRepositoryOps[T: ExtractVersion : ExtractId : Companion : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] =
    new SnapshotableMongoGenericRepositoryOps(collection)

  implicit def fromLegacySnapshotable[T <: LegacySnapshotable : ExtractId : Companion : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] = {
    implicit val snap: ExtractVersion[T] = legacySnapshotableToSnapshotable[T]
    snapshotableMongoGenericRepositoryOps
  }
}

trait LowPriorityMongoGenericRepositoryOpsInstances {
  implicit def regularMongoGenericRepositoryOps[T: ExtractId : Companion : ClassTag](implicit collection: MongoCollection[T]): MongoGenericRepositoryOps[T] =
    new RegularMongoGenericRepositoryOps(collection)
}
