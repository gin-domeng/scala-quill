package com.bootcamp.billspayment.quill

import com.bootcamp.billspayment.dbConn.CassandraConf
import com.datastax.oss.driver.api.core.CqlSession

trait QuillContext extends CassandraConf {

  val cassandraCluster: CqlSession
  lazy implicit val session: CqlSession = cassandraCluster
}
