DEMO VIDEO - https://drive.google.com/file/d/1niHrSTDnWX8Z5xVKoj8jTQ8q7G8kllcb/view?usp=sharing


- Setup instructions
	For the set up:
	- install IntelliJ community edition
	- download java jdk11
	- download latest maven
	- add java 11 in System Variables 
	- set Java 11 as the JAVA_HOME
	- add path  %JAVA_HOME%\bin
	- open IntelliJ
	- Create project and select maven as build system and jdk -11
	- then set the project structure, add the jdk 11 in SDK
	- set the  home path of build tool from settings to Bundled (Maven3)
	- configure the pom.xml
	- set properties for maven compiler to 11
	- set build to maven-compiler-plugin
	- in terminal, run mvn clean compile to check if there's no error in compilation 
	- run mvn exec:java -D"exec.mainClass"="com.banking.app.Main" to run the system
		- add "" to exec.mainClass to run if using Windows

- Features implemented
	- Account
		- create account
	- Transactions
		- Check balance
		- deposit
		- withdraw
			- checking account
				- transaction limit 
				- withdrawal limit
			- savings account
				- minimum balance
				
		- transfer
		- process monthly fee
			- savings account
				- earn 3% interest
			- checking account
				- pays 12
		- transaction history
	- Exception Handling
		- create custom exception
			- AccountNotFoundException
			- InsufficientFundsException
			- InvalidDepositException
		- built in exception
			- NumberFormatException
			- NullPointerException
			- IOException
	- Logs
		- create log file for each account transaction
	
			
- Design decisions
	- model - domain class
	- service - business logic
	- exception - custom exceptions
	- util - helper

	- Encapsulation
		- provides data security 
	- Abstraction 	
		- hides implementations
	- Interface
		- use for contract to services
	- Inheritance
		- reuse method

- Known issues/limitations
	- No GUI
	- No database 
	- No authentication and authorization
