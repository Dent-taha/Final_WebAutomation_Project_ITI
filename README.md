# Web Automation Testing Framework

## Overview

This project is a scalable and maintainable UI automation testing framework built using Selenium WebDriver, TestNG, and Java. The framework follows the Page Object Model (POM) design pattern to improve code reusability, readability, and maintainability.

## Features

* Selenium WebDriver for browser automation.
* TestNG for test execution and test management.
* Page Object Model (POM) design pattern.
* Allure Reporting integration.
* Cross-browser support.
* Screenshot capturing for failed tests.
* Maven dependency management.
* GitHub Actions CI/CD integration.
* Headless execution support.

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Allure Report
* GitHub Actions

## Project Structure

src
├── main
│   ├── java
│   └── resources
├── test
│   ├── java
│   └── resources
├── test-output
└── pom.xml

## Running Tests

Run all tests:

mvn clean test

Run specific TestNG suite:

mvn test -DsuiteXmlFile=testng.xml

## Generating Allure Report

Generate report:

allure generate test-output/allure-results --clean

Open report:

allure open

## CI/CD

The framework is integrated with GitHub Actions to automatically execute test suites and generate reports on every push or pull request.

## Reporting

The framework generates detailed Allure Reports including:

* Test execution status
* Test steps
* Screenshots
* Failure details
* Execution history

## Author

Taha Ashraf
Automation Test Engineer
