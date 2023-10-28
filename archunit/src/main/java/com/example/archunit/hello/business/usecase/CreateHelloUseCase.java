package com.example.archunit.hello.business.usecase;

import com.example.archunit.hello.business.model.Hello;
import com.example.archunit.hello.business.port.in.CreateHello;
import com.example.archunit.hello.business.port.out.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateHelloUseCase implements CreateHello {

    @Autowired
    HelloRepository repository;

    @Override
    public void execute() {
        repository.persist(new Hello(UUID.randomUUID(), "Hello world!"));
    }
}
