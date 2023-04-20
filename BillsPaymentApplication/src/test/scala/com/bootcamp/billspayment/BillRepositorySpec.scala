package com.bootcamp.billspayment

import com.bootcamp.billspayment.cassandra.CassandraTestContext
import com.bootcamp.billspayment.repos.domain.Biller
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.flatspec.AsyncFlatSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner

import java.util.UUID

@RunWith(classOf[SpringRunner])
class BillRepositorySpec extends AsyncFlatSpec with BeforeAndAfter {

  val tstContext = new CassandraTestContext()
  val biller = Biller(UUID.randomUUID(), "Meralco", "Meralco", "Meralco ....", true, "ACTIVE")

  @Autowired
  val billerRepository: BillerDAO = new BillerRepository(tstContext)

  before {
    new CQLDataLoader(tstContext.testSession).load(new ClassPathCQLDataSet("biller.cql", "billspayment"))
  }

  "Biller Repository Records" should "Insert a Biller" in {

    billerRepository.upsertBiller(biller)

    val result = billerRepository.findBillerByCode("Meralco")
    val code = result.map(data => data.get.code)

    code map { field => assert(field.toString == "Meralco") }
  }

  "Biller Repository Records" should "Return a Biller" in {

    val result = billerRepository.findBillerByCode("NAWASA")
    val code = result.map(data => data.get.code)

    code map { field => assert(field == "NAWASA") }
  }

  "Biller Repository Records" should "Update a Biller" in {

    val updateBiller = biller.copy(enabled = false)

    billerRepository.upsertBiller(updateBiller)

    val result = billerRepository.findBillerByCode("Meralco")
    val code = result.map(data => data.get.enabled)

    code map { field => assert(field == false) }
  }

  /* Flaky since test ran in random sometimes it gets the right num of records
  "Biller Repository Records" should "Should Have 2-3 Billers but its @Flaky" in {
    billerRepository.upsertBiller(biller)
    val billers = billerRepository.findBillers()

    billers map { billers => assert(billers.count(x => true) == 2) }

  }*/

  "Biller Repository Records" should "Find by UUID" in {

    val billers = billerRepository.findBillerById(UUID.fromString("e6b42b29-2411-4fc7-800d-2ee553579b41"))

    billers map { billers => assert(billers.count(x => true) == 1) }

  }
}

