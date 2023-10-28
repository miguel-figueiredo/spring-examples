package com.example.archunit.hello.business.usecase;

import com.example.archunit.hello.business.model.Hello;
import com.example.archunit.hello.business.port.in.GetHellos;
import com.example.archunit.hello.business.port.out.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetHellosUseCase implements GetHellos {

    @Autowired
    HelloRepository repository;

    @Override
    public List<Hello> execute() {
        return repository.findAll();
    }
}
