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

  implicit def materializeExtractId[T]: ExtractId[T] = macro Macro.materializeExtractIdImpl[T]

}