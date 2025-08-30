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

