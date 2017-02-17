# processing-utils
A collection of utilities to aid developers using Processing. Built on Kotlin for even more usefulness.

[![Build Status](https://travis-ci.org/GiantTreeLP/processing-utils.svg?branch=master)](https://travis-ci.org/GiantTreeLP/processing-utils)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.gianttreelp/gtlp-processing-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.gianttreelp/gtlp-processing-utils)
[![Dependencies](https://app.updateimpact.com/badge/831936721338896384/processing-utils.svg?config=test)](https://app.updateimpact.com/latest/831936721338896384/processing-utils)
[![Dependency Status](https://www.versioneye.com/user/projects/58a71900a782d1002e644046/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58a71900a782d1002e644046)

Example:  
-----
Vector - A nice vector that allows the use of infix operators to behave naturally and less like Java's BigDecimal.

    // Naturally add Vectors together
    Vector(1, 2) + Vector(3, 4) = Vector(4, 6)
    
    // Multiply by a scalar
    Vector(1, 1, 1) * 10 = Vector(10, 10, 10)
    
Extensions.kt - Extensions for various Processing methods

    // Map a number from a range to another range without casting to Float
    var ten = map(0.1, 0, 1, 0, 100)
    ten = 10f // 10 as a Float

FloatRange - A range from some decimal number to another

    // Go from 0 to 10 with a step of 0.25 and print the number in the process
    (0f..10f step 0.25f).forEach {
        println(it)
    }
