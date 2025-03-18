Feature: Kafka Integration Test

  Scenario: Send message to Kafka
    * def KafkaUtils = Java.type('kafka.KafkaUtils')
    * def topic = 'test-topic'
    * def key = 'test-key'
    * def value = 'Hello from Karate!'
    * KafkaUtils.publishMessage(topic, key, value)

  Scenario: Read message from Kafka
    * def KafkaUtils = Java.type('kafka.KafkaUtils')
    * def topic = 'test-topic'
    * def message = KafkaUtils.consumeMessage(topic)
    * match message == 'Hello from Karate!'
