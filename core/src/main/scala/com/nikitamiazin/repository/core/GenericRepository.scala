package com.nikitamiazin.repository.core

import scala.concurrent.Future

trait GenericRepository[T] {

  def save(t: T): Future[Unit]

  def get(id: String): Future[Option[T]]

}
