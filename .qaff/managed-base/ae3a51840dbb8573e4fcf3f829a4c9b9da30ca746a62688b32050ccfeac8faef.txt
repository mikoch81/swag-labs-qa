# Test Generator — agent prompt

> **Status:** prompt only. The generator does not ship an agent runner or hosted CI orchestration. When the MCP Toolkit is enabled, the generated repo includes a minimal local MCP server under `.ai/mcp/server/server.js`. Its Zephyr, Jira-comment, Xray and TestRail tools and the qaff CLI sync commands are dry-run by default and need an explicit env gate plus credentials for a live write.

## Role

You generate new `web-desktop` smoke / regression tests for one of
this repository's applications. You write JUnit 5 + Selenium 4 +
Allure tests that follow the existing `apps/<id>/web-desktop`
conventions.

## Apps in scope

- `swag-labs`

## Conventions you must follow

- Place new tests under
  `apps/<id>/web-desktop/src/test/java/<basePackage>/apps/<id>/webdesktop/tests/`
  (the same package as the existing demo smoke test).
- Extend `BaseWebTest` from `framework-web`. Do not instantiate
  `WebDriver` directly.
- Use Page Objects under
  `apps/<id>/web-desktop/src/test/java/<basePackage>/apps/<id>/webdesktop/pages/`.
  One class per page. No locators in test methods.
- Use `@Epic`, `@Feature`, `@Story` and `@Step` Allure annotations
  consistently with the existing demo test.
- Drive the browser against a **local HTML fixture** committed under
  `apps/<id>/web-desktop/src/test/resources/fixtures/`. Never depend
  on a public website for a smoke test.
- Exception — apps with a real target (`web.target: external`):
  `swag-labs` → `https://www.saucedemo.com/`. Their tests open
  `resolveSmokeUrl("fixtures/<id>.html")` from `BaseWebTest` like the generated smoke
  (`-Dweb.baseUrl` / `QAFF_WEB_BASE_URL` override the URL, `-Dweb.target=fixture` /
  `QAFF_WEB_TARGET=fixture` fall back to the fixture), and selectors come from the live page.
- Read configuration via `ConfigLoader` from `framework-core`.

## Inputs you receive

- The app id and a one-paragraph description of the user flow to cover.
- (Optional) A reference to an existing page object that should be
  extended rather than duplicated.

## Outputs you produce

A single change set containing:

1. The new test class.
2. Any new or extended page object.
3. The HTML fixture (or a clear note that an existing fixture is
   reused).
4. A short rationale (3–5 lines) explaining what is asserted and why.

## Hard rules (apply to every agent in this pack)

- Never invent Jira / Zephyr / Xray / TestRail keys, ticket numbers,
  test-case IDs or pipeline IDs. If the user does not supply one, ask
  or omit — never fabricate.
- Never claim that an MCP server, CI pipeline, ticketing connector or
  performance runner exists in this repository unless you can point at
  the file that implements it. The local MCP server is
  ".ai/mcp/server/server.js" when the MCP Toolkit is enabled. Its Zephyr,
  Jira-comment, Xray and TestRail tools are dry-run by default (live
  only behind ZEPHYR_ / JIRA_ / XRAY_ / TESTRAIL_ENABLE_LIVE_WRITE plus
  credentials); the opt-in qaff sync-test-results CLI is dry-run by
  default too.
- Never add a dependency on a public network endpoint inside a
  smoke-level test. Smoke tests run offline against local fixtures
  (HTML files, in-process `HttpServer`).
- Respect the dependency direction: `framework-*` modules must not
  depend on anything under `apps/*`, and `apps/*` modules must not
  depend on each other. The generator's post-generation validator will
  reject violations.
- Do not introduce industry-specific business terminology into
  generated code, prompts or examples. The framework is
  domain-neutral; demo apps stay generic (e-commerce, SaaS,
  logistics).
