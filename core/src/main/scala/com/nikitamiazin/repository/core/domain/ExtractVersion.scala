package com.nikitamiazin.repository.core.domain

trait ExtractVersion[T] {

  type Version

  val versionField: String = "version"

  def version(t: T): Version

}

object ExtractVersion {

  def apply[T](implicit ev: ExtractVersion[T]): ExtractVersion[T] = ev

}