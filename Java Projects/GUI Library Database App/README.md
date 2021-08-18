# GUI Library Database App

- This is a Java application that allows a user to connect to, search, and update an example Library database through a Java Swing GUI.
- App.java: GUI application
  - actionPerformed(ActionEvent e): Takes an instance of ActionEvent and calls the appropriate handleButtonAction() method
  - handleButtonAction(): Methods to handle GUI button actions. Actions include adding a new member, searching for info, updating the GUI, etc.
  - createConnection(): establishes the connection with the database
  - getMemberInfo(String memberID): Queries the database for the specified libary member data
  - bookSearch(ResultSet bookInfo): Queries the database for the specified book data
  - getBookSearchInfoAuthor(String authorLastName): Queries the database for books written by the specified author
  - getBookSearchInfoISBN(String ISBN): Queries the database for the data of the book specified by ISBN
  - getBookSearchInfoTitle(String title): Queries he database for the data of the book specified by title
