Traveling Salesman Project
*** Steven Comer
*** sfc15@pitt.edu
*** 7 April 2013

INTRODUCTION

This project gives an approximate solution for the Euclidian Traveling Salesman problem. It
finds a minimum spanning tree (MST) using the Simple Prim algorithm as described by Dr.
Aronis. It then finds a tour of this MST and displays it. The user gives the number of cities
as a command line argument.
 
INSTALLATION

The following files should be present:
  
  TestTSP.java
  TSP.java
  City.java
  Edge.java
  Roadmap.java

Compile with:

  javac TestTSP.java

USAGE

The program should compile and run with Java 2 SDK 1.5 or later:

  java TestTSP [integer number of cities]

KNOWN PROBLEMS

None.