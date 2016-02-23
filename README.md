# AndroidCodingChallenge
For Fishermen Labs application

Questions:

1. What is the major difference between an abstract class and an interface?

An abstract class acts as a template which other classes can extend from. It is never instantiated on its own, but its properties are inherited by its child class. An interface is used to add specific functionality to any class. When it's implemented into a class, all it's methods must be as well. The implementing class can then define what happens in the interface's methods. A concrete child class can only extend one abstract class, but can implement as many methods as it needs.

2. Why is Java 7’s class inheritance flawed?

Changes within a superclass, e.g. in subsequent releases, can potentially break a subclass. For example, If a subclass overrides a superclass method but invokes the superclass's method within the overridden one (using super.methodCall(...)), the results of that method may not be as expected.

3. What are the major differences between Activities and Fragments?

An Activity is basically a screen that contains UI elements presented to the user. A Fragment is essentially a UI element that is hosted by an Activity, so there can be multiple Fragments within an Activity itself. 

One of the main differences between Activities and Fragments is how they are added and removed to the backstack. If a user starts a new Activity from the previous one, the previous Activity is stopped but preserved in the backstack. When the user presses the "back" button, their current Activity is destroyed and the previous one is resumed from its previous state. A Fragment needs a FragmentManager to manage these transactions, and a specific call of addToBackstack() in order for the Fragment instance to be saved.

Another very crucial difference is that Activities and Fragments both have separate lifecycles. While the lifecycle of a Fragment is tied to the Activity it's attached to (i.e. onPause() in the Activity will call the Fragment's onPause()), there are extra callbacks in the Fragment's lifecycle that manage how the Fragment is built and destroyed within the Activity.

4. When using Fragments, how do you communicate back to their hosting Activity?

You would define a interface in the Fragment and then create a stub method, which would also be called somewhere within the Fragment's code (i.e. an OK button's onClick method). The host Activity would then implement the Fragment's interface and override it's methods to do whatever is needed with the information that was passed into the Fragment's invocation of the interface methods.

5. Can you make an entire app without ever using Fragments? Why or why not?

Yes you can, as long as you don't want any added UI elements running on their own lifecycle within the same screen. It's easy to pass information amongst multiple Activities, so if your app was made up of separate screens that each did an individual thing, you wouldn't need any Fragments. 

6. What makes an AsyncTask such an annoyance to Android developers? Detail some of the issues with AsyncTask, and how to potentially solve them.

Developers need to be responsible for managing AsyncTask throughout an Activity or Fragment's lifecycle. AsyncTask can keep on running in the background if its task has not finished, even if the Activity it is in has already finished. This can create memory leaks and waste resources like the CPU or the device's battery. To fix this, we can override a callback to the Activity's lifecycle when it is finishing, performing a check to see if the background task is running at that point in the lifecycle. If it is, we can cancel that task. 

However, calling AsyncTask.cancel() doesn't actually cancel the task. To do that, we need to check if AsyncTask.isCancelled() is true while it is operating, and then cancel the task if so. 

One thing that can happen is that device rotation can repeatedly fire off new AsyncTasks unless setRetainInstance(true) is called on the Fragment in which AsyncTask is executing.

A way to manage both these issues is to use AsyncTaskLoader instead. The Loader keeps data among configuration changes, so it will not try to fetch data again when a lifecycle is affected by rotation.
      
