package com.bootcamp.billspayment.config

import com.datastax.oss.driver.api.core.{CqlIdentifier, CqlSession}

import java.net.InetSocketAddress

trait CassandraCluster extends CassandraConf {

  lazy val cassandraCluster: CqlSession = {
    val builder = CqlSession.builder()
    for (cp <- cassandraHosts) builder.addContactPoint(new InetSocketAddress(cp, cassandraPort))
    builder.withLocalDatacenter(cassandraDatacenter)
    builder.withKeyspace(CqlIdentifier.fromCql(cassandraKeyspace))

    builder.build()
  }

  lazy implicit val session: CqlSession = cassandraCluster
}
