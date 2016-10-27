## The Nature of Code (with Clojure!)

This repository serves as a well documented collection of simulations I wrote while reading **The Nature of Code** by *Daniel Shiffman*.

Please note that I have deviated from the technology choices made in the book. I have elected to code all my examples with Clojure's version of Processing ([Quil](http://quil.info/)), as opposed to the Java implementation used in the book.

Why, you ask? Quite simply put, I don't like object oriented programming for these kinds of programs. I find functional programming to be very powerful and expressive, not to mention it allows for changing the simulations at run time via a REPL (which greatly speeds up development).

At the end of the day, it is just personal preference and fun that drives this choice, as well as learning new things! Despite the differences in language choice and programming style, the content of my examples maps quite closely to the subject matter of the book.

## Usage
So, if you've never seen any Clojure or LISP before, this might not be the best place to start. I would recommend reading **[Clojure for the Brave and True](http://www.braveclojure.com/)** which is freely available on the internet.

If you are comfortable enough with the basics of Clojure and want to run the examples here's the high level overview of what you need to do. Please continue reading if you need further guidance on any of these steps.

1. Have a JDK and JRE on your machine since Clojure is hosted on the JVM
2. Install [Leiningen](http://leiningen.org/)
3. Clone the repository
4. Enter the repository in your terminal and build it with Leiningen
5. Open the REPL
6. Run the desired simulation

### Installing Java
Just head over to Oracle's site and pick up whatever you need for your platform.

### Installing Leiningen
You can think of Leiningen as the all in one build and dependency manager for Clojure projects.

To install it head [here](http://leiningen.org/) and follow the instructions for your platform. When you are done, you should be able to open up your terminal and type
```bash
lein --help
```

### Cloning and Building
Once you have stack up and running, its time to the build the project
```bash
git clone https://github.com/nickmyers217/nature-of-code-clojure
cd nature-of-code-clojure
lein deps # This downloads the project dependencies
```

### Running the simulations
Once you have the project built you can run the various simulations quite easily from the REPL.

```bash
lein repl
```
Once the REPL opens and greets you with a prompt, you can run a simulation of your choice as shown below. All the available simulations can be found in [./src/nature_of_code_clojure/](./src/nature_of_code_clojure/)
```
(use 'nature-of-code.random-walker)
(run-simulation)
```

## License

Copyright © 2016 Nicholas Myers

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
