# hexagonal-gradle-demo
Example architecture with Gradle and Java

# Introduction
This repository is an sample of an implementation of [Hexagonal Architecture](http://alistair.cockburn.us/Hexagonal+architecture) in Java using Gradle as a build system.  It is different from [layered architecture](https://en.wikipedia.org/wiki/Multitier_architecture) as the domain is central and other modules are only there to allow the domain to communicate with the outside world.

# Running the application 
```
gradle :app run
```

# Modules
It is comprised of several modules

- **domain** : domain logic.  Here a very simple blogging platform.
- **persistence** : simple in-memory persistence used by the bloggin platform.
- **facebook-connector** and **twitter-connector** : connectors to share a blog post on Facebook and Twitter
- **webserver** : HTTP web server for hosting the blogging platform
- **service-locator** : utility module for searching and exposing Java Service Providers

# Ports and Adapters
Each module has a clear responsibility.  Domain is for domain logic.  Therefore, there should not be any framework-specific or any code specific to a database for example.  This code belongs in modules implementing domain ports.  Domain ports are represented by Java interfaces.

Implementations of these interfaces are defined by [Service Provider](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html) implementations.  Service-locator is a special module as it is an [adapter](https://en.wikipedia.org/wiki/Adapter_pattern) around [Service Provider](https://docs.oracle.com/javase/tutorial/ext/basics/spi.html) to remove boilerplate and give it a more functional style.

# Domain module

The key to this architecture is that the domain module should not have any dependency on modules with implementations.  It should only have dependencies on utilities (like the service-locator, logging systems, data manipulation or such).

# Domain logic

Main domain logic is implemented in class `org.detoeuf.hexagonal.domain.Blog`.  It allows three actions.

- Write a post
- Read a post
- Share a post

For these actions, `org.detoeuf.hexagonal.domain.Persistence` and  `org.detoeuf.hexagonal.domain.SocialMedia` are used to access persistence and sharing connectors.  The key point is that the domain has no idea which persistence is used.  It could be a relational database, a file or as in this example an in-memory HashMap.  Same goes for connectors.  Here sharing consists of printing to `System.out`, but it could be calling Facebook and Twitter with diverse APIs like REST, or with an external service like [Buffer](http://buffer.com/).

Application domain logic is implemented in class `org.detoeuf.hexagonal.domain.BlogApp`.  Domain logic assumes that a web server has to be stated upon startup.  This web server has to implement `void start()`.  Notice that the domain is calling the web server and not the other way around.  Even though HTTP requests flow from the web server to the domain and back, control starts from the domain.  This is an important part of understanding hexagonal architecture as it is what makes it fundamentally different from a [layered architecture](https://en.wikipedia.org/wiki/Multitier_architecture).

# Adapters

Adapters are connections to the outside world.  It is therefore the web server's responsibility to define which URLs should call domain methods.  Same goes for persistence, the domain defines what should be saved or retrieved and in which manner.  Persistence module should bridge the gap between how it is called by the domain and database calls (in the case of a database).
