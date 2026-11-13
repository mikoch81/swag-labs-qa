# Swag Labs — end-to-end test automation showcase

[![CI](https://github.com/mikoch81/swag-labs-qa/actions/workflows/qaff-ci.yml/badge.svg)](https://github.com/mikoch81/swag-labs-qa/actions/workflows/qaff-ci.yml)
[![Allure report](https://img.shields.io/badge/Allure-latest%20report-blue)](https://mikoch81.github.io/swag-labs-qa/)

A working example of the test framework I deliver to clients: real browser tests
against a real shop, a pipeline that runs them on every change, and a report anyone
on the team can read.

- **Application under test:** [Sauce Demo](https://www.saucedemo.com/) ("Swag Labs"),
  a public demo shop published by Sauce Labs for test-automation practice.
- **Latest test report:** <https://mikoch81.github.io/swag-labs-qa/> — rebuilt by
  every pipeline run on `main`.
- **Stack:** Java 21, Maven, Selenium 4, JUnit 5, Allure 2, GitHub Actions.

## What is tested

13 tests, about 20 seconds on a laptop.

| Area | Scenario | Suite |
| --- | --- | --- |
| Login | The login page renders | smoke |
| Login | A standard user signs in and lands on the product list | smoke |
| Login | A locked-out user is refused with a clear message | regression |
| Login | The form names the missing field (username, password) | regression |
| Product list | Every product shows a name, a price and a cart button | regression |
| Product list | Sorting by price lists the cheapest product first | regression |
| Product list | Sorting by name Z to A reverses the alphabet | regression |
| Cart | Added products are counted on the cart icon and listed in the cart | regression |
| Cart | Removing a product leaves the rest of the cart untouched | regression |
| Checkout | A customer buys two products and gets an order confirmation | smoke |
| Checkout | The item total equals the sum of the product prices | regression |
| Checkout | Checkout cannot continue without a first name | regression |

The tests live in
[`apps/swag-labs/web-desktop/src/test/java/.../tests`](apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/tests)
and read like the table above:

```java
@SmokeTest
@DisplayName("A customer buys two products and gets an order confirmation")
void customerPlacesAnOrder() {
    CheckoutPage checkout = checkoutWithTwoProducts()
            .enterCustomer("Ada", "Example", "00-001")
            .finish();

    assertEquals("Thank you for your order!", checkout.confirmation());
    assertEquals(0, checkout.cartBadgeCount(), "the cart is emptied by a placed order");
}
```

## What you get with the framework

- **Page objects** — one class per screen
  ([`pages/`](apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/pages)),
  so a change in the application is fixed in one place, not in every test.
- **Readable reports** — every action is a named step in the Allure report, and a
  failed test attaches a screenshot of what the browser showed.
- **Stable waits** — explicit waits everywhere, no fixed sleeps.
- **Suites** — `smoke` for a fast health check, `regression` for the full run.
- **Pipeline** — GitHub Actions runs the tests on every push and pull request, every
  weekday morning, and on demand; the report is published to GitHub Pages.
- **Clear layering** — shared code in `framework-core` and `framework-web`, tests per
  application under `apps/`. The build fails if a module depends in the wrong
  direction, so the structure holds as the suite grows.

## Ready for AI assistants

The repository carries the instructions an AI coding assistant needs to add tests the
same way a team member would:

- [`AGENTS.md`](AGENTS.md) / [`CLAUDE.md`](CLAUDE.md) — project map, conventions, how
  to run and verify.
- [`.ai/`](.ai) — task prompts (write a test from a scenario, review a test, analyse a
  failure, repair a locator).
- [`.claude/`](.claude) — the same tasks as ready-to-use skills for Claude Code.
- [`.mcp.json`](.mcp.json) — a local tool server that lets the assistant read test
  results and failures. It runs on your machine only and sends nothing outside.

See [`docs/AI_ASSISTED_DEVELOPMENT.md`](docs/AI_ASSISTED_DEVELOPMENT.md).

## Run it yourself

You need Java 21, Maven 3.9+ and Chrome. The first run downloads the matching browser
driver.

```bash
mvn verify                     # everything
mvn verify -Dgroups=smoke      # the fast health check only
mvn verify -Dbrowser=firefox   # another browser (chrome, firefox, edge)
bash scripts/allure-report.sh  # build the HTML report under target/site/
```

To watch the browser, set `headless=false` in
[`config.properties`](apps/swag-labs/web-desktop/src/test/resources/config.properties).

## How it is maintained

The framework skeleton — build files, base classes, pipeline, documentation — is
produced from one configuration file by my own generator, so every client starts from
the same proven base. Improvements to that base arrive later as a reviewed update that
keeps the tests and edits written in the repository. The repository itself is plain
Maven and Java: it builds and runs without the generator.

More detail: [`docs/how-to-run.md`](docs/how-to-run.md),
[`docs/generated-architecture.md`](docs/generated-architecture.md),
[`docs/HOW_TO_ADD_WEB_TEST.md`](docs/HOW_TO_ADD_WEB_TEST.md).

---

Sauce Demo is a practice site owned by Sauce Labs. This project is not affiliated with
Sauce Labs and runs functional tests only — no load is generated against the site.
