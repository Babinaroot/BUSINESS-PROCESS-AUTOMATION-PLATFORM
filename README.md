# Business Process Automation Platform

**Enterprise-grade business process automation platform built on Java & Spring Boot.**

Business Process Automation Platform lets teams design, run, and monitor multi-step business workflows — approvals, onboarding, procurement, claims processing — without hand-rolling state machines for every use case. It combines a BPMN-compliant workflow engine, a pluggable rules engine, and first-class integrations into a single self-hostable platform.

> 🚧 Status: Early development — core workflow engine in progress.

---

## Table of Contents

- [Why This Platform](#why-this-platform)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [API Overview](#api-overview)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

---

## Why This Platform

Most teams end up scattering business logic across cron jobs, if/else chains, and ad-hoc status columns. This platform centralizes that logic into versioned, auditable process definitions — so a "process" (an approval chain, a claims pipeline, an employee onboarding flow) is a first-class object you can design, test, deploy, and monitor like code.

## Key Features

- **Workflow / BPMN Engine** — Define processes as BPMN 2.0 diagrams or fluent Java DSL. Supports sequential, parallel, and conditional branching, sub-processes, timers, and compensation/rollback steps.
- **Task & Approval Management** — Human task queues with delegation, escalation, SLA timers, and multi-level approval chains (single approver, quorum, sequential sign-off).
- **Rules Engine** — Externalized decision logic (DMN-style tables or embeddable rule scripts) so business users can tune conditions without redeploying code.
- **Integrations** — Native connectors for REST/SOAP APIs, relational databases, and message brokers (Kafka/RabbitMQ), plus a webhook framework for outbound events.
- **Dashboard & Analytics** — Real-time process visibility: in-flight instances, bottleneck detection, average cycle time per stage, SLA breach alerts.
- **Multi-Tenant / Enterprise Ready** — Tenant isolation at the data and process-definition level, RBAC, audit logging, and horizontal scalability for high-throughput deployments.

*Note: these are the platform's target capabilities. See Project Structure below for what's actually implemented today.*

## Architecture

```
                     ┌─────────────────────┐
                     │     API Gateway     │
                     └──────────┬──────────┘
                                │
        ┌───────────────────────┼───────────────────────┐
        │                       │                       │
┌───────▼────────┐    ┌─────────▼─────────┐    ┌─────────▼────────┐
│ Process Engine   │    │  Rules Engine     │    │ Task Service      │
│ (BPMN execution) │◄──►│ (decision tables) │    │ (queues, SLAs)    │
└───────┬──────────┘    └───────────────────┘    └─────────┬────────┘
        │                                                   │
        │               ┌───────────────────┐               │
        └──────────────►│  Integration Bus   │◄──────────────┘
                         │ (REST/DB/Kafka)   │
                         └─────────┬─────────┘
                                   │
                         ┌─────────▼─────────┐
                         │  Analytics Store   │
                         │  + Dashboard API   │
                         └───────────────────┘
```

This is the target architecture as the platform grows into separate modules. The current codebase is a single-module starter (see Project Structure).

## Tech Stack

| Layer | Technology |
|---|---|
| Language / Runtime | Java 21, Spring Boot 3.x |
| Workflow Engine | In-memory starter today; Flowable/Camunda planned |
| Persistence | Currently in-memory; PostgreSQL planned |
| Messaging | Kafka/RabbitMQ planned |
| Rules | Drools or DMN decision tables planned |
| Auth | Spring Security + OAuth2/JWT planned |
| API | REST (OpenAPI 3.0 spec) |
| Build | Maven |
| Containerization | Docker (planned) |

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+

### Run locally

```bash
git clone https://github.com/Babinaroot/BUSINESS-PROCESS-AUTOMATION-PLATFORM.git
cd BUSINESS-PROCESS-AUTOMATION-PLATFORM

mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Project Structure

Currently a single-module starter — will split into separate modules (engine, rules, tasks, integrations, analytics) as features grow. See Roadmap.

```
BUSINESS-PROCESS-AUTOMATION-PLATFORM/
├── src/main/java/com/flowforge/
│   ├── model/         # Process, step, and instance data models
│   ├── service/        # Core workflow logic (in-memory for now)
│   └── controller/     # REST API endpoints
├── src/main/resources/
│   └── application.yml
├── pom.xml
├── .gitignore
└── README.md
```

## Configuration

Currently runs with no external dependencies — in-memory storage, no database or auth yet.

```yaml
server:
  port: 8080
```

> Database, messaging, and auth config will be added here as those features are implemented (see Roadmap).

## API Overview

| Endpoint | Description |
|---|---|
| `POST /api/v1/processes` | Create a new process definition |
| `GET /api/v1/processes/{id}` | Get a process definition |
| `POST /api/v1/processes/{id}/start` | Start a new process instance |
| `GET /api/v1/instances/{id}` | Get instance status and current step |
| `POST /api/v1/instances/{id}/complete-current` | Complete the current step, advancing the instance |

## Roadmap

- [ ] Persist definitions/instances to PostgreSQL
- [ ] Split into multi-module architecture (engine, rules, tasks, integrations, analytics)
- [ ] BPMN 2.0 diagram support
- [ ] Rules engine (DMN-style decision tables)
- [ ] Kafka/RabbitMQ integration
- [ ] Auth (OAuth2/JWT) and multi-tenancy
- [ ] Dashboard & analytics API
- [ ] Visual BPMN designer (drag-and-drop, web-based)

## Contributing

Contributions are welcome. Please open an issue to discuss significant changes before submitting a PR.

## License

Licensed under the [MIT License](LICENSE) — see the LICENSE file for details.
