package com.example.hexagonal.name.business.usecase;

import com.example.hexagonal.name.port.in.AddName;
import org.springframework.stereotype.Service;

// TODO: replace with alias @UseCase
@Service
public class AddNameUseCase implements AddName {
    @Override
    public void execute(final String name) {
        System.out.println("Name added: " + name);
    }
}
