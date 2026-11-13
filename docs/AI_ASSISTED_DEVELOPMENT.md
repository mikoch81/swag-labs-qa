# AI-Assisted Development

## Overview

This framework includes MCP (Model Context Protocol) tools and agents to help you write tests more efficiently. This guide explains how to use them.

## MCP Tools

The `.ai/mcp/` directory contains tool definitions for AI models. These tools help agents:
- Read and understand your test structure
- Query test results and historical data
- Understand generated file ownership
- Get performance metrics

MCP tools never modify your code: `read_test_results`, `compare_performance_baseline`,
`inspect_repository_structure` and `suggest_test_implementation` are read-only;
`create_zephyr_execution` writes a local dry-run file by default and performs a live
Zephyr write only when `ZEPHYR_ENABLE_LIVE_WRITE=true` and credentials are supplied;
`attach_report_to_jira` writes a local dry-run comment the same way and posts one Jira
comment only when `JIRA_ENABLE_LIVE_WRITE=true` and credentials are supplied;
`sync_xray_results` / `sync_testrail_results` work the same way behind
`XRAY_ENABLE_LIVE_WRITE` / `TESTRAIL_ENABLE_LIVE_WRITE`. Agent code generation is a
separate workflow.

## Using AI Agents

### Before AI Generation

1. **Understand the patterns** — Read the how-to guides in `docs/` and study example classes.
2. **Set up your environment** — Ensure all tests run locally.
3. **Document your conventions** — Example: naming patterns, test organization.

### With AI Agents

AI agents can generate:
- Page objects following your patterns
- Test methods for common scenarios
- Test data builders and fixtures
- Documentation updates

But remember:
- **Agents follow your guidance** — Better prompts → better generated code
- **Review generated code** — Don't blindly accept agent output
- **Update prompts** — If generated code doesn't match your patterns, refine your prompt

### After AI Generation

1. **Review** — Check generated tests against your patterns
2. **Modify** — Edit and adjust as needed
3. **Test** — Run tests to verify they work
4. **Commit** — Commit good code to your repository

## Framework Conventions for AI

When prompting AI agents, reference:
- **Page Object Model** — See [HOW_TO_ADD_PAGE_OBJECT.md](HOW_TO_ADD_PAGE_OBJECT.md)
- **Test structure** — See [HOW_TO_ADD_WEB_TEST.md](HOW_TO_ADD_WEB_TEST.md) and [HOW_TO_ADD_API_TEST.md](HOW_TO_ADD_API_TEST.md)
- **Annotations** — See [HOW_TO_USE_ALLURE.md](HOW_TO_USE_ALLURE.md) and [HOW_TO_USE_JUNIT5.md](HOW_TO_USE_JUNIT5.md)
- **Example classes** — Use `ExamplePageObject.java`, `ExampleWebTest.java`, etc. as style guides

## Managed Files and AI

AI agents should **not** modify:
- Managed files (see [GENERATED_FILE_OWNERSHIP.md](GENERATED_FILE_OWNERSHIP.md))
- `pom.xml` files
- `framework-*` module code (unless extending for shared use)

AI agents should generate:
- Custom test classes in `apps/<id>/<layer>/src/test/java`
- Shared page objects and utilities in `framework-web/src/test/java`
- Test data and fixtures in `src/test/resources`

## Next Steps

- Study the example classes in your project
- Read the how-to guides to understand patterns
- Use AI agents to accelerate test creation while maintaining code quality
