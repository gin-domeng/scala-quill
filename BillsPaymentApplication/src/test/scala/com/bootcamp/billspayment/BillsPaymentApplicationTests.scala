package com.bootcamp.billspayment;

import com.datastax.oss.driver.api.core.CqlSession
import org.cassandraunit.CQLDataLoader
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.hamcrest.CoreMatchers.is
import org.hamcrest.MatcherAssert.assertThat
import org.junit.{After, Before, Test}

class BillsPaymentApplicationTests {

	var session: CqlSession = null

	@Before
	@throws[Exception]
	def setUp(): Unit = {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra
		session = EmbeddedCassandraServerHelper.getSession
		new CQLDataLoader(session).load(new ClassPathCQLDataSet("biller.cql", "billspayment"))
	}

	@After
	@throws[Exception]
	def tearDown(): Unit = {
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra
	}

	@Test
	@throws[Exception]
	def givenEmbeddedCassandraInstance_whenStarted_thenQuerySuccess(): Unit = {
		val result = session.execute("select * from biller WHERE code='PLDT' ALLOW FILTERING")
		assertThat(result.iterator.next.getString("short_name"), is("PLDT"))
	}

}
