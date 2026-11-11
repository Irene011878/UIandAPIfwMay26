# QA Automation Framework – Testing Documentation

## 1. Project Overview

This project is a Java-based test automation framework developed for **learning, hands-on practice, and demonstration of QA Automation skills**.

The main objective is to apply test automation best practices to functional UI testing and REST API testing using technologies from the Java ecosystem.

The application under test is:

[Automation Exercise](https://automationexercise.com/)

**Disclaimer:** Automation Exercise is a public website used for educational purposes. Its availability, behavior, response times, and stability are outside the control of this project. Therefore, some test results may be affected by website changes or environmental instability.

This project is not intended to certify the overall quality of Automation Exercise or guarantee that all application scenarios are defect-free.

---

## 2. Technologies and Tools

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Maven | Project and dependency management |
| Selenium WebDriver | UI test automation |
| Rest Assured | REST API test automation |
| TestNG | Test execution and organization |
| Allure Reports | Test reporting and execution evidence |
| Apache POI | Excel test data management |
| Git / GitHub | Version control |
| GitHub Actions | Continuous Integration |
| IntelliJ IDEA | Development environment |

---

## 3. Framework Architecture

The framework follows a layered architecture:

**Maps → Pages → Flows → TestCases**

- **Maps:** Centralize UI element locators.
- **Pages:** Contain interactions with application pages.
- **Flows:** Group reusable business processes and functional workflows.
- **TestCases:** Contain test scenarios and their validations.

API tests are organized by functionality and use reusable components to manage HTTP requests and API endpoints.

Assertions are maintained within the TestCases layer.

---

## 4. Test Coverage

### 4.1 UI Test Automation

UI test scenarios are organized into the following functional modules:

| Module | Functional Coverage |
|---|---|
| Account | User registration, login, logout, and account deletion |
| Cart | Adding products, viewing the shopping cart, and validating product quantities |
| Checkout | Checkout process and billing/shipping address validation |
| Contact Us | Contact form submission |
| Navigation | Page and category navigation |
| Products | Product browsing, search, brands, and recommended products |
| Subscription | Subscription registration and validation |

### 4.2 REST API Test Automation

The project contains 14 API test classes:

| ID | Test Scenario | Functional Coverage |
|---|---|---|
| API01 | Get All Products | Retrieve all products |
| API02 | Post All Products | Verify unsupported POST method |
| API03 | Get All Brands | Retrieve all brands |
| API04 | Put All Brands | Verify unsupported PUT method |
| API05 | Search Product | Search for products |
| API06 | Search Product Without Parameter | Validate search without a required parameter |
| API07 | Verify Login | Validate successful login |
| API08 | Verify Login Without Email | Validate login without email |
| API09 | Verify Login Delete | Verify unsupported DELETE method for login |
| API10 | Verify Login Invalid | Validate invalid login credentials |
| API11 | Create Account | Create a user account |
| API12 | Delete Account | Delete a user account |
| API13 | Update Account | Update user account information |
| API14 | Get User Detail By Email | Retrieve user details using an email address |

### 4.3 Test Data Management

An Excel workbook is used as a test data source for data-driven scenarios.

TestNG DataProviders allow test methods to execute with multiple sets of input data.

Unique user accounts are also generated to prevent data collisions between tests that create accounts.

---

## 5. TestNG Suite Organization

| Test Suite | Coverage |
|---|---|
| testng-ui.xml | All UI tests |
| testng-api.xml | All API tests |
| testng-smoke-suite.xml | UI + API Smoke tests |
| testng-regression.xml | UI + API Regression tests |
| testng-full.xml | All UI + API tests |
| testng-ui-parallel.xml | UI parallel execution diagnostic suite |

### API Test Group Classification

- **Smoke:** API01, API05, API07, and API11.
- **Regression:** API01 through API14.
- **Full:** All API test classes included in the Full suite packages.

---

## 6. Allure Screenshot Management

### Automatic Screenshot Capture

The framework implements automatic screenshot capture through a custom Allure `TestLifecycleListener`, named `AllureAttachmentListener`.

The listener is registered through Java's Service Provider mechanism using the following configuration file:

`src/test/resources/META-INF/services/io.qameta.allure.listener.TestLifecycleListener`

### Screenshot Capture Conditions

Screenshots are automatically captured and attached to the corresponding Allure test result when the test finishes with either of the following statuses:

- **FAILED:** The test fails due to an assertion failure.
- **BROKEN:** The test encounters an unexpected exception during execution.

Tests with a **PASSED** status do not generate screenshots.

### Exception Handling

The screenshot listener includes exception handling to prevent exceptions during screenshot capture or attachment from propagating through the listener callback.

Additional validations ensure that:

- The WebDriver instance is available.
- The driver supports screenshot capture.
- The captured image is not empty.

If screenshot capture or attachment fails, an error message is printed to the console.

### Screenshot Validation

The `TestScreenshot` test case is used to intentionally trigger a test failure and verify the automatic screenshot mechanism.

The implementation was validated for both FAILED and BROKEN test statuses. Successful tests were also checked to confirm that no unnecessary screenshot attachments are generated.

### Implementation Notes

- `AllureAttachmentListener` handles automatic screenshot capture.
- `ScreenshotUtils` remains in the project for potential future use; no active usages were identified during the review.
- The listener is registered through `META-INF/services`.
- No additional TestNG listener was required for automatic screenshot capture.
- `TestScreenshot` is located in `testCases.ui.diagnostics` and is executed independently for technical validation of the screenshot mechanism. It is excluded from the regular TestNG suites.
## 7. Regression Suite Execution Results

**Test Suite:** testng-regression.xml

**Execution Configuration:** parallel="tests", thread-count="2"

| Result | Count |
|---|---:|
| Total Tests Run | 43 |
| Passed | 38 |
| Failed | 5 |
| Skipped | 0 |
| Pass Rate | 88.37% |

These results correspond to an execution against the public Automation Exercise website.

The five reported failed or affected test scenarios are documented in the following section.

---

## 8. Parallel Execution Evaluation

### Objective

Evaluate the framework's ability to execute UI tests in parallel using TestNG and Selenium WebDriver.

### Configuration

- TestNG parallel execution: `parallel="tests"`
- Thread count: `2`
- WebDriver management: `ThreadLocal<WebDriver>`
- Browser: Chrome
- Diagnostic suite: `testng-ui-parallel.xml`
- Test cases: TC013 and TC019

### Framework Validation

The following improvements were implemented:

- Used `try/finally` in `BaseTest.tearDown()` to ensure ThreadLocal cleanup even if browser shutdown fails.
- Protected ChromeDriver initialization in `DriverFactory` to attempt browser closure if configuration fails.
- Maintained independent WebDriver instances for parallel test execution.

### Execution Results

Both test cases passed when executed individually.

During repeated parallel executions, intermittent failures were observed:

| Execution | TC013 | TC019 |
|-----------|-------|-------|
| 1 | Passed | Passed |
| 2 | Failed | Passed |
| 3 | Failed | Failed |
| 4 | Failed | Passed |
| 5 | Not completed | Not completed |

The fifth execution could not be completed because the public website displayed a high-load message.

### Analysis and Limitations

The individual execution of both test cases was successful. However, repeated parallel execution produced intermittent failures.

Since Automation Exercise is a public website, server load, response times, rendering behavior, and environmental conditions are outside the control of this framework.

The observed results do not establish that the WebDriver ThreadLocal implementation is defective, nor do they confirm that the website is the sole cause of the failures.

### Conclusion

Parallel execution was successfully configured and technically validated in initial runs. However, consistent execution under concurrent load could not be established due to intermittent failures and public website availability limitations.

No additional synchronization mechanisms or artificial waits were introduced solely to compensate for these environmental conditions.

The current implementation is retained, and parallel execution stability remains a potential area for future evaluation.

# 9. Test Incidents and Defect Reports

## Important Note

The following reports document issues observed during automated test execution.

They are not considered confirmed application defects until they can be consistently reproduced and potential synchronization, rendering, loading, and execution environment issues have been ruled out.

### DEF-UI-001 – Add to Cart Element Not Visible

**Test Case:** TC012 – verifyProductsAreAddedSuccessfullyToCart

**Module:** Cart

**Issue Type:** UI Automation / Element Visibility

**Priority:** Medium (Tentative)

**Preconditions:**
- Automation Exercise website is accessible.
- Products page is available.

**Steps to Reproduce:**
1. Navigate to the Products page.
2. Locate the Blue Top product.
3. Attempt to select the Add to Cart option from the product overlay.

**Expected Result:**

The Add to Cart element should be visible and allow the product to be added to the shopping cart.

**Actual Result:**

Selenium reports that the element is not visible.

**Affected Locator:**

`//div[@class='product-image-wrapper'][.//p[text()='Blue Top']]//div[contains(@class,'product-overlay')]//a[contains(@class,'add-to-cart')]`

**Error Message:**

`Element not visible`

**Investigation Notes:**

Investigate whether the product overlay is displayed only after hovering over the product or whether a rendering or synchronization issue prevents the element from becoming visible.

**Status:** Pending Investigation.

---

### DEF-UI-002 – Product Detail Information Not Displayed

**Test Case:** TC013 – verifyProductQuantityInCart

**Module:** Cart

**Issue Type:** Observed UI Behavior / Assertion Failure

**Priority:** Medium (Tentative)

**Steps to Reproduce:**
1. Navigate to the product detail scenario.
2. Execute the navigation steps defined in the test.
3. Validate the presence of the product information.

**Expected Result:**

Product detail information should be displayed correctly.

**Actual Result:**

The assertion indicates that product information was not displayed.

**Error Message:**

`Product detail information should be displayed.`

Expected: `true`

Actual: `false`

**Investigation Notes:**

Verify whether the product detail page finished loading and whether the expected element was available before the assertion was executed.

**Status:** Pending Investigation.

**Additional Investigation – Parallel Execution:**

TC013 passed when executed individually. During repeated parallel execution with TC019, intermittent failures were observed.

This behavior suggests a possible interaction with concurrent execution or environmental conditions. The root cause has not been confirmed.

No changes were made to the test logic based solely on these observations.

---

### DEF-UI-003 – Dress Category Element Not Clickable

**Test Case:** TC018 – viewCategoryProducts

**Module:** Products / Navigation

**Issue Type:** UI Interaction / Element Clickability

**Priority:** Medium (Tentative)

**Steps to Reproduce:**
1. Navigate to the Categories section.
2. Locate the Dress category.
3. Attempt to select it.

**Expected Result:**

The Dress category should be clickable and display the corresponding products.

**Actual Result:**

Selenium cannot successfully click the element within the configured timeout.

**Affected Locator:**

`//a[contains(text(),'Dress')]`

**Error Message:**

`org.openqa.selenium.TimeoutException: Element was not clickable`

**Investigation Notes:**

Investigate whether an overlay, loading issue, or visibility condition prevents the click interaction.

**Status:** Pending Investigation.

---

### DEF-UI-004 – Add to Cart Element Not Visible During Checkout

**Test Case:** verifyAddressDetailsCheckout

**Module:** Checkout

**Issue Type:** UI Automation / Element Visibility

**Priority:** Medium (Tentative)

**Steps to Reproduce:**
1. Start the checkout scenario.
2. Locate the Blue Top product.
3. Attempt to add the product to the shopping cart.
4. Continue with the checkout flow and address validation.

**Expected Result:**

The product should be successfully added to the cart so the checkout process can continue and address details can be verified.

**Actual Result:**

Selenium reports that the Add to Cart element is not visible.

**Affected Locator:**

`//div[@class='product-image-wrapper'][.//p[text()='Blue Top']]//div[contains(@class,'product-overlay')]//a[contains(@class,'add-to-cart')]`

**Error Message:**

`TimeoutException: Element not visible`

**Investigation Notes:**

The error is similar to the one observed in TC012. Investigate whether both scenarios share the same issue related to interacting with the product overlay.

**Status:** Pending Investigation.

---

### DEF-UI-005 – Browser Renderer Timeout During Recommended Products Test

**Test Case:** TC022 – addRecommendedProductToCart

**Module:** Products / Cart

**Issue Type:** Browser Stability / Rendering Timeout

**Priority:** High (Tentative)

**Steps to Reproduce:**
1. Navigate to the recommended products scenario.
2. Locate a recommended product.
3. Attempt to add it to the shopping cart.

**Expected Result:**

The page should respond to the interaction and allow the recommended product to be added to the cart.

**Actual Result:**

The execution is interrupted by a timeout while waiting for a response from the browser renderer process.

**Error Message:**

`Timed out receiving message from renderer: 59.223`

**Investigation Notes:**

This message alone does not demonstrate a functional defect in Automation Exercise. It may be related to page loading, browser resources, rendering performance, or execution environment stability.

**Status:** Pending Investigation.

---

## 10. Project Conclusions

This framework provides hands-on practice and demonstrates the implementation of:

- Layered test automation architecture.
- UI test automation using Selenium WebDriver.
- REST API test automation using Rest Assured.
- Test organization and execution with TestNG.
- Data-driven testing using Excel and DataProviders.
- Automated reporting with Allure.
- Continuous Integration using GitHub Actions.
- Identification, analysis, and documentation of test incidents.

The reported results represent the behavior observed during test executions against a public educational website.

**This project is intended for learning and demonstration of QA Automation skills.** Reported failures are documented for future investigation and are not considered resolved or confirmed application defects without supporting evidence.

---

## 11. Future Improvements

- Investigate the reported test incidents when a stable and controlled test environment is available.
- Continue evaluating UI test stability under controlled execution environments.
- Reassess parallel execution using a stable and controlled test environment.
- Implement test data cleanup for API scenarios.
- Expand test coverage.
- Review and clean up the existing GitHub Actions pipeline.
- Improve test report generation and publication through GitHub Actions.
- Prepare the framework for Jenkins CI integration.
- Explore additional automation tools and testing practices.
