package in.handyman.raven.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ConsumerK {

    @Test
    void testKafkaProducer() {
        final String topic = "purchases";

        // Load consumer configuration settings from a local file
        final Properties props = ProducerK.PROPS;

        // Add additional properties.
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Add additional required properties for this consumer app
        try (final Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of(topic));
            while (true) {
                final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (final ConsumerRecord<String, String> record : records) {
                    final String key = record.key();
                    final String value = record.value();
                    System.out.printf("Consumed event from topic %s: key = %-10s value = %s%n", topic, key, value);
                }
            }
        }
    }

}
