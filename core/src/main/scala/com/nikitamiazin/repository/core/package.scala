package com.nikitamiazin.repository

import scala.collection.mutable

package object core {

  type MongoCollection[T] = mutable.Map[String, T]

}
