package com.bootcamp.billspayment

import com.bootcamp.billspayment.repos.BillerRepository
import com.bootcamp.billspayment.repos.domain.Biller
import org.cassandraunit.spring.{CassandraDataSet, CassandraUnitTestExecutionListener, EmbeddedCassandra}
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.when
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global;

@RunWith(classOf[SpringJUnit4ClassRunner])
@TestExecutionListeners(Array(classOf[CassandraUnitTestExecutionListener]))
@CassandraDataSet(value = Array("biller.cql"), keyspace = "billspayment")
@EmbeddedCassandra
	class SpringBillsPaymentApplicationTests {

	//@MockBean val repository: BillerRepository = null

		@Test
		@throws[Exception]
		def spring_givenEmbeddedCassandraInstance_whenStarted_thenQuerySuccess(): Unit = {
			val session = EmbeddedCassandraServerHelper.getSession
			val result = session.execute("select * from biller WHERE code='PLDT' ALLOW FILTERING")
			assertThat(result.iterator.next.getString("short_name"), is("PLDT"))
		}


	/*@throws[Exception]
	def spring_reporitory_test(): Unit = {

		when(repository.findBillers(),
	}*/
}
