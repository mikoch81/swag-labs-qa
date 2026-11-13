# Generation Summary

`qaff create` produced a real, buildable Java/Maven/Selenium/JUnit5/Allure
framework from the inputs below.

## Inputs

- **Config:** `showcase-java.yaml`
- **Project:** `swag-labs-qa`
- **Java target:** `21`
- **Blueprint:** `java-maven-selenium-junit5-allure` v0.1.0

## Selected blueprint

- **id:** `java-maven-selenium-junit5-allure`
- **version:** 0.1.0
- **name:** Java + Maven + Selenium 4 + JUnit 5 + Allure
- **maturity:** stable
- **description:** Default web-automation blueprint. Generates a Maven multi-module Java framework with Selenium 4, JUnit 5 and Allure 2 reporting. Targets Java 21 (default) with Java 17 as a compatibility option.
- **language:** java
- **buildTool:** maven
- **testRunner:** junit5
- **automationTools:** selenium-4, webdrivermanager
- **reportingTools:** allure-2
- **supportedChannels:** web-desktop
- **supportedIntegrations:** github-actions, jenkins
- **supportedQualityPacks:** integration, api, performance
- **outputStructure:** pom.xml, framework-core/, framework-web/, apps/, qa-factory.lock.json
- **manifest path:** `java-maven-selenium-junit5-allure/blueprint.yaml`

## Applications

- `apps/swag-labs` — Swag Labs (Sauce Demo)  (layers: web-desktop)

- **External web targets:** `swag-labs` → `https://www.saucedemo.com/` — the web smoke runs against these URLs, so the first `mvn verify` fails until they are reachable (replace a placeholder such as `https://example.invalid`; `qaff doctor --project .` probes them; see README "External web targets").

## Files written

