package com.bootcamp.billspayment.cassandra

import com.bootcamp.billspayment.configurable.ConfigQuillContext
import com.datastax.oss.driver.api.core.CqlSession
import io.getquill.{CassandraAsyncContext, SnakeCase}
import org.cassandraunit.utils.EmbeddedCassandraServerHelper

class CassandraTestContext extends ConfigQuillContext{

   val testSession: CqlSession = {
    EmbeddedCassandraServerHelper.startEmbeddedCassandra(EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE)
    EmbeddedCassandraServerHelper.getSession
  }
  override val cassandraCluster: CassandraAsyncContext[SnakeCase.type] = {
    new CassandraAsyncContext(SnakeCase, testSession, preparedStatementCacheSize)
  }

}
