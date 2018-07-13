# JBasic

This project is a collection of high quality, extremely useful and widely appreciated APIs targeting Java 1.7:

* [Guava](https://github.com/google/guava): Guava is a set of core libraries that includes new collection types (such as multimap and multiset), immutable collections, a graph library, functional types, an in-memory cache, and APIs/utilities for concurrency, I/O, hashing, primitives, reflection, string processing, and much more!

* [Joda-Time](http://www.joda.org/joda-time): Joda-Time provides a quality replacement for the Java date and time classes. Joda-Time is the de facto standard date and time library for Java prior to Java SE 8 ([JSR-310](https://jcp.org/en/jsr/detail?id=310)).

* [JSR-305](https://jcp.org/en/jsr/detail?id=305): annotations that can assist defect detection tools. Annotations like `@Nonnull`, `@Nullable`, `@Immutable` increase code and Javadoc clarity.  

* [Simple Logging Facade for Java](https://www.slf4j.org): the Simple Logging Facade for Java (SLF4J) serves as a simple facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time.

* [java-string-similarity](https://github.com/tdebatty/java-string-similarity): a library implementing different string similarity and distance measures. A dozen of algorithms (including Levenshtein edit distance and siblings, Jaro-Winkler, Longest Common Subsequence, cosine similarity etc.) are currently implemented.

* [JavaVerbalExpressions](https://github.com/VerbalExpressions/JavaVerbalExpressions): provides efficient APIs for building complex regular expression.

Over this APIs some new utilities are provided to enhance I/O operations, reflection capabilities, date/time detection and automated parsing, primitive types and primitive wrappers manipulation.

## Built With

* [Maven](https://maven.apache.org) - Dependency Management

## Getting Started

### Minimum Requirements

* Java 1.7 or above - tested with [OracleJDK 7.0](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html)

* One build automation tool:
   * Maven - [official download page](https://maven.apache.org/download.cgi)
   * [Gradle](https://gradle.org/)


### Adding JBasic to your build

1. This project is not yet available on the official maven repository: the project must be cloned and manually installed into your local Maven repository.
Some tests depends on [JSupport](https://github.com/mforoni/jsupport.git) that also needs to be cloned and manually installed.

1. Then you can start using JBasic by adding Maven dependencies into your Maven project.

To add a dependency on JBasic, use the following:
```xml
<dependency>
  <groupId>com.github.mforoni.jbasic</groupId>
  <artifactId>jbasic</artifactId>
  <version>0.1-SNAPSHOT</version>
</dependency>
```

To add a dependency using Gradle:
```
dependencies {
  compile 'com.github.mforoni.jbasic:jbasic:0.1-SNAPSHOT'
}
```

## Author

* **Marco Foroni** - [mforoni](https://github.com/mforoni)

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/mforoni/jbasic/blob/master/LICENSE) file for details


## IMPORTANT WARNINGS

1. This project is under development.

1. APIs marked with the `@Beta` annotation at the class or method level
are subject to change. They can be modified in any way, or even
removed, at any time. Read more about [`@Beta`](https://github.com/google/guava#important-warnings) annotation.
