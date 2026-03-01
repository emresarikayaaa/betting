# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a test automation framework for [bilyoner.com](https://www.bilyoner.com), built with **Gauge** (BDD) + **Selenium** + **REST Assured** on Java 19 / Maven.

## Commands

```bash
# Compile
mvn compile

# Run all specs
mvn test
# or equivalently:
gauge run specs

# Run a specific spec file
gauge run specs/login.spec
gauge run specs/home.spec

# Run a specific scenario by tag (if tagged)
gauge run --tags "tagname" specs
```

## Architecture

The framework follows the **Page Object Model (POM)** pattern layered under Gauge BDD.

### Layer Structure

```
specs/          → Gauge spec files (BDD scenarios in Turkish)
steps/          → Gauge @Step implementations that call page methods
pages/          → Page Object classes (UI interactions)
constants/      → Locator definitions (By objects) per page
api/            → REST Assured API clients
driver/         → Singleton WebDriver lifecycle (DriverManager)
hooks/          → Gauge @BeforeScenario / @AfterScenario
utils/          → ConfigReader, WaitUtils
```

### Key Design Decisions

- **DriverManager** is a singleton (`getDriver()` / `quitDriver()`). The driver is initialized lazily on first access and torn down in `@AfterScenario`.
- **BasePage** provides reusable Selenium helpers (`click`, `sendKeys`, `getText`, `waitAndFind`, `isElementVisible`) with a built-in 10-second `WebDriverWait`. All page classes extend it.
- **Constants classes** (`LoginPageConstants`, `HomePageConstants`) hold all `By` locators as `static final` fields, keeping selectors separate from page logic.
- **ConfigReader** loads `src/main/resources/config.properties` once via a static initializer. Add new config keys there and read them via `ConfigReader.get("key")`.
- **LoginApiClient** uses REST Assured to call the login API directly (`/api/v6/oauth-manager/users/login`) for hybrid UI+API test verification.
- Spec files are written in **Turkish** — step text in `.spec` files must exactly match the `@Step` annotation strings in the steps classes.

### Configuration (`config.properties`)

| Key | Description |
|---|---|
| `baseUrl` | Browser start URL |
| `platform` | `Android` or `Web` |
| `deviceName` | Appium device (for mobile runs) |
| `appiumServerUrl` | Appium server address |
| `explicitWait` | Default wait in seconds |

## Adding New Tests

1. Add locators to the relevant `constants/` class (or create a new one).
2. Add UI interaction methods to the corresponding `pages/` class extending `BasePage`.
3. Add `@Step` methods in the relevant `steps/` class.
4. Write the scenario in the `.spec` file using the exact step text.