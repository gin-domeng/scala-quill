package com.bootcamp.billspayment.repository

import com.bootcamp.billspayment.config.CassandraCluster
import com.bootcamp.billspayment.repository.domain.Biller
import io.getquill._

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

trait BillerRepository

  }



  override def findBillerById(id: UUID): Future[Option[Biller]] = ???

  override def findBillerByCode(code: String): Future[Option[Biller]] = ???

}
