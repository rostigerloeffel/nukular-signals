# nukular-signals

## Installation
Simply navigate to the nukular-signals root folder and type: 
```
mvn clean install
```

Afterwards add following dependencies to your dependency section:
```
<dependency>
	<groupId>com.nukulargames</groupId>
	<artifactId>signals-annotations</artifactId>
	<version>0.9.0</version>
</dependency>
<dependency>
	<groupId>com.nukulargames</groupId>
	<artifactId>signals-processing</artifactId>
	<version>0.9.0</version>
</dependency>
```

Please note, nukular-signals makes use of java annotation processors to generate code. Hence, possibly you have to inform your IDE about these processors to work correctly. After a succesful build you can find them at:
```
nukular-signals\signals-processing\target\signals-processing-0.9.0-jar-with-dependencies.jar
```

## Usage
To prepare a class for signal processing annotate it with `@Signaller`. Alternatively any member variable of this class' type can be annotated in the same way.
```
@Signaller
public class SampleSignaller {
  public void method() {
    ...
  }
}
```
```
public class NotASignaller {
  @Signaller private SampleSignaller signaller;
}
```

Be careful: It is not allowed to annotate classes carrying the `final` modifier.

Afterwards use `Signallers.wrap(...)` to prepare objects of signaller classes for signalling.
```
import static com.nukulargames.signals.Signallers.wrap;

...

SampleSignaller obj = new SampleSignaller();
...
// Prepare signalling object
SampleSignaller ready = wrap(obj);
```

Now you can prepend and append arbitrary delegates to any method of your signaller. Make use of `Prepender.prepend(...)` and `Appender.append(...)` to do so.
```
import static com.nukulargames.signals.Prepender.prepend;
import static com.nukulargames.signals.Appender.append;

...
// Connect arbitray Java 8 lambdas, method references etc.
prepend(anotherObject::doSomePreparations()).to(ready).method();
append(() -> { System.out.println("Called!"); }).to(ready).method();
...
// Execute
ready.method();
```

## Limitations
Java 8 is needed to use lambdas and method references. Nevertheless it is possible to use nukular-signals without Java 8 by implementing `Delegate[0-9]` and connecting it to signallers as described above.

Currently there is no support for final classes, hence it is not possible to use JDK classes like `String` or `System` as signallers.

Unfortunately it is not possible to annotate local variables yet.
