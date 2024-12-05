package com.example.hexagonal.name.adapter.persistence;

import com.example.hexagonal.name.port.in.AddName;
import org.springframework.stereotype.Service;

@Service
public class AddNameDouble implements AddName {
    @Override
    public void execute(final String name) {

    }
}
