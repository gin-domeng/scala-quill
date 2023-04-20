package com.bootcamp.billspayment.cassandra

import org.cassandraunit.dataset.CQLDataSet
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet
import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.cassandraunit.{CQLDataLoader, CassandraCQLUnit}

/**
  * Created by M. Hoekstra on 11-3-2016.
  * Copied from https://gitlab.ing.net/Twilio/contacting-test/blob/feature/merak17/src/main/scala/nl/ing/api/contacting/test/cassandra/ScalaCassandraUnit.scala
  */
class ScalaCassandraUnit(dataSet: CQLDataSet, configurationFileName: String, readTimeoutMillis: Long) extends CassandraCQLUnit(dataSet, configurationFileName, readTimeoutMillis) {

  @throws(classOf[Exception])
  override def before() = {
    super.before()
  }

  override def after() = {
    super.after()
  }


  def load(initCql : String, dataSet: String, recreateKeySpace: Boolean, keySpaceName : String): Unit = {
    val dataLoader = new CQLDataLoader(super.getSession)
    if (recreateKeySpace) {
      dataLoader.load(new ClassPathCQLDataSet(initCql, true, true, keySpaceName))
    }
    dataLoader.load(new ClassPathCQLDataSet(dataSet, false, false))
  }
}

object ScalaCassandraUnit {
  def init(initCql : String, dataset: Option[String], recreateDatabase: Boolean, timeout: Long, keySpaceName : String): ScalaCassandraUnit = {
    val cassandraUnit: ScalaCassandraUnit = startCassandra(initCql, timeout)
    loadData(initCql, dataset, recreateDatabase, cassandraUnit, keySpaceName)
    cassandraUnit
  }

  def loadData(initCql : String, dataset: Option[String], recreateDatabase: Boolean, cassandraUnit: ScalaCassandraUnit, keySpaceName : String): Unit = {
    if (dataset.isDefined) {
      cassandraUnit.load(initCql, dataset.get, !recreateDatabase, keySpaceName)
    }
  }

  def startCassandra(initCql : String, timeout: Long): ScalaCassandraUnit = {
    // setup cassandra
    val cassandraUnit = new ScalaCassandraUnit(new ClassPathCQLDataSet(initCql), EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE, timeout)
    cassandraUnit.before()
    cassandraUnit
  }
}