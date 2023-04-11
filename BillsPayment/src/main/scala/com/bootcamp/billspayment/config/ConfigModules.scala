package bootcamp.billspayment.config

import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ConfigModules {


  @Bean
  private implicit val billerService = BillerService

}
