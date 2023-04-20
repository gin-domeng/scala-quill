package com.bootcamp.billspayment.cassandra

import com.bootcamp.billspayment.configurable.ConfigQuillContext
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.{Bean, Configuration, Primary}

import scala.concurrent.ExecutionContext.Implicits.global

@TestConfiguration
class TestDBConfig {

    @Bean
    @Primary
    def configQuillContext: ConfigQuillContext = new CassandraTestContext

    @Bean
    def billerRepository: BillerDAO = new BillerRepository(configQuillContext)
}
