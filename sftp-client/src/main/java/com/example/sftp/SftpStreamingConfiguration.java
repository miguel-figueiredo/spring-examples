package com.example.sftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.sftp.client.SftpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.VfsResource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.metadata.ConcurrentMetadataStore;
import org.springframework.integration.metadata.MetadataStore;
import org.springframework.integration.redis.metadata.RedisMetadataStore;
import org.springframework.integration.sftp.filters.SftpPersistentAcceptOnceFileListFilter;
import org.springframework.integration.sftp.inbound.SftpStreamingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class SftpStreamingConfiguration {

    @Autowired
    ConcurrentMetadataStore metadataStore;

    @Bean
    public SessionFactory<SftpClient.DirEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost("localhost");
        factory.setPort(2222);
        factory.setUser("sftp");
        factory.setPassword("sftp");
        factory.setKnownHostsResource(new ClassPathResource("/known_hosts"));
        return new CachingSessionFactory<>(factory);
    }

    @Bean
    @InboundChannelAdapter(channel = "stream", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<InputStream> ftpMessageSource() {
        SftpStreamingMessageSource messageSource = new SftpStreamingMessageSource(template());
        messageSource.setRemoteDirectory("upload");
        messageSource.setMaxFetchSize(1);
        messageSource.setFilter(new SftpPersistentAcceptOnceFileListFilter(metadataStore, "sftpStreamingMessageSource"));
        return messageSource;
    }

    @Bean
    public SftpRemoteFileTemplate template() {
        return new SftpRemoteFileTemplate(sftpSessionFactory());
    }

    @ServiceActivator(inputChannel = "stream")
    @Bean
    public InputStreamMessageHandler handle() {
        return message -> {
            log.info("File: {}", message.getHeaders().get("file_remoteFile"));
            InputStream inputStream = message.getPayload();
            try {
                log.info("Payload: {}", new String(inputStream.readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface InputStreamMessageHandler {
        void handle(Message<InputStream> message);
    }
}
