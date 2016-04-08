This library helps you create service provider declaration file under the `META-INF/services` directory. By adding a `@Servcie` annotation onto your service implementation class, you will have the service provider declaration file generated at compile time.

The below code sample declares a `GreetingService` implementation.

```java
import org.taosha.nit.Service;

@Service(GreetingService.class)
public class EnglishGreetingService implement GreetingService {

    @Override public void greet() {
        System.out.println("Oh hello!");
    }

}
```

If you are using gradle, add these two dependencies to your project:

```gradle
repositories {
    maven { url 'http://maven.taosha.org' }
}

dependencies {
    compile 'org.taosha:nit:1.0'
    provided 'org.taosha:nit-processor:1.0'
}
```