```
.ai/agents/api-test-generator.agent.md
.ai/agents/code-reviewer.agent.md
.ai/agents/failure-analyst.agent.md
.ai/agents/integration-test-planner.agent.md
.ai/agents/locator-healer.agent.md
.ai/agents/performance-analyst.agent.md
.ai/agents/test-generator.agent.md
.ai/contracts/allure-step-mapping.example.json
.ai/contracts/generated-test-review.example.json
.ai/contracts/locator-evidence.example.json
.ai/contracts/scenario-contract.example.json
.ai/contracts/test-authoring-request.template.json
.ai/instructions/allure-step-policy.md
.ai/instructions/generated-framework-authoring.md
.ai/instructions/locator-policy.md
.ai/mcp/mcp-config.example.json
.ai/mcp/performance-results-mcp.md
.ai/mcp/README.md
.ai/mcp/repository-inspection-mcp.md
.ai/mcp/server.manifest.json
.ai/mcp/server/server.js
.ai/mcp/test-results-mcp.md
.ai/mcp/tools/attach-report-to-jira.tool.md
.ai/mcp/tools/compare-performance-baseline.tool.md
.ai/mcp/tools/create-zephyr-execution.tool.md
.ai/mcp/tools/inspect-repository-structure.tool.md
.ai/mcp/tools/read-test-results.tool.md
.ai/mcp/tools/suggest-test-implementation.tool.md
.ai/mcp/tools/sync-testrail-results.tool.md
.ai/mcp/tools/sync-xray-results.tool.md
.ai/prompts/analyze-allure-results.md
.ai/prompts/analyze-quality-impact-results.md
.ai/prompts/fix-failed-test.md
.ai/prompts/generate-api-test.md
.ai/prompts/generate-web-test-from-scenario.md
.ai/prompts/generate-web-test.md
.ai/prompts/review-generated-test.md
.ai/prompts/review-new-test.md
.claude/agents/api-test-generator.md
.claude/agents/code-reviewer.md
.claude/agents/failure-analyst.md
.claude/agents/integration-test-planner.md
.claude/agents/locator-healer.md
.claude/agents/test-generator.md
.claude/rules/authoring-policies.md
.claude/skills/analyze-allure-results/SKILL.md
.claude/skills/fix-failed-test/SKILL.md
.claude/skills/generate-api-test/SKILL.md
.claude/skills/generate-web-test-from-scenario/SKILL.md
.claude/skills/generate-web-test/SKILL.md
.claude/skills/review-generated-test/SKILL.md
.claude/skills/review-new-test/SKILL.md
.gitattributes
.github/workflows/qaff-ci.yml
.gitignore
.mcp.json
AGENTS.md
apps/swag-labs/web-desktop/pom.xml
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/ExampleTestData.java
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/ExampleWebTest.java
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/pages/DemoHomePage.java
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/pages/ProductCardComponent.java
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/tests/DemoHomeSmokeTest.java
apps/swag-labs/web-desktop/src/test/java/com/example/swaglabsqa/apps/swaglabs/webdesktop/tests/DemoProductCardTest.java
apps/swag-labs/web-desktop/src/test/resources/allure.properties
apps/swag-labs/web-desktop/src/test/resources/config.properties
apps/swag-labs/web-desktop/src/test/resources/fixtures/swag-labs.html
CLAUDE.md
docs/agent-validation-checklist.md
docs/AI_ASSISTED_DEVELOPMENT.md
docs/ai-api-test-authoring.md
docs/ai-authoring-readiness.md
docs/ai-web-test-authoring.md
docs/allure-step-mapping-contract.md
docs/allure-step-policy.md
docs/ci-cd.md
docs/DEVELOPER_GUIDE.md
docs/GENERATED_FILE_OWNERSHIP.md
docs/generated-architecture.md
docs/generated-test-review-contract.md
docs/github-actions.md
docs/HOW_TO_ADD_PAGE_OBJECT.md
docs/HOW_TO_ADD_WEB_TEST.md
docs/HOW_TO_INTERPRET_VERIFY.md
docs/HOW_TO_KEEP_DEPENDENCY_DIRECTION_VALID.md
docs/HOW_TO_RUN_TESTS_LOCALLY.md
docs/HOW_TO_TARGET_YOUR_APP.md
docs/HOW_TO_USE_ALLURE.md
docs/HOW_TO_USE_GENERATED_SCRIPTS.md
docs/HOW_TO_USE_JUNIT5.md
docs/how-to-run.md
docs/local-fixture.md
docs/local-scripts.md
docs/locator-evidence-contract.md
docs/locator-policy.md
docs/scenario-contract.md
docs/test-authoring-request.md
framework-core/pom.xml
framework-core/src/main/java/com/example/swaglabsqa/core/config/ConfigLoader.java
framework-core/src/main/java/com/example/swaglabsqa/core/util/ResourcePath.java
framework-core/src/main/java/com/example/swaglabsqa/core/util/Resources.java
framework-core/src/main/java/com/example/swaglabsqa/core/util/Retry.java
framework-core/src/main/java/com/example/swaglabsqa/core/util/SoftAssertionHelper.java
framework-core/src/main/java/com/example/swaglabsqa/core/util/TestDataBuilder.java
framework-web/pom.xml
framework-web/src/main/java/com/example/swaglabsqa/web/config/SmokeExpectations.java
framework-web/src/main/java/com/example/swaglabsqa/web/config/WebConfig.java
framework-web/src/main/java/com/example/swaglabsqa/web/driver/WebDriverFactory.java
framework-web/src/main/java/com/example/swaglabsqa/web/extension/RegressionTest.java
framework-web/src/main/java/com/example/swaglabsqa/web/extension/RetryExtension.java
framework-web/src/main/java/com/example/swaglabsqa/web/extension/RetryingTest.java
framework-web/src/main/java/com/example/swaglabsqa/web/extension/ScreenshotOnFailureExtension.java
framework-web/src/main/java/com/example/swaglabsqa/web/extension/SmokeTest.java
framework-web/src/main/java/com/example/swaglabsqa/web/page/BasePage.java
framework-web/src/main/java/com/example/swaglabsqa/web/page/PageComponent.java
framework-web/src/main/java/com/example/swaglabsqa/web/test/BaseWebMobileTest.java
framework-web/src/main/java/com/example/swaglabsqa/web/test/BaseWebTest.java
framework-web/src/test/java/com/example/swaglabsqa/web/page/ExamplePageObject.java
pom.xml
qa-factory.lock.json
README.md
scripts/allure-report.ps1
scripts/allure-report.sh
scripts/run-all-tests.ps1
scripts/run-all-tests.sh
scripts/run-android-smoke.ps1
scripts/run-android-smoke.sh
scripts/run-api-smoke.ps1
scripts/run-api-smoke.sh
scripts/run-gatling-smoke.ps1
scripts/run-gatling-smoke.sh
scripts/run-integration-smoke.ps1
scripts/run-integration-smoke.sh
scripts/run-ios-smoke.ps1
scripts/run-ios-smoke.sh
scripts/run-jmeter-import-smoke.ps1
scripts/run-jmeter-import-smoke.sh
scripts/run-k6-import-smoke.ps1
scripts/run-k6-import-smoke.sh
scripts/run-mobile-appium-smoke.ps1
scripts/run-mobile-appium-smoke.sh
scripts/run-performance-smoke.ps1
scripts/run-performance-smoke.sh
scripts/run-web-external-smoke.ps1
scripts/run-web-external-smoke.sh
scripts/run-web-mobile-smoke.ps1
scripts/run-web-mobile-smoke.sh
scripts/run-web-smoke.ps1
scripts/run-web-smoke.sh
scripts/verify-framework.ps1
scripts/verify-framework.sh
GENERATION_SUMMARY.md
```

## Post-generation validation

- **Dependency direction:** PASSED
- **Modules checked:** 3
- **Rule:** `apps/* -> framework-*` allowed; `framework-* -> apps/*` forbidden.

## Build it

```bash
mvn verify
```
