package com.bootcamp.billspayment.repos

import com.bootcamp.billspayment.config.CassandraContext
import com.bootcamp.billspayment.repos.domain.Biller
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class BillerRepository extends BillerDAO with CassandraContext {

  import ctx._
  val billerTbl = quote(querySchema[Biller]("biller"))

  override def upsertBiller(biller: Biller)(implicit ec: ExecutionContext) : Future[Unit] ={

    run(quote {
      billerTbl.insertValue(lift(biller))
    })

  }

  override def findBillers()(implicit ec: ExecutionContext): Future[List[Biller]] = {

    val q = quote {
        query[Biller]
      }
      run(q)

  }



  override def findBillerById(id: UUID): Future[Option[Biller]] = ???

  override def findBillerByCode(code: String): Future[Option[Biller]] = ???

}
