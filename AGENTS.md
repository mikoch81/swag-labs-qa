# AGENTS.md

Guidance for AI coding assistants (and human contributors) working in
**swag-labs-qa** — a generated, **owned** QA automation framework. This file is
the vendor-neutral entry point that many agents load automatically;
[CLAUDE.md](CLAUDE.md) points here.

You own this code. Edit it freely and commit it to your own version control — the
project runs on its own (no dependency on the generator that produced it).

## What this is

A regular **Maven multi-module Java** project: Java 21, JUnit 5, Selenium 4, Allure 2.
Open it in IntelliJ IDEA or VS Code and write your own automated tests.

## Module map

- `framework-core` — config loading, helpers (`SoftAssertionHelper`, `TestDataBuilder`, `Retry`, `Resources`).
- `framework-web` — Selenium 4 driver factory, `BasePage`, `PageComponent`, `BaseWebTest`.
- `apps/swag-labs/`

## Dependency direction (do not break)

- `apps/* → framework-*` is **allowed**.
- `framework-* → apps/*` is **forbidden**.
- `framework-core → framework-web` is **forbidden**.
- Cross-app dependencies (`apps/a → apps/b`) are **forbidden**.

See [docs/HOW_TO_KEEP_DEPENDENCY_DIRECTION_VALID.md](docs/HOW_TO_KEEP_DEPENDENCY_DIRECTION_VALID.md).

## How to add a test or page object

- Add a web test → [docs/HOW_TO_ADD_WEB_TEST.md](docs/HOW_TO_ADD_WEB_TEST.md).
- Add a page object → [docs/HOW_TO_ADD_PAGE_OBJECT.md](docs/HOW_TO_ADD_PAGE_OBJECT.md).
- Point tests at your own application → [docs/HOW_TO_TARGET_YOUR_APP.md](docs/HOW_TO_TARGET_YOUR_APP.md).
- Study the working tests beside your test code (`LoginTest`, `CartTest`,
  `CheckoutTest`, their shared start `SwagLabsTest`) and the page objects under
  `pages/` (`SwagLabsPage` is the base of every signed-in page).
- If the `qaff` CLI is available, `qaff scaffold-test` / `qaff scaffold-page`
  generate stubs in the right place.

## Conventions to preserve

- Use the Page Object Model; keep JUnit 5 + Allure annotations readable.
- Use explicit waits (`BasePage` helpers / `WebDriverWait`) — never `Thread.sleep`.
- **Selectors must come from real evidence** (fixture HTML, page source, existing page
  objects) — never invent them. See [docs/locator-policy.md](docs/locator-policy.md).
- Apps under *Test targets* run against their real application (the bundled local fixture
  is only the offline fallback).
- Map meaningful actions to Allure `@Step`s — see [docs/allure-step-policy.md](docs/allure-step-policy.md).

## Test targets

These apps test a real application (`web.target: external`):

- `swag-labs` → `https://www.saucedemo.com/`

New tests for them run against that application: open `resolveSmokeUrl("fixtures/<id>.html")`
from `BaseWebTest` like the generated smoke (`qaff scaffold-test` emits that line).
`-Dweb.baseUrl` / `QAFF_WEB_BASE_URL` override the URL, `-Dweb.target=fixture` /
`QAFF_WEB_TARGET=fixture` run offline against the bundled fixture, and `-Dweb.smoke.*` /
`QAFF_WEB_SMOKE_*` change what the smoke expects — see
[docs/HOW_TO_TARGET_YOUR_APP.md](docs/HOW_TO_TARGET_YOUR_APP.md). Take selectors from the
live page (its DOM in the browser dev tools, existing page objects).

## Before you commit

- Work through [docs/agent-validation-checklist.md](docs/agent-validation-checklist.md).
- Run `qaff verify --project .` (structural health) and `mvn -B verify` (build + tests),
  or the helper scripts under `scripts/` ([docs/HOW_TO_USE_GENERATED_SCRIPTS.md](docs/HOW_TO_USE_GENERATED_SCRIPTS.md)).

## AI Agent Pack

This repo ships a static, offline AI authoring pack — no hosted runtime, no external
LLM required:

- `.ai/agents/` — task-specific agent prompts (test generator, code reviewer, …).
- `.ai/prompts/` — reusable prompt templates.
- `.ai/mcp/` — a local Model Context Protocol server + tool contracts (local-only; no external writes and no secrets by default — the Zephyr connector writes a local dry-run file unless live mode is explicitly enabled).
- `.ai/instructions/generated-framework-authoring.md` — authoring rules for this repo.

Start here: [docs/AI_ASSISTED_DEVELOPMENT.md](docs/AI_ASSISTED_DEVELOPMENT.md).
