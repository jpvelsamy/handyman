package in.handyman.raven.lambda.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.CaseFormat;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Log4j2
public class ElasticsearchAccessApi {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final RestHighLevelClient CLIENT = getRestHighLevelClientConnection();

    private static RestHighLevelClient getRestHighLevelClientConnection() {
        //TODO move to config
        final RestClientBuilder builder = RestClient
                .builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultIOReactorConfig(
                        IOReactorConfig.custom()
                                .setIoThreadCount(5)
                                .build()));
        return new RestHighLevelClient(builder);
    }

    protected static void createIndices(final Set<Class<?>> indexNames) {
        try {
            indexNames.stream().map(ElasticsearchAccessApi::toIndexName).forEach(indexName -> {
                log.info("Index creation for {} has been initiated", indexName);
                try {
                    if (!CLIENT.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT)) {
                        final CreateIndexRequest request = new CreateIndexRequest(indexName);
                        request.settings(Settings.builder().put("number_of_shards", 2).put("number_of_replicas", 1)
                                .put("mapping.total_fields.limit", 200000000));
                        final CreateIndexResponse createIndexResponse = CLIENT.indices().create(request,
                                RequestOptions.DEFAULT);
                        log.debug("response status {} shard {}", createIndexResponse.isAcknowledged(),
                                createIndexResponse.isShardsAcknowledged());
                    }
                } catch (Exception ex) {
                    throw new HandymanException("Index creation failed for " + indexName, ex);
                }
                log.info("{} has already exists", indexName);
            });
        } catch (Exception ex) {
            throw new HandymanException("Index creation failed", ex);
        }

    }


    protected static void saveIndex(final String indexId, final Object payload) {
        try {
            final String indexName = toIndexName(payload.getClass());
            log.debug("save IndexName {} ID {} payload {}", indexName, indexId, payload);
            final IndexRequest request = getIndexRequest(indexName, indexId, payload);
            final IndexResponse indexResponse = CLIENT.index(request, RequestOptions.DEFAULT);
            log.debug("response status {} payload {}", indexResponse.status(), indexResponse.getResult());
        } catch (Exception ex) {
            throw new HandymanException("Index save failed", ex);
        }
    }

    private static IndexRequest getIndexRequest(final String indexName, final String indexId, final Object payload) throws JsonProcessingException {
        final IndexRequest request = new IndexRequest(indexName);
        request.id(indexId);
        request.source(MAPPER.writeValueAsString(payload), XContentType.JSON);
        return request;
    }

    protected static DeleteResponse deleteIndex(final String indexName, final String indexId) {
        try {
            log.debug("{} ID {} has been requested to delete", indexName, indexId);
            var deleteRequest = new DeleteRequest(indexName, indexId);
            final DeleteResponse deleteResponse = CLIENT.delete(deleteRequest, RequestOptions.DEFAULT);
            log.debug("response status {} payload {}", deleteResponse.status(), deleteResponse.getResult());
            log.info("{} ID {} has been deleted", indexName, indexId);
            return deleteResponse;
        } catch (Exception ex) {
            throw new HandymanException("Index deletion failed", ex);
        }
    }

    protected static void updateIndexAttribute(final Class<?> name, final String indexId, final Map<String, Object> attributes) {
        try {
            final String indexName = toIndexName(name);
            log.debug("update field for IndexName {} ID {} attributes {} ", indexName, indexId,
                    attributes);
            final UpdateRequest updateRequest = new UpdateRequest();
            final XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
            attributes.forEach((s, s2) -> {
                try {
                    xContentBuilder.field(s, s2);
                } catch (IOException e) {
                    log.error(attributes);
                    throw new HandymanException("Index update failed by " + s, e);
                }
            });
            final XContentBuilder builder = xContentBuilder.endObject();
            updateRequest.doc(builder);
            updateRequest.id(indexId);
            updateRequest.index(indexName);
            final UpdateResponse updateResponse = CLIENT.update(updateRequest, RequestOptions.DEFAULT);
            log.debug("response status {} payload {}", updateResponse.status(), updateResponse.getResult());
        } catch (Exception ex) {
            throw new HandymanException("Index update failed", ex);
        }
    }

    protected static Optional<?> getDocument(final Class<?> aClass, final String indexId) {
        try {
            final String indexName = toIndexName(aClass);
            final GetRequest request = new GetRequest(indexName, indexId);
            final GetResponse response = CLIENT.get(request, RequestOptions.DEFAULT);
            if (response.isExists()) {
                var version = response.getVersion();
                var sourceAsString = response.getSourceAsString();
                log.debug("fetch for IndexName {} ID {} version {} source {}", indexName, indexId,
                        version, sourceAsString);
                return Optional.of(MAPPER.readValue(sourceAsString, aClass));
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw new HandymanException("GetDOC failed", ex);
        }
    }

    protected static String toIndexName(final Class<?> aClass) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, aClass.getSimpleName());
    }

    protected static ArrayNode fetch(final String query) {
        final Request request = new Request("POST", "/_sql?format=json");
        request.setJsonEntity(String.format("{\"query\":\"%S\"}", query));
        try {
            var response = CLIENT.getLowLevelClient().performRequest(request);
            var responseBody = EntityUtils.toString(response.getEntity());
            final JsonNode jsonNode = MAPPER.readTree(responseBody);
            return toResult(jsonNode);
        } catch (Exception ex) {
            throw new HandymanException("fetch Failed", ex);
        }
    }

    private static ArrayNode toResult(final JsonNode jsonNode) {

        final Optional<ArrayNode> optionalColumns = Optional.of(jsonNode.get("columns")).map(ArrayNode.class::cast);
        final Optional<ArrayNode> optionalRows = Optional.of(jsonNode.get("rows")).map(ArrayNode.class::cast);

        final List<String> columns = optionalColumns
                .map(node -> StreamSupport.stream(node.spliterator(), false)
                        .map(node1 -> Optional.ofNullable(node1.get("name")).map(JsonNode::asText).orElse(null))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        final ArrayNode nodes = MAPPER.createArrayNode();
        return optionalRows
                .filter(node -> !columns.isEmpty())
                .map(node -> {
                    final int size = columns.size();
                    StreamSupport.stream(node.spliterator(), false).forEach(node1 -> {
                        final AtomicInteger index = new AtomicInteger();
                        final ObjectNode objectNode = MAPPER.createObjectNode();
                        node1.forEach(node2 -> {
                            if (index.get() < size) {
                                objectNode.set(columns.get(index.getAndIncrement()), node2);
                            }
                        });
                        nodes.add(objectNode);
                    });
                    return nodes;
                })
                .orElse(nodes);

    }

}
