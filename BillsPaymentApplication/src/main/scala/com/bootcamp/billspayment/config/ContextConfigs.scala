package com.bootcamp.billspayment.config

import com.bootcamp.billspayment.configurable.{ConfigImplQuillContext, ConfigQuillContext}
import com.bootcamp.billspayment.quill.{QuillContext, QuillDevImplContext, QuillLocalImplContext}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ContextConfig(@Value("${database.mode}") databaseMode: String) {

  val LOCAL : String = "local"
  val DEV : String = "dev"

  @Bean def quillContext: QuillContext = {
    if (LOCAL == databaseMode) {
      new QuillLocalImplContext()
    }else if (DEV == databaseMode) {
      new QuillDevImplContext()
    } else{
      throw new Exception("invalid value for 'database.mode': " + databaseMode)
    }
  }

  @Bean
  def configImplQuillContext : ConfigQuillContext = new ConfigImplQuillContext(quillContext)
}
