# highj17

This is a copy of highJ adapted to use Java 17 and to take advantage of the new possibilities. 

As I run in problems with derive4j, I dropped the dependency and use no more annotation-processor in the project. When these problems are solved, it might be easier to maintain a Java 17 branch in the original project, but at the moment it isn't clear which way to go.

### Changes
* Take advantage of better type inference (e.g. drop some now unnecessary generic details)
* Use record syntax, if this doesn't compromise encapsulation
* Use switch expressions
* Use instanceof with cast
* Use var when it improves readability
* Use Java API improvements (e.g. for Collections, String)
