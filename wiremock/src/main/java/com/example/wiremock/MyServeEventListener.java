package com.example.wiremock;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ServeEventListener;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyServeEventListener implements ServeEventListener {

    @Override
    public void beforeMatch(ServeEvent serveEvent, Parameters parameters) {
    }

    @Override
    public void afterMatch(ServeEvent serveEvent, Parameters parameters) {
    }

    @Override
    public void beforeResponseSent(ServeEvent serveEvent, Parameters parameters) {
    }

    @Override
    public void afterComplete(ServeEvent serveEvent, Parameters parameters) {
        try {
            if(serveEvent.getWasMatched() && serveEvent.getRequest().getUrl().equals("/hello")) {
                System.out.println("Creating xpto file");
                final File file = new File("/tmp/xpto");
                try(FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write("XPTO");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "my-listener";
    }
}