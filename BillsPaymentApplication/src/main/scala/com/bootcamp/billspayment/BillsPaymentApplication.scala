package com.bootcamp.billspayment;

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BillsPaymentApplication

/*
CREATE KEYSPACE IF NOT EXISTS billspayment
    WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1} AND DURABLE_WRITES = false;


create table biller (id UUID,
                 code text,
                 short_name text,
                 full_name text,
                 enabled boolean,
                 status text,
                 PRIMARY KEY (id));
 */
object BillsPaymentApplication extends App{
		SpringApplication.run(classOf[BillsPaymentApplication], args: _*)
}
