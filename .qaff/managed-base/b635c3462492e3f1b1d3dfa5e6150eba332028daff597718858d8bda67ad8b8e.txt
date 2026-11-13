# Prompt — generate a new api test

> ⚠️  This project does not have the Quality Impact Pack enabled, so there is no `apps/<id>/api/` module to add to. Enable `packs.qualityImpact: true` and re-run `qaff create` first.

Use this prompt with the **API Test Generator** agent
(`.ai/agents/api-test-generator.agent.md`).

## Fill in

- App id: `<one of: swag-labs>`
- Scenario contract ref: `<path to .ai/contracts/scenario-contract.example.json-like file>`
- Test authoring request ref: `<path to .ai/contracts/test-authoring-request.template.json-like file>`
- Endpoint under test (verb + path): `<...>`
- Smoke or regression: `<smoke | regression>`
- Expected status: `<...>`
- Expected body shape (JSON or short description): `<...>`

## Ask for

1. The RestAssured + JUnit 5 test class.
2. Any new handler added to the in-process `HttpServer` stub.
3. A 3-line rationale.

## Reject the result if

- It proceeds without scenario contract and explicit expected results.
- The smoke variant calls a public network endpoint instead of the
  in-process stub.
- It asserts only on status code without inspecting the body.
- It invents endpoint paths, payloads, expected results, URLs, credentials, Jira keys, or Zephyr IDs.
- It claims an MCP tool or external service was called.
