# Generated Framework Authoring Instruction

> Static instruction template only.
> Not a runtime.
> Not a hosted agent.
> Not a Jira/Zephyr connector.
> Not a DOM collector.
> Not a locator generator.
> Not a test generator by itself.

## Apps in scope

- `swag-labs`

## Mandatory behavior

- Do not invent selectors, Jira keys, Zephyr case IDs, credentials, URLs, or external services.
- Keep smoke tests offline/local unless explicitly configured.
- Preserve JUnit 5, Allure and Maven conventions already present.
- Respect generated-owned file boundaries.

## Validation

- Run `qaff verify --project .`.
- Run `mvn -B verify`.
