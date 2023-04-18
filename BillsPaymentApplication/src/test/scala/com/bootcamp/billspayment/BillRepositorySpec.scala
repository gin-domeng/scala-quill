package com.bootcamp.billspayment

import com.bootcamp.billspayment.cassandra.CassandraTestContext
import com.bootcamp.billspayment.repos.domain.Biller
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global


// Direct to DB validation should be TEST CASSANDRA
class BillRepositorySpec extends AnyFlatSpec with BeforeAndAfter  with CassandraTestContext{

  var billerRepository: BillerDAO = null

  before {
    EmbeddedCassandraServerHelper.startEmbeddedCassandra
    val session = EmbeddedCassandraServerHelper.getSession
    new CQLDataLoader(session).load(new ClassPathCQLDataSet("biller.cql", "billspayment"))

    billerRepository = new BillerRepository
  }

  "Biller Records" should "return 1 Biller" in {
    val result = session.execute("select * from biller WHERE code='Meralco' ALLOW FILTERING")


    var shortName = ""

    while(result.iterator().hasNext){
      println("BILLER UUID " +result.iterator().next().getUuid("id"))
      shortName = result.iterator().next().getString("short_name")
    }

    assertThat(shortName, is("Meralco"))
  }

  "Biller Repository Records" should "return 1 Biller" in {
    billerRepository.upsertBiller(Biller(UUID.randomUUID(), "Meralco", "Meralco", "Meralco ....", true, "ACTIVE"))
    val result = billerRepository.findBillerByCode("Meralco")

    assertThat(result.isCompleted, is(true))
  }

}
