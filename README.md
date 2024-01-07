CSC2001F | Assignment 1 - Binary Search Trees

About The Project

This is an introductory Data Structures and Algorithms assignment to test analyse and demonstrate the efficiency of a traditional array against a binary search tree specifically the number of operations it takes to insert and lookup data in these respective data structures.

Built With

- Java 11 - Implementation of the Data Structures and Algorithms
- Python 3.10 - Automating processes and analysing the data
- Pandas
- Seaborn
- Matplotlib
- Numpy

Getting Started

Make sure to always run commands from the project directory

Prerequisites

- A unix-based operating system
- Java 11+
- Python 3.8+

Installation

To run the Python scripts it's recommended to use a virtual environment to be able to use the additional packages as to not install them in your global environment.

Create the virtual environment activate the virtual environment (from the project root) install the required packages

- make install-venv

For manual installation (tested on Arch Linux for ubuntu see the end of the README)

- python3 -m venv scripts/venv
- source scripts/venv/bin/activate
- pip install -r scripts/requirements.txt (or pip3)

Usage

General

Compiling all your classes

- make

Removing all classes

- make clean

Clean project results

- make clean-data

Generate documentation for Java source code

- make doccer

Clean documentation

- clean docs

Part 1

Running the VaccineArrayApp and VaccineBSTApp for Part 1 of the assignment

For providing manual input

- make run-array IN=input/vaccinations.csv
- make run-bst IN=input/vaccinations.csv

For redirecting input data

- make run-array IN=input/vaccinations.csv < input/test_set_1.txt
- make run-bst IN=input/vaccinations.csv < input/test_set_3.txt

To automate the process this runs all three tests in the input/ directory and exports the command line output to output/part_1/

- make run-part1

Part 2

To automatically run the experiment

- make run-part2

Creates 10 random subsets of n = (991 1982… 9910) and exports it to input/ Runs the VaccineArrayApp and VaccineBSTApp for all 10 subsets Exports results to output/part_2/operations Analyses and summarises data to find the minimum maximum and average for each of the insertion operations and the lookup operations and exports these results to output/part_2/ Uses the data from summaries to create tables and line plots and exports them to output/part_2/

Acknowledgements

Hussein Suleman (My Computer Science professor for Data Structures and Algorithms)

Ubuntu python venv installation

For installation of pip

- sudo apt-get install -y python3-pip

Additional packages to make your environment more consistent

- sudo apt-get install build-essential libssl-dev libffi-dev python-dev
