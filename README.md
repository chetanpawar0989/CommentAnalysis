# Comment Analysis - Find Comment Ratio in java file
------------------------------------------
This java program is to identify the number of comment lines to number of code line in java file. It outputs number of code lines, number of comment lines, total lines (excluding empty lines) and comment ratio to total lines.

### Assumption:
- java file on which this analysis is run is compiled without any error.
- only following types of comments are considered
	- // single line comments
	- /* */ multiline comments
- if a line combines both comments and code line then
	- if that line starts as a valid code line, it will be considered as code line
	- otherwise it will be considered as comment line
- empty line is considered neither code line nor comment line (even though it is part of multiline comment)


### Running the program
`javac CommentAnalysis *filename.java*`

### Test cases
I tried to cover different types of comments combinations as mentioned in [Comment scenarios.txt](./comment%20scenarios.txt) file. 

Please comment with any scenario I can add for which it is not working. I will try to cover them as soon as possible. Later on, I am planning to write jUnit test cases with mocha for different scenarios.

### Improvements
- Adding new types of comment scenarios
- *Can't think of anything now. I will add later* 

