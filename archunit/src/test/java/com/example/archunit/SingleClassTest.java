package com.example.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

@AnalyzeClasses(packages = "com.example.archunit")
public class SingleClassTest {

    @ArchTest
    static final ArchRule should_be_free_of_cycles =
            SlicesRuleDefinition.slices().matching("com.example.archunit.(**)..").should().beFreeOfCycles();


    @ArchTest
    static final ArchRule hexagonal_architecture =
            Architectures.onionArchitecture()
                    .domainModels("com.example.archunit.hello.business.entity..")
                    .domainServices(
                            "com.example.archunit.hello.business.port..",
                            "com.example.archunit.hello.business.usecase.."
                            )
                    .applicationServices("com.example.archunit..")
                    .adapter("postgres", "com.example.archunit.hello.adapter.postgres..")
                    .adapter("rest", "com.example.archunit.hello.adapter.rest..");

}