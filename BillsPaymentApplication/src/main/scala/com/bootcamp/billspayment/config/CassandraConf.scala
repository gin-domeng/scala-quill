package com.bootcamp.billspayment.config

import com.typesafe.config.ConfigFactory

trait CassandraConf {
  val cassandraConf = ConfigFactory.load("cassandra.conf")

  // cassandra config
  lazy val cassandraKeyspace = cassandraConf.getString("cassandra.keyspace")
  lazy val cassandraHosts = cassandraConf.getString("cassandra.hosts").split(",").toSeq.map(_.trim)
  lazy val cassandraPort = cassandraConf.getInt("cassandra.port")
  lazy val cassandraDatacenter = cassandraConf.getString("cassandra.data-center")
  lazy val preparedStatementCacheSize = cassandraConf.getInt("cassandra.preparedStatementCacheSize")
}