# Playwright Java TestNG Automation Framework

Simple, Eclipse-importable Playwright framework using:

- Java 21
- Maven
- Playwright Java
- TestNG
- Page Object Model
- Config properties
- Screenshot/trace-ready debugging

## 1. Requirements

Install:

- JDK 21
- Eclipse IDE
- Maven (Eclipse can use its embedded Maven)

Verify Java:

    java -version

Expected:

    21.x

## 2. Import into Eclipse

1. Extract the ZIP.
2. Open Eclipse.
3. File -> Import
4. Maven -> Existing Maven Projects
5. Select the extracted project folder.
6. Finish.
7. Right-click the project -> Maven -> Update Project.

## 3. Install Playwright browsers

From the project folder:

    mvn test

The Playwright Maven plugin installs the required browser binaries during the Maven lifecycle.

You can also use the Playwright CLI if needed:

    mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"

## 4. Run the tests

Command line:

    mvn test

Or in Eclipse:

Right-click testng.xml -> Run As -> TestNG Suite

## 5. Headed / headless

Open:

    src/test/resources/config.properties

For visible browser:

    headless=false

For headless execution:

    headless=true

## 6. Framework flow

LoginTest
    |
    +-- PlaywrightFactory
    |      |
    |      +-- Browser
    |      +-- BrowserContext
    |      +-- Page
    |
    +-- HomePage
    |
    +-- LoginPage
    |
    +-- ConfigReader
    |
    +-- TestNG

## 7. Important Playwright concepts

Unlike Selenium, Playwright automatically waits for elements to become actionable.

Example:

    page.locator("#Email").fill("user@example.com");
    page.locator("#Password").fill("Password123");
    page.locator("input[value='Log in']").click();

Explicit Thread.sleep() is normally unnecessary.

## 8. Trace

The framework starts Playwright tracing.

After execution, check:

    test-output/trace.zip

View it with:

    mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="show-trace test-output/trace.zip"

The trace can show actions, screenshots, DOM snapshots and network information.


