package com.example.hexagonal.spring;

import com.example.hexagonal.util.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.example.hexagonal",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION, value = UseCase.class),
        }
)
public class ComponentScanConfig {
}
