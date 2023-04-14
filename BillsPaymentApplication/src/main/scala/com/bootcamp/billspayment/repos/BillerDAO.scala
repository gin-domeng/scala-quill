package com.bootcamp.billspayment.repos

import com.bootcamp.billspayment.repos.domain.Biller

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

trait BillerDAO {

  def upsertBiller(biller: Biller)(implicit ec: ExecutionContext): Future[Unit]

  def findBillers()(implicit ec: ExecutionContext): Future[List[Biller]]

  def findBillerById(id : UUID ): Future[Option[Biller]]

  def findBillerByCode(code : String ): Future[Option[Biller]]
}
