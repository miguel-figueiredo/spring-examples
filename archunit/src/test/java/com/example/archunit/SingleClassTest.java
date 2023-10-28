package com.example.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.example.archunit")
public class SingleClassTest {

    @ArchTest
    static final ArchRule should_be_free_of_cycles =
            SlicesRuleDefinition.slices().matching("com.example.archunit.(**)..").should().beFreeOfCycles();


    @ArchTest
    static final ArchRule hexagonal_architecture =
            Architectures.onionArchitecture()
                    .domainModels("..model..")
                    .domainServices(
                            "..port..",
                            "..usecase.."
                            )
                    .applicationServices("com.example.archunit..")
                    .adapter("jpa", "..adapter.jpa..")
                    .adapter("rest", "..adapter.rest..");

    @ArchTest
    static final ArchRule adapter_dependencies =
            noClasses().that().resideInAPackage("..adapter..")
                    .should().dependOnClassesThat().resideInAPackage("..usecase..");

    @ArchTest
    static final ArchRule port_dependencies =
            noClasses().that().resideInAPackage("..port..")
                    .should().dependOnClassesThat().resideInAPackage("..adapter..")
                    .orShould().dependOnClassesThat().resideInAPackage("..usecase..");

    @ArchTest
    static final ArchRule model_dependencies =
            noClasses().that().resideInAPackage("..model..")
                    .should().dependOnClassesThat().resideInAPackage("..usecase..")
                    .orShould().dependOnClassesThat().resideInAPackage("..port..")
                    .orShould().dependOnClassesThat().resideInAPackage("..adapter..");

    @ArchTest
    static final ArchRule usecase_dependencies =
            noClasses().that().resideInAPackage("..usecase..")
                    .should().dependOnClassesThat().resideInAPackage("..port.in..")
                    .andShould().dependOnClassesThat().resideInAPackage("..adapter..");

}