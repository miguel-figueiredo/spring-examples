package com.example.archunit.hello.adapter.rest;

import com.example.archunit.hello.business.entity.Hello;
import com.example.archunit.hello.business.port.in.CreateHello;
import com.example.archunit.hello.business.port.in.GetHellos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hellos")
public class HelloController {

    @Autowired
    CreateHello createHello;

    @Autowired
    GetHellos getHellos;

    @PostMapping
    public void create() {
        createHello.execute();
    }

    @GetMapping
    public List<Hello> get() {
        return getHellos.execute();
    }
}
