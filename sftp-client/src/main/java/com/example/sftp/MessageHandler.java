package com.example.sftp;

import com.example.sftp.SftpStreamingConfiguration.SftpStreamingMetadataStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.sftp.client.SftpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Configuration
@Slf4j
public class MessageHandler {

    private final SessionFactory<SftpClient.DirEntry> sessionFactory;
    private final SftpStreamingMetadataStore metadataStore;

    public MessageHandler(
            SessionFactory<SftpClient.DirEntry> sessionFactory,
            SftpStreamingMetadataStore metadataStore) {
        this.sessionFactory = sessionFactory;
        this.metadataStore = metadataStore;
    }

    @ServiceActivator(inputChannel = "stream")
    @Bean
    public InputStreamMessageHandler handle() {
        return this::handle;
    }

    private void handle(Message<InputStream> message) {
        log.info("Headers: {}", message.getHeaders());
        final String directory = message.getHeaders().get(FileHeaders.REMOTE_DIRECTORY).toString();
        final String file = message.getHeaders().get(FileHeaders.REMOTE_FILE).toString();
        log.info("File: {}", file);
        try {
            InputStream inputStream = message.getPayload();
            log.info("Payload: {}", new String(inputStream.readAllBytes()));
            log.info("Removing file {} from SFTP server", file);
            new SftpRemoteFileTemplate(sessionFactory).remove(Path.of(directory, file ).toString());
            log.info("Removing file {} from metadata", file);
            metadataStore.remove(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ServiceActivator(inputChannel = IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME)
    public Message<String> handleError(Exception e) {
        log.info("Error: {}", e.getMessage());
        return null;
    }

    @FunctionalInterface
    public interface InputStreamMessageHandler {
        void handle(Message<InputStream> message);
    }
}
