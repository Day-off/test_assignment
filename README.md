# Project README

## Overview

This project consists of a backend and a frontend component, developed as a test assignment for Askend company. The detailed assignment can be viewed [here](https://s3.eu-central-1.amazonaws.com/eu.peopleforce.io/p3qbmsw2qe6k0m7n00t3y5fl0war?response-content-disposition=inline%3B%20filename%3D%22Test%20assignment%20Java%202023.pdf%22%3B%20filename%2A%3DUTF-8%27%27Test%2520assignment%2520Java%25202023.pdf&response-content-type=application%2Fpdf&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA4L5NWT3XV5H4MYNH%2F20240723%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Date=20240723T062211Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=12d6482e895b5435e6ae2e87c59b87d7e32976ac273d156f5e710b370d297d6c).

## Technologies Used

### Backend

#### Plugins and Dependencies

- **Java**: `17`
- **Spring Boot**: `3.3.1`
- **Gradle**
- **Lombok**
- **MapStruct**

### Database

- **Liquibase Core**: `4.27.0`
- **PostgreSQL**: `42.7.3`
- **Docker**

### Frontend

- **Vue3**: `3.4.29`
- **Vite**: `5.3.1`
- **Axios**: `1.7.2`
- **Vuex**: `4.1.0`
- **Tailwindcss**: `3.4.6`

## Getting Started

### Backend Setup

1. Ensure you have Java 17 installed.
2. Navigate to the backend directory.
3. Use Gradle to build the project.
    - `'./gradlew clean build'`
4. Run the `docker-compose.yml` file to set up the database:
    - `docker-compose up -d`
    - If you want to use another database, configure your database settings in the [application.properties](backend/src/main/resources/application.properties) file.
5. Run the application using your IDE or command line:
    - `./gradlew bootRun`

### Frontend Setup

1. Ensure you have Node.js (version 18 or higher) and npm installed.
2. Navigate to the frontend directory.
3. Run `npm install` to install all dependencies.
4. Run the application using your IDE or command line:
    - `npm run dev`

## Note

### Ports

- Backend: 8080
- Frontend: 5173
- Default database: 5432

## Functionality

### Modal/Dialog Implementation

- **Add Criterion**: Button to create a new criterion for the filter.
- **Delete Criterion**: Button to delete an existing criterion.
- **Validation**:
    - Filter must have a name.
    - Filter must contain at least one criterion.
    - Criterion condition value cannot be empty.

- **Criteria Types**:
    - **Amount**: Uses number comparing conditions.
    - **Title**: Uses text comparing conditions.
    - **Date**: Uses date comparing conditions, with a date picker for date selection.

- **Default Criteria**: Each added criterion defaults to the "Amount" type.
- **Comparing Conditions**: Comparing conditions correspond to the selected criteria type.

- **Filter Dialog/Modal**:
    - Fixed size, configurable to operate in modal/non-modal mode.
    - User can resize only the height.
    - If there are more rows than the dialog can fit, scrolling will appear.
    - In non-modal mode, the user sees the filter dialog as part of the page when clicking the “Add Filter” button.

### Filters Table

- **Display Filters**: Displays all filters.
- **Show Criteria**: Users can see filter criteria by clicking on "show criteria".
- **Actions**:
    - **Delete**: Delete a filter.
    - **Edit**: Edit a filter (opens modal/dialog depending on the mode).
- **Create New Filter**: Button above the table to create a new filter.
- **Switch Mode**: Button above the table to switch between modal and non-modal modes.
