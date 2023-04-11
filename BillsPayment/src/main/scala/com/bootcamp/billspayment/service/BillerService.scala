package com.bootcamp.billspayment.service

import com.bootcamp.billspayment.repository.BillerDAO
import com.bootcamp.billspayment.repository.domain.Biller
import org.springframework.stereotype.Service

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

trait BillerService {
  this : BillerDAO =>

  override def createBiller(biller: Biller)(implicit ec: ExecutionContext): Future[Unit] = createBiller(biller)

}

class BillerService extends BillerService with