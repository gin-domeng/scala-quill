package profiles

import com.bootcamp.billspayment.dbConn.CassandraConf
import com.bootcamp.billspayment.repos.BillerRepository
import com.datastax.oss.driver.api.core.{CqlIdentifier, CqlSession}
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.{Bean, Configuration, Profile}
import org.springframework.stereotype.{Component, Service}

import java.net.InetSocketAddress


@Profile(Array("dev"))
@Configuration
class DBProfileConfiguration extends DBSession { //with CassandraConf {

  private val logger = LoggerFactory.getLogger(classOf[BillerRepository])

  /*@Bean
  override def session: CqlSession = {
    /*val builder = CqlSession.builder()
    for (cp <- cassandraHosts) builder.addContactPoint(new InetSocketAddress(cp, cassandraPort))
    builder.withLocalDatacenter(cassandraDatacenter)
    builder.withKeyspace(CqlIdentifier.fromCql(cassandraKeyspace))*/

    logger.info("LOADING DEV CONNECTION SETTINGS ")

//    builder.build()
    CqlSession.builder().build()
  }*/

  override def session: Unit = logger.info("DEV CQL SESSION")
}
