package com.bootcamp.billspayment

import com.bootcamp.billspayment.cassandra.{CassandraTestContext, TestDBConfig}
import com.bootcamp.billspayment.configurable.ConfigQuillContext
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import io.getquill.{CassandraAsyncContext, SnakeCase}
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.scalatest._
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.{SpringBootTest, TestConfiguration}
import org.springframework.context.annotation.{Bean, Configuration, Import, Primary}
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

//FUTURE REF with AsyncFlatSpec


//@Import(Array(classOf[TestDBConfig]))
/*@SpringBootTest(classes = Array(classOf[BillsPaymentApplication]),
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Array(classOf[TestDBConfig]))*/
@RunWith(classOf[SpringRunner])
//@SpringBootTest
class BillRepositorySpec extends AsyncFlatSpec with BeforeAndAfter {

  var tstContext = new CassandraTestContext()

  @Autowired
  val billerRepository: BillerDAO = new BillerRepository(tstContext)

  before {

    new CQLDataLoader(tstContext.testSession).load(new ClassPathCQLDataSet("biller.cql", "billspayment"))
    //billerRepository = new BillerRepository (tstContext)
  }

  // RUNS on Test Session
  /*"Biller Records" should "return 1 Biller" in {
    val result = tstContext.testSession.execute("select * from biller WHERE code='NAWASA' ALLOW FILTERING")
    assertThat(result.iterator.next.getString("short_name"), is("NAWASA"))
  }*/

  //RUNGS on Local Database
  "Biller Repository Records" should "return 1 Biller" in {
//    billerRepository.upsertBiller(Biller(UUID.randomUUID(), "Meralco", "Meralco", "Meralco ....", true, "ACTIVE"))

    val result = billerRepository.findBillerByCode("NAWASA")

    /*for{
      entity <- result

    }println("BUMP " + entity.get)

    result map { biller => assert(biller.get.code == "PLDT") }
*/

    val code = result.map(biller => biller.get.code)
    //println(">>>> " + code.get.toString)
    //assertThat(code.get.toString, is("NAWASA"))
    code map { entity => assert(entity.toString == "NAWASA") }

  }


  /*@TestConfiguration class ConfigDB {
    @Bean
    @Primary
    def configImplQuillContext: ConfigQuillContext = new CassandraTestContext
  }*/
}

