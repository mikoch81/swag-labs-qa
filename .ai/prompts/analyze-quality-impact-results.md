# Prompt — analyze Quality Impact Pack results

> ⚠️  This project does not have the Quality Impact Pack enabled. Enable `packs.qualityImpact: true` and re-run `qaff create` before using this prompt; otherwise the inputs below do not exist.

Use this prompt with the **Performance Analyst** agent
(`.ai/agents/performance-analyst.agent.md`).

## Provide

- The current run's normalised metrics (per scenario, per app).
- `framework-performance/baseline/<app>.baseline.json` for each app
  in scope.
- `framework-performance/thresholds.yaml`.
- (Optional) The previous trend report under
  `framework-performance/reports/`.

## Ask for

For each scenario, the agent's standard output block:

1. Verdict (`PASS` / `REGRESSION` / `OVER-BUDGET` /
   `INSUFFICIENT-DATA`).
2. Headline (one sentence with metric + before/after + delta).
3. Evidence (bullets of moved metrics with absolute and relative
   deltas).
4. Likely cause class.
5. Recommended next action.

## Reject the result if

- It claims Gatling / k6 / JMeter / JMH execution is wired in this
  repository — it is not (Phase 1.7 ships budgets + baselines +
  provider docs only).
- It proposes accepting a new baseline that exceeds
  `thresholds.yaml`.
- It invents metric names not present in the input.
