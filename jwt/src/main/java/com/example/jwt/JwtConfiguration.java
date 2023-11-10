package com.example.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;

@Configuration
public class JwtConfiguration {

    public static final String ALGORITHM = "EC";

    @Value("${jwt.key.public}")
    private String publicKey;

    @Value("${jwt.key.private}")
    private String privateKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.duration}")
    private Duration duration;

    public String getIssuer() {
        return issuer;
    }

    public Duration getDuration() {
        return duration;
    }

    @Bean
    Algorithm publicKey() {
        return Algorithm.ECDSA256(loadPublicKey(), loadPrivateKey());
    }

    private ECPublicKey loadPublicKey() {
        try {
            byte[] encoded = Base64.getDecoder().decode(publicKey);
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return (ECPublicKey) kf.generatePublic(keySpec);
        } catch (Exception e) {
            throw new KeyLoadingException("Error loading public key", e);
        }
    }

    private ECPrivateKey loadPrivateKey() {
        try {
            byte[] encoded = Base64.getDecoder().decode(privateKey);
            KeyFactory kf = KeyFactory.getInstance("EC");
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return (ECPrivateKey) kf.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new KeyLoadingException("Error loading private key", e);
        }
    }

    private static class KeyLoadingException extends RuntimeException {
        public KeyLoadingException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
