# Team2-P1: Banking Application

## What is this project?
This project is a simple mock banking application. It is a combination of a database (PostgreSQL), backend (built with Spring Boot) and Frontend (made using React JS). The idea of the end goal for this project is as follows:

* New users can register for and log in to user accounts.
* Users with accounts can create bank accounts, which hold some amount of money
* Bank accounts can have transactions associated with them (we called these transfers), which is how money is deposited into and withdrawn from them.

In addition to this simple functionality, there are also more operations made available to the end user. These include the ability to view transfers associated with a bank account based on certain criteria (such as only viewing deposits, or transfers made in a specific month), and the ability to update their information (changing their password or additional information like the email address associated with their user account).

## Authors
* Jonathan Demaree
* Connor Garcia

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is a simple web banking application that has the following functionality:
- Registering and Logging In
- Updating Profile
- Creating Bank Accounts (Checking or Savings)
- Processing Funds and Transactions (Deposits, Withdrawals, Transfers to other Accounts)
- Viewing Account Transaction History

Additionally there are custom web tokens in place that are created for the current user session, updated upon login and checked upon every request
to ensure user is authenticated and requests are valid.

Note: The front-end was completely overhauled this week due to unforeseen complications.
	
## Technologies
Project is created with:
* Backend: 
- Java version: 1.8
- Spring Boot version: 2.7.9
- Maven Version 4.0.0
- PostgreSQL Version 15.1
* Frontend:
- React Version 18.2.0
- TypeScript Version 4.9.3
- Vite Version 4.1.0
	
## Setup
Before running our application, you will need to have these installed: Maven, Node.JS, Java 8

To run the front-end of this project, install it locally using npm:

```
$ cd ./P1-Frontend
$ npm i
$ npm start
```

Before continuing, save the following system environment variables and restart your IDE after:

spring.url = "your-datasource-url"

spring.username = "your-database-username"

spring.password = "your-database-password"

Next, proceed to run the back-end of this project:

```
$ cd ./P1-Backend
$ mvn clean install
$ java -jar .\target\P1-Backend-0.0.1-SNAPSHOT.jar
```
