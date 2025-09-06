# Changelog

## [VEHMS-M02-T026] - Get Active Visitors API - 06-Sep-2025
### Type: Development
- Implemented `GET /api/visitors/active` endpoint to fetch **active visitors in society**.
- API behavior:
  - Accepts optional filter input → `type=GUEST`, `type=DELIVERY`, or `type=GUEST,DELIVERY`.
  - Returns visitors matching the filter(s).
  - If no filter provided → returns **all active visitors**.
- Ensured only **active (not exited)** visitors are returned.
- Added validation for enum values (`GUEST`, `DELIVERY`).
- Verified functionality in Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M02-T026: Implemented Get Active Visitors API with optional type filters

# Changelog

## [VEHMS-M02-T022] - Update Visitor Exit Time API - 06-Sep-2025
### Type: Development
- Implemented `PATCH /api/visitors/exit/{regNo}` endpoint to update the **end time (exit time)** of a visitor.
- API behavior:
  - Finds visitor details by associated **vehicle registration number**.
  - Updates only the `endTime` field (other details remain unchanged).
- Added validation:
  - Registration number must be valid (length = 10).
  - If visitor record not found → return `"No visitor found for the given registration number"`.
- Verified API through Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M02-T022: Implemented API to update visitor exit time using vehicle registration number

# Changelog

## [VEHMS-M02-T021] - Get Visitors by Registration Number API - 05-Sep-2025
### Type: Development
- Implemented `GET /api/visitors/getByRegistrationNumber/{regNo}` endpoint.
- API behavior:
  - Returns **visitor details** along with **resident details** linked to the given vehicle registration number.
  - Ensures no extra/irrelevant data is included in the response (only resident + visitor details).
- Added validation:
  - Registration number must be valid (length = 10).
  - If no resident/visitor found → return `"No visitor records found for the given registration number"`.
- Verified API through Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M02-T021: Implemented Get Visitors API by registration number with resident mapping


# Changelog

## [VEHMS-M02-T020] - Create Visitor API - 04-Sep-2025
### Type: Development
- Implemented `POST /api/visitors` endpoint to create new visitor records.
- Visitor details are always **mapped to an existing resident** (foreign key association).
- Added **mandatory field validations** to ensure required details are provided.
- If mandatory fields are missing → return descriptive error message.
- Verified API functionality through Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M02-T020: Implemented Create Visitor API with resident mapping and validations

# Changelog

## [VEHMS-M01-T013] - Get Resident by Vehicle Registration Number API - 03-Sep-2025
### Type: Development
- API behavior:
  - Returns **only resident details** associated with the given vehicle.
  - Does **not return extra vehicle details**.
- Validation rules:
  - Registration number must always be **exactly 10 characters**.
  - If format/length is invalid → return `Invalid registration number`.
  - If no vehicle found for the provided registration number → return `"No resident found for given registration number"`.
- Verified functionality via Swagger UI and Postman.

### Commit
- Commit Message: VEHMS-M01-T013: Added API to fetch resident details using vehicle registration number with validation

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

