# FlowForge

**Enterprise-grade business process automation platform built on Java & Spring Boot.**

FlowForge lets teams design, run, and monitor multi-step business workflows — approvals, onboarding, procurement, claims processing — without hand-rolling state machines for every use case. It combines a BPMN-compliant workflow engine, a pluggable rules engine, and first-class integrations into a single self-hostable platform.

> Replace `FlowForge` above with your actual project name, and swap this tagline for your own positioning once the product direction is locked in.

---

## Table of Contents

- [Why FlowForge](#why-flowforge)
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

## Why FlowForge

Most teams end up scattering business logic across cron jobs, if/else chains, and ad-hoc status columns. FlowForge centralizes that logic into versioned, auditable process definitions — so a "process" (an approval chain, a claims pipeline, an employee onboarding flow) is a first-class object you can design, test, deploy, and monitor like code.

## Key Features

- **Workflow / BPMN Engine** — Define processes as BPMN 2.0 diagrams or fluent Java DSL. Supports sequential, parallel, and conditional branching, sub-processes, timers, and compensation/rollback steps.
- **Task & Approval Management** — Human task queues with delegation, escalation, SLA timers, and multi-level approval chains (single approver, quorum, sequential sign-off).
- **Rules Engine** — Externalized decision logic (DMN-style tables or embeddable rule scripts) so business users can tune conditions without redeploying code.
- **Integrations** — Native connectors for REST/SOAP APIs, relational databases, and message brokers (Kafka/RabbitMQ), plus a webhook framework for outbound events.
- **Dashboard & Analytics** — Real-time process visibility: in-flight instances, bottleneck detection, average cycle time per stage, SLA breach alerts.
- **Multi-Tenant / Enterprise Ready** — Tenant isolation at the data and process-definition level, RBAC, audit logging, and horizontal scalability for high-throughput deployments.

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

Each service is independently deployable; a single-node "monolith mode" is also supported for smaller deployments.

## Tech Stack

| Layer | Technology |
|---|---|
| Language / Runtime | Java 21, Spring Boot 3.x |
| Workflow Engine | Flowable (or Camunda) BPMN engine |
| Persistence | PostgreSQL, Redis (caching/locks) |
| Messaging | Apache Kafka / RabbitMQ |
| Rules | Drools or DMN decision tables |
| Auth | Spring Security + OAuth2/JWT |
| API | REST (OpenAPI 3.0 spec) |
| Observability | Micrometer + Prometheus + Grafana |
| Build | Maven / Gradle |
| Containerization | Docker, docker-compose (Kubernetes manifests optional) |

> Adjust this table to match whatever you actually implement — this is a strong, credible default stack for a Java BPM platform.

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+ or Gradle 8+
- Docker & docker-compose (for local Postgres/Kafka)

### Run locally

```bash
git clone https://github.com/<your-username>/flowforge.git
cd flowforge

# Start dependencies (Postgres, Kafka, Redis)
docker-compose up -d

# Build and run
./mvnw clean install
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`, with Swagger UI at `http://localhost:8080/swagger-ui.html`.

### Run with Docker

```bash
docker build -t flowforge .
docker run -p 8080:8080 flowforge
```

## Project Structure

```
flowforge/
├── flowforge-engine/        # Core workflow execution engine
├── flowforge-rules/         # Rules/decision engine module
├── flowforge-tasks/         # Task & approval management service
├── flowforge-integrations/  # REST/DB/messaging connectors
├── flowforge-analytics/     # Metrics, dashboards, reporting
├── flowforge-api/           # Public REST API gateway
├── flowforge-common/        # Shared DTOs, utils, exceptions
├── docs/                    # Architecture docs, ADRs, diagrams
├── docker-compose.yml
└── pom.xml
```

## Configuration

Key environment variables (see `application.yml` for the full list):

```yaml
DB_URL: jdbc:postgresql://localhost:5432/flowforge
DB_USERNAME: flowforge
DB_PASSWORD: ${DB_PASSWORD}
KAFKA_BOOTSTRAP_SERVERS: localhost:9092
JWT_SECRET: ${JWT_SECRET}
MULTI_TENANT_MODE: true
```

## API Overview

| Endpoint | Description |
|---|---|
| `POST /api/v1/processes` | Deploy a new process definition |
| `POST /api/v1/processes/{id}/start` | Start a new process instance |
| `GET /api/v1/tasks?assignee=me` | List tasks assigned to current user |
| `POST /api/v1/tasks/{id}/complete` | Complete a task, advancing the process |
| `GET /api/v1/analytics/cycle-time` | Get average cycle time per process stage |

Full OpenAPI spec available at `/v3/api-docs` once running.

## Roadmap

- [ ] Visual BPMN designer (drag-and-drop, web-based)
- [ ] No-code rule builder UI
- [ ] Multi-region deployment support
- [ ] Marketplace for prebuilt process templates (approvals, onboarding, procurement)
- [ ] AI-assisted process optimization (bottleneck detection & suggestions)

## Contributing

Contributions are welcome. Please open an issue to discuss significant changes before submitting a PR. See `CONTRIBUTING.md` for coding standards and commit conventions.

## License

Licensed under the [MIT License](LICENSE) — see the LICENSE file for details.
