# Locator Policy Instruction

> Static instruction template only. Not a runtime.

## Rules

- Never invent selectors.
- Use selectors backed by real evidence (page source, fixture HTML, existing page objects).
- Prefer stable attributes; avoid positional selectors unless justified.

DOM collection automation, locator generation and self-healing are future scope.

## Validation

- Run `qaff verify --project .` and `mvn -B verify` after locator edits.
