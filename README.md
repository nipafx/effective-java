# Effective Java

This project contains examples for my [YouTube series on _Effective Java, Third Edition_](https://www.youtube.com/playlist?list=PL_-IO8LOLuNqUzvXfRCWRRJBswKEbLhgN).

Related links:

* affiliate links to _Effective Java, Third Edition_:
	* [InformIT](https://click.linksynergy.com/deeplink?id=kGJiVJGY2UU&mid=24808&murl=http%3A%2F%2Fwww.informit.com%2Fstore%2Feffective-java-9780134685991) (the publisher)
	* [Amazon USA](https://amzn.to/2QI0D0S)
	* [Amazon Germany](https://amzn.to/2OvsWOu)
* visit [courses.codefx.org](http://courses.codefx.org) for online courses on Java

## Creating and destroying objects

* [Item 1](https://www.youtube.com/watch?v=WUROOKn2OTk): _Consider static factory methods instead of constructors_ -
  [examples](src/main/java/org/codefx/demo/effective_java/_01_static_factory_methods/Main.java)
* [Item 2](https://www.youtube.com/watch?v=2GMp8VuxZnw): _Consider a builder when faced with many constructor parameters_ -
  [examples](src/main/java/org/codefx/demo/effective_java/_02_builder_pattern/Main.java)
* [Items 3-5](https://www.youtube.com/watch?v=kVuOveApdCk) -
  [examples](src/main/java/org/codefx/demo/effective_java/_03_04_05_singleton_utilities_di/Main.java):
	* _Enforce the singleton property with a private constructor or an enum type_
	* _Enforce noninstantiability with a private constructor_
	* _Prefer dependency injection to hardwiring resources_
