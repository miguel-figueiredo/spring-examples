package com.example.archunit.hello.business.port.out;

import com.example.archunit.hello.business.model.Hello;

import java.util.List;

public interface HelloRepository {

    void persist(Hello hello);

    List<Hello> findAll();
}
