##### Problem Name : Merchant Guide To Galaxy
##### Implemented in : Java8 (Functional Programming)
##### Built using : Maven 3.3.9
##### Unit Testing : Junit 4.12
##### Developer : Praneeth Ramesh
##### Email : sr.praneeth@gmail.com
--

## Problem Statement
You decided to give up on earth after the latest financial collapse left 99.99% of the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in your account, you are able to afford to rent a spaceship, leave earth, and fly all over the galaxy to sell common metals and dirt (which apparently is worth a lot).Buying and selling over the galaxy requires you to convert numbers and units, and you decided to write a program to help you.The numbers used for intergalactic transactions follows similar convention to the roman numerals and you have painstakingly collected the appropriate translation between them.Roman numerals are based on seven symbols:

#### SymbolValue
I 			1
V 			5
X 			10
L 			50
C 			100
D 			500
M 			1,000

Numbers are formed by combining symbols together and adding the values. For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value, starting with the largest values. When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total. For example MCMXLIV = 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944. The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be repeated.

"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted. Only one small-value symbol may be subtracted from any large-value symbol.

A number written in Arabic numerals can be broken into digits. For example, 1903 is composed of 1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should be treated separately. In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII. -- Source: Wikipedia (http://en.wikipedia.org/wiki/Roman_numerals)Input to your program consists of lines of text detailing your notes on the conversion between intergalactic units and roman numerals. You are expected to handle invalid queries appropriately.

#### Test input:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits

glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

#### Test Output:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about
--
--

## Assumptions
1. Input is provided through a file
2. Output to be printed on console
3. RomanNumerals Expressions are provided in the format "glob is I" where glob is GalaticSymbol and I is roman numeral
4. Metal Credits is provided in the format "glob glob Silver is 34 Credits" where 34 is the credits and Silver is the metal
5. Any Query input is in the following format "how many Credits is glob prok Silver ?" starting with a how and ending with a question mark
6. Any other format for the inputs application says "I have no idea what you are talking about"
7. In order to make code more readable, few more temp variables are being used. 

FileInputReader and ConsoleOutputWriter has been mocked in the business level class test cases. FileInputReader and ConsoleOutputWriter also have been unit tested.

--

Prerequisite
============
1. [Java](http://www.oracle.com/technetwork/java/javase/overview/index.html) = 1.8
2. [Maven](https://maven.apache.org) >= 3.3.9

Run Tests
==========
Navigate to Project root(where pom.xml exists) folder and run command 
`mvn test`

Build
=====
Navigate to Project root(where pom.xml exists) folder and run command 
`mvn install`

Run
===
`java -jar merchant-guide.jar [inputFile]`
[inputFile] - fully qualified path of the input file

Docker
======
To [Install Docker](https://docs.docker.com/engine/installation)
1. Build the Docker Image - `sudo docker build -f Dockerfile -t merchantguide .`
2. Run the built image - `sudo docker run -i -t merchantguide`	- This opens a ubuntu virtual machine with preinstalled oracle-java8
3. Run `java -jar /home/merchant-guide.jar /home/Input.txt` inside it, to run the application.
You can edit the Input.txt file and rerun the application for different input.


