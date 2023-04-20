package profiles

import com.bootcamp.billspayment.repos.BillerRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.{Configuration, Profile}


@Profile(Array("tst"))
@Configuration
class DBProfileTstConfiguration extends DBSession { //with CassandraConf {

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

  override def session: Unit = logger.info("TST CQL SESSION")
}
