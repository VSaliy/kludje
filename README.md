KludJe
======

KludJe is a Java API that aims to exploit features introduced in Java 8 to reduce verbosity and enhance expressiveness.

Use it to:

 - Implement less verbose equals, hashCode, and toString in your classes
 - Avoid try/catch blocks in lambdas/method references with a generalized exception throwing/catching mechanism
   - Add this capability for any functional interface using the Kludje annotation processor
 - Avoid nested null checks in getter chains with a one-line call

Documentation
=============

See [kludje.uk](http://kludje.uk) or [underlying documentation](http://mcdiae.github.io/kludje/) for binary downloads and usage information.

Build Environment
=================

Software:

 - [JDK 8](https://jdk8.java.net/)
 - [Gradle](http://www.gradle.org/)
 - See lastbuild_info.txt for most recent tool stack

To build:

```
  gradle build
```

To build with test coverage reports:

```
  gradle clean build jacoco
```

License
=======

[Apache 2.0](https://github.com/mcdiae/kludje/blob/master/LICENSE)

Stability
=========

Limited testing; breaking changes possible in future versions.

Release Notes
=============

 - version 0.3: breaking API changes in nary package (consumer methods "apply" changed to "accept")
 - version 0.7: breaking API changes: Meta.XGetter interfaces deprecated; will be removed in future release; recompile lambdas to fix
 - version 0.8: from 0.8 onwards master branch is last release
