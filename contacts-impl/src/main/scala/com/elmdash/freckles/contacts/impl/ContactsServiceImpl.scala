package com.elmdash.freckles.contacts.impl

import java.util.UUID

import com.elmdash.freckles.contacts.api.Contact
import com.elmdash.freckles.contacts.api.ContactsService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import scala.collection.immutable.Seq
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ContactsServiceImpl extends ContactsService {


  override def listContacts(uuid: UUID) = ServiceCall {
    _ => Future {
      Seq(
        Contact(UUID.randomUUID(), Option(UUID.randomUUID()), None),
        Contact(UUID.randomUUID(), None, Option("test@test.com"))
      )
    }
  }
}
