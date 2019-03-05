# Chaos Monkey for Spring MVC

Chaos Monkey for Spring MVC simplifies [Chao Monkey Spring Boot](https://github.com/codecentric/chaos-monkey-spring-boot) which is inspired by [Netflix Chaos Monkey](https://github.com/Netflix/chaosmonkey). This project puts a Chaos Monkey into Spring MVC & Spring Boot applications and will try to attack your running Spring App.

## Getting Started

Import chaos-monkey-spring-mvc.jar into your app's dependency and make sure that your Spring App running profile is **chaos-monkey**. `spring.profiles.active=chaos-monkey`

What's more, make sure that your Spring App could scan chaos-monkey-spring-mvc and add them into your **Spring Bean Container Management** via `@ComponentScan` annotation or `<context:component-scan>` tag.

You could configure your chaos monkey in **application.properties** file as follow:

```
#enable a chaos monkey
chaos.monkey.enabled=true
#enable latenc attack
chaos.monkey.assaults.latencyActive=true
#min latency milliseconds
chaos.monkey.assaults.latencyRangeStart=1000
#max latency milliseconds
chaos.monkey.assaults.latencyRangeEnd=4500
#enable exception attack
chaos.monkey.assaults.exceptionsActive=false
#enable termination attack
chaos.monkey.assaults.killApplicationActive=false
#enable attack @Controller annotated code block
chaos.monkey.watcher.controller=true
#enable attack @RestController annotated code block
chaos.monkey.watcher.restController=false
#enable attack @Service annotated code block
chaos.monkey.watcher.service=false
#enable attack @Repository annotated code block
chaos.monkey.watcher.repository=false
#enable attack @Component annotated code block
chaos.monkey.watcher.component=false
#the smaller the number is, the more often the attack hits
chaos.monkey.assaults.level=1
```

### Prerequisites

Before you use chaos monkey, make sure you have already existed those dependency.

```
<dependency>
	<groupId>org.aspectj</groupId>
	<artifactId>aspectjweaver</artifactId>
	<version>xxx</version>
</dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>xxx</version>
      <scope>provided</scope>
</dependency>
```

## Running

Then you can launch your Spring App as usual.

If you can find information in the console like these, that's to say your chaos monkey has been configured to enable.

```
     _____ _                       __  __             _
    / ____| |                     |  \/  |           | |
   | |    | |__   __ _  ___  ___  | \  / | ___  _ __ | | _____ _   _
   | |    | '_ \ / _` |/ _ \/ __| | |\/| |/ _ \| '_ \| |/ / _ | | | |
   | |____| | | | (_| | (_) \__ \ | |  | | (_) | | | |   |  __| |_| |
    \_____|_| |_|\__,_|\___/|___/ |_|  |_|\___/|_| |_|_|\_\___|\__, |
                                                                __/ |
    _ready to do evil!                                         |___/

:: Chaos Monkey for Spring Framework                               ::
```

## Inspiration

- [Chao Monkey Spring Boot](https://github.com/codecentric/chaos-monkey-spring-boot)
- [Netflix Chaos Monkey](https://github.com/Netflix/chaosmonkey)
- [PRINCIPLES OF CHAOS ENGINEERING](http://principlesofchaos.org/)