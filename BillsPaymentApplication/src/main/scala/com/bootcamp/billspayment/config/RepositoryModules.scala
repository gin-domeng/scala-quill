package com.bootcamp.billspayment.config

import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class RepositoryModules {

  @Bean
  def billerRepository : BillerDAO = new BillerRepository

}
