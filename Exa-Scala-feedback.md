# Project: Scala_IMDB_Project

## CAVEATS
So I don't repeat the same thing over and over with each point below, let me say up front that I know you write this as a way to actually use what you have learned reading up on Scala, so you took shortcuts you wouldn't otherwise. I am going to point out these shortcuts that I do not like as for me, I would have to consciously force myself to take those shortcuts and likely would never do so as I treat all my code as something I would want to put in production and that way I don't have to think about not taking shortcuts, etc.  

## General
1. `gitignore` - Should always put in a gitignore file to avoid committing build and ide files that are generated and change often. 
2. `Coding conventions` - You should always follow a defined style guide to make your code more readable so anyone else looking at it just reads the code instead of first trying to decipher how you wrote it and then seeing the logic you implemented. I just ran the default Format Code within intelliJ to make it a little easier to read but more can be done. 
3. `Code cleanliness`
    * if you have the Scala plugin installed in your IntelliJ IDE, much of what I will say below would have been easily visible
    * Always keep your code as clean as possible and getting rid of compiler warnings is one of the easiest things to do toward that end. 
4. `var vs val` - 
    1. In general, you should be using `val` as most of the time you are storing an immutable value that won't be changed which allows the compiler to do some optimizations and also puts in a check if you do try to update the value. 
    2. You only need to use `var` if you will be updating the value of the variable after initializing it.
5. `semi-colons` - This is a habit from I have too since I learned Java first but in Scala you only need to use semicolons if you have multiple statements on the same line. 

## Design
1. `Scala is a JVM language` - You can use any Java library in your code. For example, there are a million CSV Parsers out there that would have simplified your code and saved you time. 
2. `Magic Strings` - 
    * Extract them into a constant so at least you have only one place to update. 
    * 

## Unit Tests
1. `Use a template` - 
    * I always follow a template for how I structure each of my tests. Common templates are `Given-When-Then` or `Arrange-Act-Assert` this makes your tests easier to understand. 
    * ScalaTest actually has a support for FeatureSpec and GivenWhenThen, so you can add split up your tests and make them more explicit. https://www.scalatest.org/getting_started_with_feature_spec
2. `Tests should be in same package a class being tested` - Avoids some unnecessary imports.
3. `Each Test should be independent` - 
    * Each of your unit tests should be independent of the others and should not fail if run multiple times. Use the Before and After to do the common setup and clean up after each test. 
    * In SuperMoviesTest, the tests are fragile. the second test generates the file that is checked for in the last test so running all the tests or not doing a clean ends up with the last test failing. 
4. `Unit Tests should test a scenario` - 
    * Each unit test should test one scenario of inputs and expected outputs. 
    * This should include the happy path but also cover error conditions, edge cases, etc. 
    * In the HandleMoviesDataTest, few comments:
        * first test doesn't really test much other than the result is not empty. 

