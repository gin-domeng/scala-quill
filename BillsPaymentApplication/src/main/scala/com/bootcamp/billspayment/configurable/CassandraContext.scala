package com.bootcamp.billspayment.configurable

import com.bootcamp.billspayment.dbConn.CassandraConf
import com.bootcamp.billspayment.quill.QuillContext
import com.datastax.oss.driver.api.core.CqlSession
import io.getquill.{CassandraAsyncContext, SnakeCase}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

trait ConfigQuillContext extends CassandraConf {
   val cassandraCluster : CassandraAsyncContext[SnakeCase.type]
}

/*class ConfigImplQuillContext @Autowired()(quilContext: QuillContext) extends ConfigQuillContext {

   private val logger = LoggerFactory.getLogger(classOf[ConfigImplQuillContext])

   override lazy val cassandraCluster : CassandraAsyncContext[SnakeCase.type] = {
      logger.info("ConfigImplQuillContext ...... " + quilContext.cassandraKeyspace)
      new CassandraAsyncContext(SnakeCase, quilContext.session, preparedStatementCacheSize)
   }

}*/

class ConfigImplQuillContext @Autowired()(quilContext: QuillContext, cache : Long) extends
   CassandraAsyncContext[SnakeCase.type](SnakeCase, quilContext, cache) with ConfigQuillContext {
}