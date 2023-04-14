package com.bootcamp.billspayment.controller

import com.bootcamp.billspayment.repos.BillerDAO
import com.bootcamp.billspayment.repos.domain.Biller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@RestController
class BillerController {

  @Autowired
  private val billerService : BillerDAO = null

  @PostMapping(path = Array("/biller"))
  def addBiller(@RequestBody biller: Biller): ResponseEntity[String] = {

    billerService.upsertBiller(biller)

    ResponseEntity.ok("DONE")
  }

  @GetMapping(path = Array("/billers"))
  def getAllBillers(): Unit = {

   /* val billers = .map(_.toList)

    val billers: List[Biller] =
      for{ data <- billerService.findBillers()
           entities <- data.toList}
      yield  entities
*/

      billerService.findBillers().onComplete {
      case Success(data) => ResponseEntity.ok(data.toList)
      case Failure(t) => ResponseEntity.ok(t.getLocalizedMessage)
    }


  }


}
