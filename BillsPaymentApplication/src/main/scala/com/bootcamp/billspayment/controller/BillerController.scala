package com.bootcamp.billspayment.controller

import com.bootcamp.billspayment.quill.QuillContext
import com.bootcamp.billspayment.repos.{BillerDAO, BillerRepository}
import com.bootcamp.billspayment.repos.domain.Biller
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RestController
class BillerController {

  private val logger = LoggerFactory.getLogger(classOf[BillerController])

  @Autowired
  private val quillContext : QuillContext = null

  @Autowired
  private val billerService: BillerDAO = null

  @PostMapping(path = Array("/biller"))
  def addBiller(@RequestBody biller: Biller): ResponseEntity[String] = {

    logger.info(">>>>>> SESSION ON PORT " + quillContext.session.getKeyspace )
    billerService.upsertBiller(biller)

    ResponseEntity.ok("DONE")
  }

  @GetMapping(path = Array("/billers"))
  def getAllBillers(): ResponseEntity[Future[List[Biller]]] = {
    ResponseEntity.ok(billerService.findBillers())
  }

  @GetMapping(path = Array("/biller/{code}"))
  def getBillerByCode(@PathVariable("code") code: String): ResponseEntity[Option[Biller]] = {

    val result = Await.result(billerService.findBillerByCode(code), Duration.fromNanos(100000000))

    ResponseEntity.ok(result)

  }


}
