# Course Enrollment Example

This project contains both a back end that is Java using spring boot and a front end that's in JavaScript with Vue built by Vite

## Java Spring Boot Back End

This was run locally using Visual Studio Code to Run and Debug the project. With Spring Initializer Java 21 was chosen for this project.

Without using Visual Studio Code providing that it's at least Java 21, the following also works. It's probably using port 8080.
```
mvnw clean install
mvnw spring-boot:run
```

### The Database and Models

The database used in this project is H2, it's a small light weight datbase with tables initialized by *schema.sql* and *data.sql*, it does not persist any changes between the project running. But there's some sample data already filled for the project.

There wasn't meant to be thousands let alone hundreds of rows in any of the tables, so there isn't anything like pagination, sorting or filtering with the results.

There are 6 tables in the database. A quick summary of them are:
- **teachers** - This represents the teachers in the database
- **students** - This represents the students in the database
- **subjects** - Subjects is part of what represents courses, it's simply something like "Math", "Physics" or "English", and courses are of a particular subject
- **courses** - The other part of what represents courses, it's the representation of a course offering with a subject, start date, end date and teacher assigned to it
- **student_courses** - This is a join table between students and courses, it represents an enrollment of a student into a course. A student's grade is also in this table and it represented by Grade Point numbers between 0 and 4.33, though nothing sets those grades yet.
- **system_logs** - This is the system logs of all actions involving the REST APIs, it contains a timestamp, URI of the request, request method, remote IP address, response status code and possible exceptions

System logs can't be seen anywhere from the front end app but you can see what's in them if you use the H2 console (which is enabled in application.properties) with the path `/h2-console` and the data source url of *jdbc:h2:mem:testdb*

The system logs should get updated with an interceptor middleware that writes the columns mentioned above.

The models used in the project are quite eager in their fetches, and will get most of the associated data with the relationships. More efforts in validation was done on the front end with this project.

Most database operations where done using JPA repositories with built in functions or JPSQL, but some queries used Native SQL instead because of complexities around relationships with the tables.

There's also an associated Service with each repository.

### The APIs
There are 4 controllers and of them a REST API controllers. There paths are:
- **courses**
- **students**
- **subjects**
- **courses**

They contain basic CRUD functions, and some special cases like course enrollment for students, or getting future un-enrolled courses. They generally use GET, POST, DELETE and PUT as expected, though enrollment uses the PATCH method.

As part of the configuration of getting the APIs to work with the front end, CORS was enabled and it allows the source from anywhere.

## Vue.js Single Page app
The environment used had Node 22.16.00 and NPM 11.4.2. The frontend was initialized using Vite and probably just needs the following to run from it's path of `src/main/frontend/enrollment-client`:
```
npm install
npm run dev
```

There is an *.env* which has just one environment variable `VITE_API_URL` which needs to be set to whatever URL the backend is running on.

The frontend app uses **vue-router** for the different pages, there's effectively 9 different pages with a main menu home page, 4 list pages for Teachers, Students, Courses and Subjects, and 4 modify/form pages.

It's Vue 3 that was used for these pages and all components used the composition API. Vanilla javascript fetch was used for all API requests from the front end because I felt it's good enough to use for the project's purposes.

Beyond vue-router the only other plugins used was **Tailwind CSS** as I wanted it to be relatively light weight in it's dependencies.