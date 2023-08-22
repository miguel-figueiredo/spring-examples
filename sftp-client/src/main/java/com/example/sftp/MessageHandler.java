package com.example.sftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.sftp.client.SftpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Configuration
@Slf4j
public class MessageHandler {

    private SessionFactory<SftpClient.DirEntry> sessionFactory;

    public MessageHandler(SessionFactory<SftpClient.DirEntry> sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @ServiceActivator(inputChannel = "stream")
    @Bean
    public InputStreamMessageHandler handle() {
        return this::handle;
    }

    private void handle(Message<InputStream> message) {
        log.info("Headers: {}", message.getHeaders());
        final String directory = message.getHeaders().get("file_remoteDirectory").toString();
        final String file = message.getHeaders().get("file_remoteFile").toString();
        log.info("File: {}", file);
        InputStream inputStream = message.getPayload();
        try {
            log.info("Payload: {}", new String(inputStream.readAllBytes()));
            new SftpRemoteFileTemplate(sessionFactory).remove(Path.of(directory, file ).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface InputStreamMessageHandler {
        void handle(Message<InputStream> message);
    }
}