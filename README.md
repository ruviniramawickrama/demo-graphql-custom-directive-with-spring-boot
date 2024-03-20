# GraphQL Custom Directive with Spring Boot

This project demonstrates the implementation of a **GraphQL Custom Directive** using Spring Boot. We are using an uppercase directive that can be used to convert field values to uppercase.

### What are GraphQL Directives?

GraphQL directives are a feature that allows you to annotate parts of your GraphQL schema to provide additional instructions or metadata. 

Directives can be used to control various aspects of query execution, validation, and response formatting. They provide a way to add custom logic and behaviors to your GraphQL schema.

GraphQL supports both built-in and custom directives. Built-in directives include @skip, @include, @deprecated, and @cacheControl.

### Prerequisites
Before running the application, make sure the following list is installed/configured on your machine:

* Java version 17 or above
* Maven
* PostgreSQL
* pgAdmin (Optional)

### Running the Application

1. Clone the project using the command `git clone https://github.com/ruviniramawickrama/demo-graphql-custom-directive-with-spring-boot.git`
2. Go to the cloned project's root directory and open a command prompt
3. Build the project using the command `mvn clean install`
4. Run the project using the command `mvn spring-boot:run`
5. Open the GraphQL playground url http://localhost:8080/graphiql
6. Use the sample GraphQL Queries and Mutations given in the file `queries-and-mutations` to test the endpoints (`\src\main\resources\graphql\queries-and-mutations`)

### Application Details

`pom.xml`
- Contains the dependencies related to GraphQL, Postgres (database connection) and Spring Data JPA.

`application.yml`
- Contains the properties related to GraphQL, JPA and Postgres.

`schema.sql` and `data.sql`
- Contains DDL and DML SQL scripts to create and insert data into the **Book** table.
- When the application starts, Spring Boot automatically runs the contents of these files based on the **sql** and **jpa** properties mentioned in the `application.yml` file.

`DemoGraphqlCustomDirectiveApplication.java`
- Entry point of the Spring Boot application.
- It defines a runtime wiring Bean for the GraphQL schema by registering the `UppercaseDirective.java` to handle the behavior of the `@uppercase` directive.

`UppercaseDirective.java`
- Customizes the behavior of the `@uppercase` directive, making it convert field values to uppercase if the directive is used in the schema.
- It overrides the onField method to intercept field definitions, checks for the uppercase directive, creates a new data fetcher to convert string values to uppercase, and updates the field definition accordingly.

`schema.graphqls`
- GraphQL schema definition which contains Queries to retrieve data from the database and Mutations to manipulate data in the database. For this example, we have defined a simple Query named `getBooks` to retrieve Book details from the database.
- `@uppercase` custom directive - A custom GraphQL directive named `@uppercase` is defined using the directive keyword. This directive is specified to be used on field definitions (_i.e._ FIELD_DEFINITION). The `@uppercase` directive is intended to modify the behavior of the fields to which it is applied. Within the Book type definition, the name field is annotated with the `@uppercase` directive. This indicates that whenever the name field is requested in a query, the value returned for this field will be converted to uppercase. This behavior is defined by the implementation of the directive on the server-side using the class `UppercaseDirective.java`.

`BookController.java`
- Controller class which contains the mapping for the Query defined in `schema.graphqls`.

`BookService.java`
- Implemented by `BookServiceImpl.java` which is responsible for communicating with the repository class to retrieve Book details from the database.

`BookRepository.java`
- Implements Spring Data JpaRepository which provides ready-made methods to communicate with the database. It uses `Book.java` as the entity which maps with the respective database table.
