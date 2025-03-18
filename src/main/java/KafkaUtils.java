import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;

import java.util.Collections;
import java.util.Properties;

public class KafkaUtils {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void publishMessage(String topic, String key, String value) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(topic, key, value));
        producer.close();
    }

    public static String consumeMessage(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        String message = "";
        for (ConsumerRecord<String, String> record : consumer.poll(5000)) {
            message = record.value();
        }
        consumer.close();
        return message;
    }
}
