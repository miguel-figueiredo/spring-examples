package com.example.ioc;

import com.example.ioc.factory.HelloFactory;
import com.example.ioc.multiple.HelloName;
import com.example.ioc.strategy.HelloStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private HelloStrategy helloStrategy;
    private List<HelloName> names;
    private HelloFactory helloFactory;

    public HelloController(
            HelloStrategy helloStrategy,
            List<HelloName> names,
            HelloFactory helloFactory) {
        this.helloStrategy = helloStrategy;
        this.names = names;
        this.helloFactory = helloFactory;
    }

    @GetMapping
    public String getOne() {
        return helloStrategy.hello();
    }

    @GetMapping("/multiple")
    public String getMultiple() {
        return names.stream()
                .map(HelloName::name)
                .collect(joining(", "));
    }

    @GetMapping("/{name}")
    public String getName(@PathVariable("name") String name) {
        return helloFactory.get(name).name();
    }
}
