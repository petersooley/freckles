package com.elmdash.freckles.contacts.api

import java.util.UUID

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}
import scala.collection.immutable.Seq

object ContactsService {
  val TOPIC_NAME = "contacts"
}

trait ContactsService extends Service {

  def listContacts(uuid: UUID): ServiceCall[NotUsed, Seq[Contact]]

  override final def descriptor = {
    import Service._

    named("contacts")
      .withCalls(
        pathCall("/contacts/:uuid", listContacts _)
      )
      .withAutoAcl(true)
  }
}


case class Contact(id: UUID, contactId: Option[UUID], contactEmail: Option[String])

object Contact {
  implicit val format: Format[Contact] = Json.format
}