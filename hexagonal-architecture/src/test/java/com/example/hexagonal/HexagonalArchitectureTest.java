package com.example.hexagonal;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.example.hexagonal")
public class HexagonalArchitectureTest {

    @ArchTest
    static final ArchRule should_be_free_of_cycles =
            SlicesRuleDefinition.slices().matching("com.example.hexagonal.(**)..").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule hexagonal_architecture =
            Architectures.onionArchitecture()
                    .domainModels("..model..")
                    .domainServices(
                            "..usecase.."
                    )
                    .applicationServices("com.example.hexagonal..")
                    .adapter("adapter", "..adapter..");

    /**
     * Adapters should not depend on use cases.
     */
    @ArchTest
    static final ArchRule adapters_should_not_depend_on_use_cases =
            noClasses().that().resideInAPackage("..adapter..")
                    .should().dependOnClassesThat().resideInAPackage("..usecase..");

    /**
     * Adapters should not depend on output ports which as all interfaces,
     * except for port messages which are not interfaces.
     */
    @ArchTest
    static final ArchRule adapters_should_not_depend_output_ports =
            noClasses().that().resideInAPackage("..adapter..")
                    .should().accessClassesThat(
                            resideInAPackage("..port.out..")
                                    .and(JavaClass.Predicates.INTERFACES));

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
            noClasses().that().resideInAPackage("..domain.usecase..")
                    .should().dependOnClassesThat().resideInAPackage("..adapter..")
                    .orShould().accessClassesThat(
                            resideInAPackage("..domain.port.in..")
                                    .and(JavaClass.Predicates.INTERFACES));

    @ArchTest
    static final ArchRule domain_independent_from_frameworks =
            noClasses().that().resideInAPackage("..domain..")
                    .or().resideInAPackage("..port..")
                    .should().dependOnClassesThat().resideInAPackage("..springframework..");
}
