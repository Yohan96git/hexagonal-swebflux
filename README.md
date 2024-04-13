# hexagonal-swebflux

Basic project with spring webflux in a Hexagonal architecture.

This project is to manage applicants and job candidates for a new position.

### Definitions:

Applicant: Person who is applying for a job.

Candidate: Person who was selected and is in advanced stages for the job.

### Logic:

In this project there are CRUD operations for applicants and candidates.

Additionally, there is a service "/evaluation" where the logic is to save in the "Applicant" repository if the applicant is not qualified, but if it is qualified, the information will be saved in the "Candidate" repository.

## Installation

### Prerequisites

- Java JDK 17
- Mongo db

### Steps

- Clone repository
- Use branch develop
- USE IDE: Intellij or STS
- Run project in IDE
