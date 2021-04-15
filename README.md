# Unified Modeling Language (UML) Editor
A UML class diagram can be used to express the structure of a system by showing the relationship among objects. And a UML editor is a tool to make drawing a UML class diagram efficiently.

## Table of Contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Class Diagram](#class-diagram)

## Introduction
![Demo Example](image/demoExample.png)
A UML Editor is in the window which is composed of a **Menu bar** on the top, a list of **Buttons** on the left, and a **Canvas** on the rest of the window.

* **Buttons**: six buttons from top to bottom is select, association line, generalization line, composition line, class object, and use case object
* **Menu bar**: other features in the Edit menu included group objects, ungroup object and change the object’s name
* **Canvas**: a place for drawing objects and lines
* Other definition 
    * basic object: class object or use case object
    * object: composite object or basic object
    * composite object: grouped objects
    * line: association line, generalization line, or composition line

## Technologies 
* OpenJDK 15.0.2

## Setup 
```
# change directory to src 
$ cd src

# generate and store the compiled class files in directory output
$ javac gui/Main.java -d output

# change directory to output
$ cd output

# excecute
$ java gui/Main
```

## Features
* **Create basic object mode**
    * Create a UML basic object
        * click on a _Basic object_ button
        * click on the canvas to create a basic object
* **Create line mode**
    * Create a UML connection line
        * click on a _Line_ button
        * press one basic object
        * drag continuously to another basic object and release
        * create a connection line from the first basic object to the second one
* **Select mode**
    * Select a single object
        * click on _Select_ button
        * click on a single object
    * Select a group of objects
        * click on _Select_ button
        * press a point that contains no objects
        * drag continuously to another point and release
        * the selected area where contains specific objects
    * Unselect a single object or a group of objects
        * click on _Select_ button
        * click on a place where contains no objects
    * Group objects into a composite object
        * click on _Select_ button
        * select a group of objects
        * click on _Group objects_ in the Edit menu
    * Ungroup a composite object back to objects
        * click on _Select_ button
        * select a composite object
        * click on _Ungroup objects_ in the Edit menu
    * Move object
        * click on _Select_ button
        * press one object or a group of objects
        * drag continuously to another point and release on where the object you want object/objects to be
        * automatically remove all connection lines which connected with the object
    * Change object name
        * click on _Select_ button
        * select a single basic object
        * click on the _Change object name_ in the Edit menu
        * enter a new name for the basic object

## Class Diagram
![Class Diagram](image/classDiagram.png)