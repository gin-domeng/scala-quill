package com.bootcamp.billspayment.repos

import com.bootcamp.billspayment.configurable.ConfigQuillContext
import com.bootcamp.billspayment.repos.domain.Biller
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class BillerRepository @Autowired() (configImplQuillContext: ConfigQuillContext)(implicit ec: ExecutionContext) extends BillerDAO {

  private val logger = LoggerFactory.getLogger(classOf[BillerRepository])

  import configImplQuillContext.cassandraCluster._

  private val billerTbl = quote(querySchema[Biller]("biller"))

  override def upsertBiller(biller: Biller) : Future[Unit] ={
    logger.info("BillerRepository >>>> " + configImplQuillContext.cassandraKeyspace )
    run(quote {
      billerTbl.insertValue(lift(biller))
    })

  }

  override def findBillers(): Future[List[Biller]] = {
    run(quote { billerTbl })
  }

  override def findBillerById(id: UUID): Future[Option[Biller]] = {
    run(quote {
      billerTbl.filter(_.id == lift(id)).allowFiltering
    }).map(_.headOption)

  }

  override def findBillerByCode(code: String): Future[Option[Biller]] = {
    logger.info("BillerRepository >>>> " + configImplQuillContext.cassandraKeyspace )
    run(quote {
      billerTbl.filter(_.code == lift(code)).allowFiltering
    }).map(_.headOption)

  }
}
