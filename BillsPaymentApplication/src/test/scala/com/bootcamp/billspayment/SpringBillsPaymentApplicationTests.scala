package com.bootcamp.billspayment

import org.cassandraunit.spring.{CassandraDataSet, CassandraUnitTestExecutionListener, EmbeddedCassandra}
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(classOf[SpringJUnit4ClassRunner])
@TestExecutionListeners(Array(classOf[CassandraUnitTestExecutionListener]))
@CassandraDataSet(value = Array("biller.cql"), keyspace = "billspayment")
@EmbeddedCassandra
	class SpringBillsPaymentApplicationTests {

		@Test
		@throws[Exception]
		def spring_givenEmbeddedCassandraInstance_whenStarted_thenQuerySuccess(): Unit = {
			val session = EmbeddedCassandraServerHelper.getSession
			val result = session.execute("select * from biller WHERE code='PLDT' ALLOW FILTERING")
			assertThat(result.iterator.next.getString("short_name"), is("PLDT"))
		}

}
