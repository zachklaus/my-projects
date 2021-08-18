# Library Database

- This is a basic java application for connecting to, querying, and updating an example Library MySQL database.
- "data"
  - Contains the .dump files used to initally populate the database.
  - Contains Libdata.xml which App.java reads and inserts data from.
- "sql"
  - CreateTables.sql: Creates the tables and relations that App.java interacts with.
  - LoadTables.sql: Loads data into the database using the .dump files.
  - NukeTables.sql: Drops the tables of the database.
  - Queries.sql: Executes queries for basic data verification.
  - refresh.sql: Deletes, re-creates, and loads the tables of the database.
- App.java: Main java application. 
  - Connects to a MySQL database using createConnection(). 
  - Data from "Libdata.xml" (stored in "data") is inserted into the database using readXML() and insertEntries().
  - runFinalQueries() queries the database to verify the data has been stored as desired.
- None of the data in this application corresponds to real people.