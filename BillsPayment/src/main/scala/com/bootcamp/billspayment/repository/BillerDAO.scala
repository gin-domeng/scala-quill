package com.bootcamp.billspayment.repository

import com.bootcamp.billspayment.config.CassandraCluster
import com.bootcamp.billspayment.repository.BillerRepository.{cassandraCluster, cassandraKeyspace, preparedStatementCacheSize}
import com.bootcamp.billspayment.repository.domain.Biller
import io.getquill.{CassandraAsyncContext, SnakeCase}

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

trait BillerDAO {

  def createBiller(biller: Biller)(implicit ec: ExecutionContext): Future[Unit]

  def updateBiller(biller: Biller): Future[Unit]

  def findBillers()(implicit ec: ExecutionContext): Future[List[Biller]]

  def findBillerById(id : UUID ): Future[Option[Biller]]

  def findBillerByCode(code : String ): Future[Option[Biller]]
}

object QuillDAO extends CassandraCluster with BillerDAO {

  val ctx = new CassandraAsyncContext(SnakeCase, cassandraCluster, preparedStatementCacheSize)  {
    var cluster = cassandraCluster
    var keySpace = cassandraKeyspace
  }

  import ctx._

  override def createBiller(biller: Biller)(implicit ec: ExecutionContext) : Future[Unit] ={
    val q = quote {
      query[Biller].insert(lift(biller))
    }
    ctx.run(q)

  }


  override def updateBiller(biller: Biller): Future[Unit] = ???
  /*val q = quote {
    query[Biller]
      .filter(p => p.company == lift(biller.company) && p.id == lift(biller.id))
      .update(_.name -> lift(biller.name), _.status -> lift(biller.status))
  }
  ctx.run(q)*/

  override def findBillers()(implicit ec: ExecutionContext): Future[List[Biller]] = {

    val q = quote {
      query[Biller]
    }
    ctx.run(q)

  }



  override def findBillerById(id: UUID): Future[Option[Biller]] = ???

  override def findBillerByCode(code: String): Future[Option[Biller]] = ???

}
