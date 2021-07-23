package com.nikitamiazin.repository.mongo

import com.nikitamiazin.repository.core.GenericRepositoryImpl
import com.nikitamiazin.repository.mongo.ops.MongoGenericRepositoryOps

// doesn't really needed (you can use GenericRepository directly) but it improves implicit resolution
abstract class MongoGenericRepositoryImpl[T: MongoGenericRepositoryOps] extends GenericRepositoryImpl[T]