package com.elmdash.freckles.contacts.impl

import com.elmdash.freckles.contacts.api.Contact
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

import scala.collection.immutable.Seq




//sealed trait UserCommand extends ReplyType[String]

object ContactSerializerRegistry extends JsonSerializerRegistry {
  override def serializers = Seq(
    JsonSerializer[Contact]
  )
}