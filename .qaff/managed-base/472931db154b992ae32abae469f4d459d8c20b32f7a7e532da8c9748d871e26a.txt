# CLAUDE.md

@AGENTS.md

Guidance for Claude Code working in this generated QA automation framework.
**[AGENTS.md](AGENTS.md) is the authoritative entry point** (imported above). This
file restates only the rules that are easiest to get wrong.

## Non-negotiable rules

- **Dependency direction:** `apps/* → framework-*` only. Never make a
  `framework-*` module depend on `apps/*`, never `framework-core → framework-web`,
  never app-to-app.
- **Never invent selectors, URLs, or credentials.** Use selectors backed by real
  evidence (fixture HTML, page source, existing page objects).
- **Real targets:** `swag-labs` → `https://www.saucedemo.com/` — new tests
  for these apps run against that application (`-Dweb.baseUrl` / `QAFF_WEB_BASE_URL`
  override it; see *Test targets* in AGENTS.md); take selectors from the live page.
- **No `Thread.sleep`** — use the explicit-wait helpers on `BasePage`.

## Where to look

- Add a test / page object → [docs/HOW_TO_ADD_WEB_TEST.md](docs/HOW_TO_ADD_WEB_TEST.md),
  [docs/HOW_TO_ADD_PAGE_OBJECT.md](docs/HOW_TO_ADD_PAGE_OBJECT.md).
- Conventions, module map, AI pack → [AGENTS.md](AGENTS.md).
- AI-assisted workflow → [docs/AI_ASSISTED_DEVELOPMENT.md](docs/AI_ASSISTED_DEVELOPMENT.md).

## Before you finish

Run `qaff verify --project .` and `mvn -B verify` (or the `scripts/` helpers), and
work through [docs/agent-validation-checklist.md](docs/agent-validation-checklist.md).
