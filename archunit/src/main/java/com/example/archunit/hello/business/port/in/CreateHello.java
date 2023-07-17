package com.example.archunit.hello.business.port.in;

import com.example.archunit.hello.adapter.postgres.JpaHelloRepository;
import com.example.archunit.hello.business.usecase.CreateHelloUseCase;

public interface CreateHello {

    void execute();
}
