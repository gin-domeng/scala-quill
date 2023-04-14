package com.bootcamp.billspayment.config

import io.getquill.{CassandraAsyncContext, SnakeCase}

trait CassandraContext extends  CassandraCluster {
  val ctx = new CassandraAsyncContext(SnakeCase, cassandraCluster, preparedStatementCacheSize) {
    var cluster = cassandraCluster
    var keySpace = cassandraKeyspace
  }

}
