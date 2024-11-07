package com.example.validation;

import org.springframework.stereotype.Service;

@Service
public class ByeUseCase implements Bye {
    @Override
    public String bye(final String name) {
        return "Goodbye, " + name + ".\n";
    }
}
