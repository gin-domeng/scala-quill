package com.bootcamp.billspayment.config

import com.bootcamp.billspayment.configurable.ConfigQuillContext
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}

import scala.concurrent.ExecutionContext.Implicits.global

@Configuration
class RepositoryModules @Autowired() (configImplQuillContext: ConfigQuillContext) {

  @Bean
  def billerRepository: BillerDAO =  new BillerRepository(configImplQuillContext)

  //@Autowired def quillContext
}
