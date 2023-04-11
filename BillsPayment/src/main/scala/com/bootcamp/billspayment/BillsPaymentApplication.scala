package com.bootcamp.billspayment

import com.bootcamp.billspayment.repository.BillerRepository
import com.datastax.oss.driver.api.core.uuid.Uuids
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
/*import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication*/
import com.bootcamp.billspayment.repository.domain.Biller

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

/*
CREATE KEYSPACE IF NOT EXISTS billspayment
 WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1} AND DURABLE_WRITES = false;
 */
@SpringBootApplication
class BillsPaymentApplication

object BillsPaymentApplication  extends App {
  SpringApplication.run(classOf[BillsPaymentApplication], args: _*)

  /*val id = Uuids.timeBased()
  val biller = Biller( id, "Meralco", "Meralco", "Manila Electric Railroad ....", true, "ACTIVE")

  Await.result(BillerRepository.createBiller(biller), 10.seconds)

  val billers = Await.result(BillerRepository.findBillers().map(entities => entities).collect(_.toList), 10.seconds)

  billers.foreach(entities => println(entities))*/
}
