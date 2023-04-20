package com.bootcamp.billspayment.quill

import com.datastax.oss.driver.api.core.{CqlIdentifier, CqlSession}
import org.springframework.stereotype.Component

import java.net.InetSocketAddress

@Component
class QuillLocalImplContext extends QuillContext {
  override lazy val cassandraCluster: CqlSession = {
    val builder = CqlSession.builder()
    for (cp <- cassandraHosts) builder.addContactPoint(new InetSocketAddress(cp, cassandraPort))
    builder.withLocalDatacenter(cassandraDatacenter)
    builder.withKeyspace(CqlIdentifier.fromCql("supermarket"))

    builder.build()
  }
}
