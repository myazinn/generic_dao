package com.nikitamiazin.repository.mongo.ops

trait Companion[T] {

  // useful if we mix `Companion` trait to model class (e.g. RegularUser), but I strongly recommend not to do it
  implicit val selfRef: Companion.this.type = this

  def collection: String
  def accessKeys: Seq[String]

}