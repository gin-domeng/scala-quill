package com.bootcamp.billspayment.controller

import com.bootcamp.billspayment.repository.domain.Biller
import com.bootcamp.billspayment.service.BillerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RestController}

import java.util.UUID

@RestController
class BillerController {

  @Autowired
  private val billerService : BillerService = null


  @GetMapping(path = Array("/billers/{code}"))
  def getBillerCode(@PathVariable(name = "code") code : String): ResponseEntity[Biller] = {

    //ResponseEntity.ok(billerService.getBiller(code))
    ResponseEntity.ok(Biller( UUID.randomUUID(), "PLDT", "PLDT", "PHILIPPINE LONG DISTANCE ....", true, "ACTIVE"))
  }

}
