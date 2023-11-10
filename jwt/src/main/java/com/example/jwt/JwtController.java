package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final JwtConfiguration configuration;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtController(JwtConfiguration configuration, Algorithm algorithm) {
        this.configuration = configuration;
        this.algorithm = algorithm;
        this.verifier = JWT.require(algorithm)
                .withIssuer(configuration.getIssuer())
                .build();
    }

    @GetMapping
    public String get() {
        return JWT.create()
                .withIssuer(configuration.getIssuer())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneOffset.UTC)
                .plusSeconds(configuration.getDuration().getSeconds()).toInstant();
    }

    @PostMapping("{jwt}")
    public void verify(@PathVariable("jwt") String jwt) {
        verifier.verify(jwt);
    }
}
