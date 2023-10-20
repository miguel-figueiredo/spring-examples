package com.example.metrics;

// This Checkstyle and PMD violation is suppressed
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Some javadoc to please checkstyle.
 */
@RestController
@RequestMapping("/hello")
public final class HelloController {

    /**
     * More javadoc to please checkstyle.
     *
     * @return the hello
     */
    @GetMapping
    public String hello() {
        return "Hello world!";
    }

    // This SpotBugs violation is suppressed
    public Boolean method() {
        return null;
    }
}
