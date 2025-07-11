
# SourceDemo Automation Test Suite

## 1\. How to Execute the Test Suite

**Prerequisites:**
- Java 11 or above
- Maven 3.6+
- Google Chrome installed
- selenium
- cucumber

## 2\. Browser Setup Instructions

- Install [Google Chrome](https://www.google.com/chrome/).
- Download [ChromeDriver](https://chromedriver.chromium.org/downloads) matching your Chrome version.
- Add ChromeDriver to your system PATH:
  ```bash
  export PATH=$PATH:/path/to/chromedriver
  ```
- Verify installation:
  ```bash
  chromedriver --version
  ```

## 3\. Test Design Document

- **Framework:** Cucumber + TestNG + Selenium WebDriver
- **Structure:**
  - `src/test/resources/features/` — Gherkin feature files
  - `src/test/java/com/sourcedemo/automation/StepDefinitions/` — Step implementations
  - `src/test/java/com/sourcedemo/automation/Hooks/` — WebDriver setup/teardown
  - `src/test/java/com/sourcedemo/automation/LoginFeatureTest.java` — Test runner
- **Test Flow:**
  1. WebDriver is initialized before each scenario via hooks
  2. Step definitions interact with the browser for login actions
  3. Assertions validate expected outcomes
  4. WebDriver quits after each scenario
