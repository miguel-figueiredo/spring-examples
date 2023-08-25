package com.example.sftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.sftp.client.SftpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.metadata.ConcurrentMetadataStore;
import org.springframework.integration.sftp.filters.SftpPersistentAcceptOnceFileListFilter;
import org.springframework.integration.sftp.inbound.SftpStreamingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

import java.io.InputStream;

@Configuration
@Slf4j
public class SftpStreamingConfiguration {

    public static final String METADATA_STORE_KEY = "SFTP_FILE_";
    private final ConcurrentMetadataStore metadataStore;

    public SftpStreamingConfiguration(final ConcurrentMetadataStore metadataStore) {
        this.metadataStore = metadataStore;
    }

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
    @InboundChannelAdapter(channel = "stream", poller = @Poller(fixedDelay = "100"))
    public MessageSource<InputStream> ftpMessageSource() {
        SftpStreamingMessageSource messageSource = new SftpStreamingMessageSource(template());
        messageSource.setRemoteDirectory("upload");
        messageSource.setMaxFetchSize(1);
        messageSource.setFilter(new SftpPersistentAcceptOnceFileListFilter(metadataStore, METADATA_STORE_KEY));
        return messageSource;
    }

    @Bean
    public SftpRemoteFileTemplate template() {
        return new SftpRemoteFileTemplate(sftpSessionFactory());
    }

    @Bean
    public SftpStreamingMetadataStore sftpStreamingMetadataStore() {
        return new SftpStreamingMetadataStore(metadataStore);
    }

    public static class SftpStreamingMetadataStore {
        private final ConcurrentMetadataStore metadataStore;

        public SftpStreamingMetadataStore(ConcurrentMetadataStore metadataStore) {
            this.metadataStore = metadataStore;
        }

        public void remove(String fileName) {
            metadataStore.remove(METADATA_STORE_KEY + fileName);
        }
    }
}
