package com.nikitamiazin.repository.core.domain

import scala.language.experimental.macros
import scala.language.reflectiveCalls

trait ExtractId[T] {

  type Id

  val idField: String = "id"

  def id(t: T): Id

}

object ExtractId {

  def apply[T](implicit ev: ExtractId[T]): ExtractId[T] = ev

  implicit def default[T <: {def id: String}]: ExtractId[T] = new ExtractId[T] {

    override type Id = String

    override def id(t: T): Id = t.id

  }

}