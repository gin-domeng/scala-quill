package com.bootcamp.billspayment.repos

import com.bootcamp.billspayment.repos.domain.Biller

import java.util.UUID
import scala.concurrent.Future

trait BillerDAO {

  def upsertBiller(biller: Biller): Future[Unit]

  def findBillers(): Future[List[Biller]]

  def findBillerById(id: UUID): Future[Option[Biller]]

  def findBillerByCode(code: String): Future[Option[Biller]]
}
