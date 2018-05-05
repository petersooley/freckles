package com.elmdash.freckles.contacts.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object ContactsService {
  val TOPIC_NAME = "contacts"
}

trait ContactsService extends Service {

  def hello(id: String): ServiceCall[NotUsed, String]

  def useGreeting(id: String): ServiceCall[GreetingMessage, Done]


  /**
    * This gets published to Kafka.
    */
  def greetingsTopic(): Topic[GreetingMessageChanged]

  override final def descriptor = {
    import Service._
    named("hello")
      .withCalls(
        pathCall("/api/hello/:id", hello _),
        pathCall("/api/hello/:id", useGreeting _)
      )
      .withTopics(
        topic(ContactsService.TOPIC_NAME, greetingsTopic)
          // Kafka partitions messages, messages within the same partition will
          // be delivered in order, to ensure that all messages for the same user
          // go to the same partition (and hence are delivered in order with respect
          // to that user), we configure a partition key strategy that extracts the
          // name as the partition key.
        .addProperty(
          KafkaProperties.partitionKeyStrategy,
          PartitionKeyStrategy[GreetingMessageChanged](_.name)
        )
      )
      .withAutoAcl(true)
  }
}

/**
  * The greeting message class.
  */
case class GreetingMessage(message: String)

object GreetingMessage {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[GreetingMessage] = Json.format[GreetingMessage]
}



/**
  * The greeting message class used by the topic stream.
  * Different than [[GreetingMessage]], this message includes the name (id).
  */
case class GreetingMessageChanged(name: String, message: String)

object GreetingMessageChanged {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[GreetingMessageChanged] = Json.format[GreetingMessageChanged]
}

