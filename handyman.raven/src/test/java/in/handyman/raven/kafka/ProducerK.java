package in.handyman.raven.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ProducerK {

    // Load producer configuration settings from a local file
    protected static final Properties PROPS = new Properties();
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        PROPS.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        PROPS.put("key.serializer", KafkaAvroSerializer.class.getName());
        PROPS.put("value.serializer", KafkaAvroSerializer.class.getName());
        PROPS.put("key.deserializer", KafkaAvroDeserializer.class.getName());
        PROPS.put("value.deserializer", KafkaAvroDeserializer.class.getName());

    }

    @Test
    void testKafkaProducer() {
        final String topic = "purchases";

        createTopic(topic);


        final String[] users = {"eabara", "jsmith", "sgarcia", "jbernard", "htanaka", "awalther"};
        final String[] items = {"book", "alarm clock", "t-shirts", "gift card", "batteries"};

        try (final Producer<String, String> producer = new KafkaProducer<>(PROPS)) {
            final long numMessages = 10L;
            for (long i = 0L; i < numMessages; i++) {
                final Random rnd = new Random();
                final String user = users[rnd.nextInt(users.length)];
                final FileContext item = FileContext.builder().filePath(items[rnd.nextInt(items.length)]).build();

                producer.send(
                        new ProducerRecord<>(topic, user, OBJECT_MAPPER.writeValueAsString(item)),
                        (event, ex) -> {
                            if (ex != null)
                                ex.printStackTrace();
                            else
                                System.out.printf("Produced event to topic %s: key = %-10s value = %s%n", topic, user, item);
                        });
            }

            producer.flush();
            System.out.printf("%s events were produced to topic %s%n", numMessages, topic);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    /**
     * We'll reuse this function to load properties from the Consumer as well
     */
    private void createTopic(final String topicName) {
        try (Admin admin = Admin.create(PROPS)) {
            int partitions = 1;
            short replicationFactor = 1;
            var newTopic = new NewTopic(topicName, partitions, replicationFactor);
            final List<String> singleton = Collections.singletonList(topicName);
            final KafkaFuture<Set<String>> listTopics = admin.listTopics().names();
            listTopics.whenComplete((topics, throwable) -> {
                final KafkaFuture<Void> deleteTopic;
                if (topics.contains(topicName)) {
                    final DeleteTopicsResult deleteTopicsResult = admin.deleteTopics(singleton);
                    deleteTopic = deleteTopicsResult.all();
                } else {
                    deleteTopic = KafkaFuture.completedFuture(null);
                }
                deleteTopic.whenComplete((unused, throwable1) -> {
                    var createTopic = admin.createTopics(Collections.singletonList(newTopic));
                    try {
                        createTopic.values().get(topicName).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

            });
        }
    }


}
