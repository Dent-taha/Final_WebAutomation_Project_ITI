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

├── src/
    ├── main/
    │   ├── resources/
    │   │   ├── allure.properties
    │   │   ├── browser.properties
    │   │   ├── environment.properties
    │   │   ├── META-INF/
    │   │   │   └── services/
    │   │   │       └── org.testng.ITestNGListener
    │   │   ├── test-data/
    │   │   │   ├── register.json
    │   │   │   ├── login.json
    │   │   │   ├── account.json
    │   │   │   ├── home_data.json
    │   │   │   ├── apparel.json
    │   │   │   ├── cart.json
    │   │   │   └── e2e.json
    │   │   └── log4j2.properties
    │   └── java/
    │       └── com/
    │           └── project/
    │               ├── drivers/
    │               │   ├── WebDriverProvider.java
    │               │   ├── AbstractDriver.java
    │               │   ├── UI.java
    │               │   ├── Browsers.java
    │               │   ├── EdgeFactory.java
    │               │   ├── GUI.java
    │               │   └── ChromeFactory.java
    │               ├── Pages/
    │               │   ├── ComputersPage.java
    │               │   ├── GiftCardsPage.java
    │               │   ├── ElectronicsPage.java
    │               │   ├── JewelryPage.java
    │               │   ├── BooksPage.java
    │               │   ├── DigitalDownloadsPage.java
    │               │   ├── WishListPage.java
    │               │   ├── RegisterPage.java
    │               │   ├── CheckoutPage.java
    │               │   ├── LoginPage.java
    │               │   ├── ShoppingCart.java
    │               │   ├── HomePage.java
    │               │   ├── MyAccountPage.java
    │               │   └── ApparelShoesPage.java
    │               ├── utils/
    │               │   ├── TimeManager.java
    │               │   ├── OSUtils.java
    │               │   ├── reports/
    │               │   │   ├── AllureAttachmentManager.java
    │               │   │   ├── AllureEnv.java
    │               │   │   ├── AllurerReportGenerator.java
    │               │   │   └── AllureBinaryManager.java
    │               │   ├── logs/
    │               │   │   └── logsManager.java
    │               │   ├── Actions/
    │               │   │   ├── BrowserActions.java
    │               │   │   ├── FrameActions.java
    │               │   │   ├── DropDownActions.java
    │               │   │   ├── AlertActions.java
    │               │   │   └── ElementsActions.java
    │               │   ├── Validation/
    │               │   │   ├── HardAssert.java
    │               │   │   ├── BaseAssertions.java
    │               │   │   └── SoftAssert.java
    │               │   ├── TerminalUtils.java
    │               │   ├── dataReader/
    │               │   │   ├── JsonReader.java
    │               │   │   └── PropertyReader.java
    │               │   ├── WaitManagement.java
    │               │   ├── ConstantPaths.java
    │               │   ├── Media/
    │               │   │   └── ScreenShot.java
    │               │   └── FileUtils.java
    │               ├── API/
    │               │   ├── Builder.java
    │               │   └── USerManagementAPI.java
    │               └── CutomListeners/
    │                   └── TestNGListener.java
    └── test/
        ├── Base/
        │   └── BaseTest.java
        └── UITests/
            ├── HomeSmokeTest.java
            ├── RegistrationTest.java
            ├── LoginTest.java
            ├── ChangingAccountInfoE2ETest.java
            ├── E2ETest.java
            ├── ApparelAndShoesTest.java
            └── CartFunctionalityTest.java

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
