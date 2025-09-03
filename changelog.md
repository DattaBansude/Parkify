# Changelog

## [VEHMS-M01-T012] - Create Vehicle API - 02-Sep-2025
### Type: Development
- Implemented `POST /api/vehicles` endpoint to create a new vehicle.
- Enforced validations:
  - Vehicle must be linked to an **existing resident**, otherwise request is rejected.
  - **Registration number** is mandatory and cannot be empty.
  - Other required fields must be provided.
- Added proper **error handling** with descriptive messages for invalid input.
- Verified API functionality using Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M01-T012: Implemented Create Vehicle API with validations and resident association

# Changelog

## [VEHMS-M01-T011] - Get Resident Details by Name API - 01-Sep-2025
### Type: Development
- Implemented `GET /api/residents/search` endpoint to fetch resident details by **first name, last name, or both**.
- Added **validation** to reject numeric values in name inputs.
- Returns a clear **error message** if no resident is found with the given input.
- Ensured response is consistent and user-friendly for both success and error scenarios.
- Verified and tested via Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M01-T011: Added API to fetch resident details by name with validation and error handling



# Changelog

## [VEHMS-M01-T010] - Get All Residents API - 31-Aug-2025
### Type: Development
- Implemented `GET /api/residents` endpoint to retrieve all residents.
- Response includes:
  - Resident details.
  - Associated list of vehicles (if available).
- Ensured empty lists are returned instead of null values.
- API tested and verified via Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M01-T010: Added Get All Residents API with vehicles



# Changelog

## [VEHMS-M01-T008] - Implement Swagger Documentation - 31-Aug-2025
### Type: Development
- Integrated **Swagger (Springdoc OpenAPI)** for API documentation.
- Added **title, description, and version** in API docs (`OpenAPI` config).
- Documented every API with:
  - Proper **summary** and **description**.
  - Expected **request body** and **response messages**.
  - **Error messages** for invalid inputs.
- Verified Swagger UI is accessible at `/swagger-ui.html` and `/v3/api-docs`.

### Commit
- Commit Message: `VEHMS-M01-T008: Added Swagger documentation with proper descriptions for all APIs`



# Changelog
## [VEHMS-M01-T006] - Tables Creation & Testing - 30-Aug-2025
### Type: Development & Testing
- Created **Postgres tables** based on implemented entities.
- Configured **one-to-many relationships** with proper `@OneToMany` and `@ManyToOne` mappings.
- Verified **table creation** through schema generation (`hibernate.hbm2ddl.auto=update`).
- Performed initial **integration testing** to validate relationships and table creation.

### Commit
- Commit Message: `VEHMS-M01-T006: Created Postgres tables with one-to-many mappings and tested successfully`

# Changelog

## [VEHMS-M01-T005] - Implement Entities - 30-Aug-2025
### Type: Development
- Implemented entities as per **TR diagram** requirements.
- Added appropriate **enums** with proper serialization handling.
- Configured **Primary Keys** with auto-generation (`@Id`, `@GeneratedValue`).
- Used **Lombok annotations** (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`) instead of traditional getters/setters.
- Ensured clean and maintainable entity design following best practices.

### Commit
- Commit Message: `VEHMS-M01-T005: Implemented entities with enums, Lombok, and auto-generated PKs`

# Changelog

## [VEHMS-M01-T001] - 30-Aug-2025
### Type: Configuration
- Created initial **Spring Boot project** with the following setup:
    - Appropriate **project name** and **packaging structure** (`com.parkify.vehitrack`).
    - Added **basic dependencies** (Spring Boot Starter Web, Lombok, swagger).
    - Generated initial `pom.xml` file with project metadata.
- Verified application starts successfully on default port (8080).
- Initialized **Git repository** for project tracking.

### Commit
- Commit Message: `VEHMS-M01-T001: Initialized Spring Boot project structure`

