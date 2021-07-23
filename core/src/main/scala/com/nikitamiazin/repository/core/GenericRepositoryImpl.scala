package com.nikitamiazin.repository.core

import com.nikitamiazin.repository.core.ops.GenericRepositoryOps

import scala.concurrent.Future

// summons correct repository implementation based on supplied type and delegate all method calls to it
abstract class GenericRepositoryImpl[T: GenericRepositoryOps] extends GenericRepository[T] {
  val ops: GenericRepositoryOps[T] = GenericRepositoryOps[T]

  @inline override def get(id: String): Future[Option[T]] = ops.get(id)

  @inline override def save(t: T): Future[Unit] = ops.save(t)
}