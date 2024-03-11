package com.example.aws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Service
public class SqsQueueListener {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    S3Client s3Client;

    @SqsListener("${events.queues.test-queue}")
    public void onMessage(Message<S3Events> message) {
        message.getPayload().records().forEach(this::handleEvent);
    }

    private void handleEvent(final S3Event event) {
        // The file name is encoded in the event, but needs to be sent decoded in the requests
        String file = URLDecoder.decode(event.key(), Charset.defaultCharset());

        if(!file.matches(".*\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")) {
            return;
        }

        log.info("Received event {}", event);

        CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                .sourceBucket(event.bucket())
                .sourceKey(file)
                .destinationBucket(event.bucket())
                .destinationKey("file")
                .build();

        s3Client.copyObject(copyObjectRequest);

        final DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(event.bucket)
                .key(file)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    public record S3Events(@JsonProperty("Records") List<S3Event> records) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(using = S3EventDeserializer.class)
    public record S3Event(String bucket, String key) {}

    public static class S3EventDeserializer extends JsonDeserializer<S3Event> {

        @Override
        public S3Event deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
            JsonNode productNode = parser.getCodec().readTree(parser);
            final String bucket = productNode.get("s3").get("bucket").get("name").textValue();
            final String key = productNode.get("s3").get("object").get("key").textValue();
            return new S3Event(bucket, key);
        }
    }
}
