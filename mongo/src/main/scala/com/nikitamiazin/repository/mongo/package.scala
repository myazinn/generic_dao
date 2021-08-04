package com.nikitamiazin.repository

import scala.collection.mutable

package object mongo {

  type MongoCollection[T] = mutable.Map[String, T]

}
