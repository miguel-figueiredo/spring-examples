# Code Metrics

Exampel project with some code metrics and static code analytis

## Checkstyle

The checkstyle.xml was copied from the `google_checks.xml` with is written to target/checkstyle-checker.xml when the checkstyle plugin is configured with:

```
<configuration>
    <violationSeverity>warning</violationSeverity>
	<configLocation>google_checks.xml</configLocation>
</configuration>
```

This way we can customize the Google checks.

The checkstyle suppressions can be added automatically running the checkstyle jar (https://github.com/checkstyle/checkstyle/releases/) like:

`java -jar checkstyle-10.12.2-all.jar -c checkstyle.xml . -g > suppressions.xml && mv suppressions.xml checkstyle-suppressions.xml`

This will add XPath suppressions which need to be added to the checkstyle.xml like:

```
<module name="SuppressionXpathFilter">
    <property name="file" value="checkstyle-suppressions.xml" />
    <property name="optional" value="false"/>
</module>
```

## PMD

The pmd-ruleset.xml was copied from the default ruleset that is generated in the target directory when running PMD.

The PMD suppressions can be added automatically running by installing PMD (e.g. `brew install pmd`) and running the command:

`pmd pmd -d src/main/java -R pmd-ruleset.xml -f json | python generate-pmd-suppressions.py >pmd-suppressions.properties`

