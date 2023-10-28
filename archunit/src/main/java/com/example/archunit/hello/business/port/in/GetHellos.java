package com.example.archunit.hello.business.port.in;

import com.example.archunit.hello.business.model.Hello;

import java.util.List;

public interface GetHellos {

    List<Hello> execute();
}
