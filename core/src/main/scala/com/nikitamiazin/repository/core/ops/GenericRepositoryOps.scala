package com.nikitamiazin.repository.core.ops

import com.nikitamiazin.repository.core.GenericRepository

// please don't add new methods here unless it's really implementation specific things
// add them to `GenericRepository`
trait GenericRepositoryOps[T] extends GenericRepository[T]

object GenericRepositoryOps {

  def apply[T](implicit ev: GenericRepositoryOps[T]): GenericRepositoryOps[T] = ev

}