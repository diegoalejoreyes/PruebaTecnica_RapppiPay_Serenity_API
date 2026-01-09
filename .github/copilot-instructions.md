<!-- Repository-specific Copilot instructions for AI coding agents. -->
# Copilot instructions — Prueba_API_Shazam_RappiPay

Purpose: help AI coding agents be immediately productive in this Serenity+Cucumber Java test project.

- **Project type & entry points:** Maven Java project focused on automated API tests using Serenity BDD + Cucumber. Key files:
  - `pom.xml` — build lifecycle, dependencies and test plugin setup.
  - `src/test/resources/serenity.conf` — environment URLs, `base.url`, and API keys used by tests.
  - `src/test/resources/features` — Cucumber feature files drive scenarios.
  - `src/test/java/org/shazam/runner/CucumberTestSuite.java` — JUnit entry for running feature suites (uses `@CucumberOptions` and tags).
  - `src/test/java/org/shazam/stepDefinitions` — step definition package (glue configured in runner).

- **How tests run (important):**
  - Unit/functional tests are wired through the Maven Failsafe lifecycle. The project sets `maven-surefire-plugin` to skip tests, and aggregates Serenity reports in the `post-integration-test` phase. To run the test suite and produce Serenity reports use:

```bash
mvn clean verify
```

  - You can run a single feature/tag via the runner by editing the `@CucumberOptions(tags=...)` in `CucumberTestSuite` or by running the runner class directly from the IDE as a JUnit test.

- **Environment configuration:**
  - `serenity.conf` contains environment entries (URLs, `base.url`, `apiKey`, etc.). Tests read values from this file; change the `environments.default` block to point to other environments.
  - Example fields: `base.url` (Shazam API), `apiKey` and `host` for RapidAPI usage. Keep secrets out of committed files — use CI secret injection if adding new credentials.

- **Common patterns to follow when editing tests or adding features:**
  - Feature files live under `src/test/resources/features` and must match the package/glue `org.shazam.stepDefinitions`.
  - Tag-based execution is used; the existing runner filters with `tags = "@get_songs"` — use tags to run subsets.
  - Use Screenplay/Serenity APIs (see `pom.xml` dependencies: `serenity-screenplay`, `serenity-rest-assured`) rather than raw RestAssured assertions where possible to keep reports consistent.

- **Build / debug tips:**
  - To generate reports locally: `mvn clean verify` then open `target/site/serenity/index.html`.
  - To run tests from VS Code: run `org.shazam.runner.CucumberTestSuite` as a JUnit run configuration (or use the Debug configuration shown in the workspace terminal history).
  - If you only want to run integration tests phase explicitly:

```bash
mvn -DskipTests=false org.apache.maven.plugins:maven-failsafe-plugin:2.18.1:integration-test org.apache.maven.plugins:maven-failsafe-plugin:2.18.1:verify
```

- **Project-specific quirks discovered:**
  - `pom.xml` contains mixed Java version settings (`<java.version>1.8</java.version>` and `<maven.compiler.source>22</maven.compiler.source>`). Prefer to follow the `maven-compiler-plugin` configuration for compilation. If changing Java targets, update both locations.
  - Many Serenity-related dependencies are declared and used for test reporting and Screenplay patterns — modifying tests should keep Serenity-compatible APIs to preserve rich reporting.

- **Where to look for related code examples:**
  - Runner: `src/test/java/org.shazam/runner/CucumberTestSuite.java` (shows tag usage and glue package).
  - Config: `src/test/resources/serenity.conf` (environment values demonstrated).
  - Generated reports: `target/site/serenity/` (contains HTML and CSV artifacts after `mvn verify`).

- **When changing tests / adding features, validate:**
  1. Feature syntax under `src/test/resources/features` (match step text with step definitions).
  2. Glue package `org.shazam.stepDefinitions` — update `CucumberOptions` if you move packages.
  3. Run `mvn clean verify` and confirm `target/site/serenity/index.html` contains the new results.

- **Examples of actionable edits for an AI agent:**
  - To add a new scenario: create `src/test/resources/features/new_feature.feature` with tags, implement matching step definitions under `src/test/java/org.shazam/stepDefinitions/` and run `mvn clean verify`.
  - To run a single tagged scenario from the command line, change `@CucumberOptions(tags="@your_tag")` in the runner and run the runner from the IDE, or use a dynamic test runner in CI with a `-Dcucumber.options="--tags @your_tag"` style argument.

If any of the paths or assumptions above look incomplete, tell me which areas to expand (examples: show a sample feature, list step files, clarify CI commands). I can iterate. 
