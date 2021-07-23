package com.nikitamiazin.repository.core.domain

import scala.language.reflectiveCalls

trait Regular[T] {
  def id(t: T): String
}

object Regular {

  def apply[T](implicit ev: Regular[T]): Regular[T] = ev

  implicit def default[T <: {def id: String}]: Regular[T] = _.id // dirty hack; we should use macro instead of reflection

}