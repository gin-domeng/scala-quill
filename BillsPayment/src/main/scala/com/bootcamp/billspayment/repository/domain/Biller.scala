package com.bootcamp.billspayment.repository.domain

import java.util.UUID

/*
create table biller (id UUID,
code text,
short_name text,
full_name text,
enabled boolean,
status text,
PRIMARY KEY (id));
*/
case class Biller(id: UUID,
                  code: String,
                  shortName: String,
                  fullName: String,
                  enabled: Boolean,
                  status: String)
