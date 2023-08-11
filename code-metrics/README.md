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

The checkstyle supressions can be added automatically running the checkstyle jar like:

`java -jar checkstyle-10.12.2-all.jar -c checkstyle.xml backend/ -g > checkstyle-suppressions.xml`

This will add XPath suppressions which need to be added to the checkstyle.xml like:

```
<module name="SuppressionXpathFilter">
    <property name="file" value="checkstyle-suppressions.xml" />
    <property name="optional" value="false"/>
</module>
```

