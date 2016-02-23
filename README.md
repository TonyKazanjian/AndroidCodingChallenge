# AndroidCodingChallenge
For Fishermen Labs application

Questions:

1. What is the major difference between an abstract class and an interface?

An abstract class acts as a template which other classes can extend from. It is never instantiated on its own, but its properties are inherited by its child class. An interface is used to add specific functionality to any class. When it's implemented into a class, all it's methods must be as well.The implementing class can then define what happens in the interface's methods. A concrete child classcan only extend one abstract class, but can implement as many methods as it needs.

2. Why is Java 7’s class inheritance flawed?

Changes within a superclass, e.g. in subsequent releases, can potentially break a subclass. For example, If a subclass overrides a superclass method but invokes the superclass's method within the overridden one (using super.methodCall(...)), the results of that method may not be as expected.

3. What are the major differences between Activities and Fragments?
4. When using Fragments, how do you communicate back to their hosting
Activity?
5. Can you make an entire app without ever using Fragments? Why or why
not?
6. What makes an AsyncTask such an annoyance to Android developers?
Detail some of the issues with AsyncTask, and how to potentially solve
them.
