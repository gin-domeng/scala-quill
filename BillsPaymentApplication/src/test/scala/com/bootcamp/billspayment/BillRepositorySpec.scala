package com.bootcamp.billspayment

import com.bootcamp.billspayment.cassandra.CassandraTestContext
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.scalatest._
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}

import scala.concurrent.ExecutionContext.Implicits.global

//FUTURE REF with AsyncFlatSpec
class BillRepositorySpec extends  AnyFlatSpec with BeforeAndAfter  with CassandraTestContext{

  var billerRepository: BillerDAO = null

  before {

    new CQLDataLoader(session).load(new ClassPathCQLDataSet("biller.cql", "billspayment"))

    billerRepository = new BillerRepository //(new CassandraAsyncContext(SnakeCase, session, 1000))
  }

  // RUNS on Test Session
  "Biller Records" should "return 1 Biller" in {
    val result = session.execute("select * from biller WHERE code='NAWASA' ALLOW FILTERING")
    assertThat(result.iterator.next.getString("short_name"), is("NAWASA"))
  }

  //RUNGS on Local Database
  "Biller Repository Records" should "return 1 Biller" in {
    //billerRepository.upsertBiller(Biller(UUID.randomUUID(), "Meralco", "Meralco", "Meralco ....", true, "ACTIVE"))

    val result = billerRepository.findBillerByCode("PLDT")

    for{
      entity <- result

    }println("BUMP " + entity.get)

    result map { biller => assert(biller.get.code == "PLDT") }

  }

}
