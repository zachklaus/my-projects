import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.event.*;

public class App implements ActionListener {

  static JFrame frame;
  static JLabel label1;
  static JLabel label2;
  static JLabel label3;
  static JLabel label4;
  static JLabel label5;
  static JLabel label6;
  static JLabel label7;
  static JLabel label8;
  static JLabel label9;
  static JLabel label10;
  static JLabel label11;
  static JLabel label12;
  static JLabel label13;
  static JLabel label14;
  static JTextField textField1;
  static JTextField textField2;
  static JTextField textField3;
  static JTextField textField4;
  static JTextField textField5;
  static JTextField textField6;
  static JTextField textField7;
  static JTextField textField8;
  static JTextField textField9;
  static JButton button1;
  static JButton button2;
  static JButton button3;
  static JButton button4;
  static JButton button5;
  static JButton button6;
  static JButton button7;
  static JButton button8;
  static JButton button9;
  static JList<String> searchResults;

  static DefaultListModel resultsList;

  private static Connection conn;

// setBounds(int x-coordinate, int y-coordinate, int width, int height)

  public static void main(String args[]){

    createConnection();

    if (conn == null) {
      System.err.println("Failed to establish connection!");
    }

    new App();

  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == button1) {
      handleButton1Action();
    }
    else if (e.getSource() == button2) {
      handleButton2Action();
    }
    else if (e.getSource() == button3) {
      handleButton3Action();
    }
    else if (e.getSource() == button4) {
      handleButton4Action();
    }
    else if (e.getSource() == button5) {
      handleButton5Action();
    }
    else if (e.getSource() == button6) {
      handleButton6Action();
    }
    else if (e.getSource() == button7) {
      handleButton7Action();
    }
    else if (e.getSource() == button8) {
      handleButton8Action();
    }
    else if (e.getSource() == button9) {
      handleButton9Action();
    }
  }

  private static void handleButton6Action() {
    ResultSet bookInfo = getBookSearchInfoAuthor(textField9.getText());
    bookSearch(bookInfo);
  }

  private static void handleButton5Action() {
    ResultSet bookInfo = getBookSearchInfoTitle(textField8.getText());
    bookSearch(bookInfo);
  }

  private static void handleButton9Action() {
    label9.setVisible(false);
    label10.setVisible(false);
    label11.setVisible(false);
    label12.setVisible(false);
    label13.setVisible(false);
    textField7.setText("");
    textField8.setText("");
    textField9.setText("");
    textField7.setVisible(false);
    textField8.setVisible(false);
    textField9.setVisible(false);
    button4.setVisible(false);
    button5.setVisible(false);
    button6.setVisible(false);
    button9.setVisible(false);


    label1.setVisible(true);
    textField1.setText("");
    textField1.setVisible(true);
    button1.setVisible(true);
  }

  private static void handleButton8Action() {
    resultsList.removeAllElements();
    searchResults.setVisible(false);
    button7.setVisible(false);
    button8.setVisible(false);
    label14.setVisible(false);
    textField7.setText("");
    textField7.setVisible(false);
    textField8.setText("");
    textField8.setVisible(false);
    textField9.setText("");
    textField9.setVisible(false);
    label10.setVisible(false);
    label9.setVisible(false);
    label1.setVisible(true);
    textField1.setText("");
    textField1.setVisible(true);
    button1.setVisible(true);
  }

  private static void handleButton7Action() {
    resultsList.removeAllElements();
    searchResults.setVisible(false);
    button7.setVisible(false);
    button8.setVisible(false);
    label11.setVisible(true);
    label12.setVisible(true);
    label13.setVisible(true);
    label14.setVisible(false);
    textField7.setText("");
    textField7.setVisible(true);
    textField8.setText("");
    textField8.setVisible(true);
    textField9.setText("");
    textField9.setVisible(true);
    button4.setVisible(true);
    button5.setVisible(true);
    button6.setVisible(true);
    button9.setVisible(true);
  }

  private static void handleButton4Action() {
    ResultSet bookInfo = getBookSearchInfoISBN(textField7.getText());
    bookSearch(bookInfo);
  }

  private static void handleButton3Action() {
    label1.setVisible(true);
    textField1.setText("");
    textField1.setVisible(true);
    button1.setVisible(true);
    label2.setVisible(false);
    label3.setVisible(false);
    label4.setVisible(false);
    label5.setVisible(false);
    label6.setVisible(false);
    label7.setVisible(false);
    label8.setVisible(false);
    textField2.setVisible(false);
    textField3.setVisible(false);
    textField4.setVisible(false);
    textField5.setVisible(false);
    textField6.setVisible(false);
    textField2.setText("");
    textField3.setText("");
    textField4.setText("");
    textField5.setText("");
    textField6.setText("");
    button2.setVisible(false);
    button3.setVisible(false);
  }

  // submitting info to add new member
  private static void handleButton2Action() {
    Statement stmt;
    int rs;

    try {
      stmt = conn.createStatement();
      String newMemberID = textField2.getText();
      String newLastName = textField4.getText();
      String newFirstName = textField3.getText();
      String newDOB = textField5.getText();
      String newGender = textField6.getText();
      String update = "INSERT INTO member VALUES(" + newMemberID + ", \'"
                        + newLastName + "\', \'" + newFirstName + "\', \'"
                        + newDOB + "\', \'" + newGender.toUpperCase() + "\')";
      //System.out.println(update);
      rs = stmt.executeUpdate(update);
    } catch(Exception e) {
      rs = -1;
    }

    if (rs == 1) {
      //int input = JOptionPane.showMessageDialog(frame, "New member successfully added.");
      int input = JOptionPane.showOptionDialog(frame, "New member successfully added.", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, null, null);
      if (input == JOptionPane.OK_OPTION) {
        label1.setVisible(true);
        textField1.setText("");
        textField1.setVisible(true);
        button1.setVisible(true);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        button2.setVisible(false);
        button3.setVisible(false);
      }
    }
    if (rs == -1) {
      String alert = "Failed to add new member. Please check entered data and try again.";
      JOptionPane.showMessageDialog(frame, alert, "Error", JOptionPane.WARNING_MESSAGE);
    }

  }

  // entering member ID
  private static void handleButton1Action() {
    ResultSet memberInfo = getMemberInfo(textField1.getText());
    try {
      if (memberInfo.next()){
        String firstName = memberInfo.getString("first_name");
        String lastName = memberInfo.getString("last_name");
        String memberID = memberInfo.getString("memberID");
        label9.setText("Currenty using library system as: " + firstName + " " + lastName);
        label10.setText("Member ID: " + memberID);
        label1.setVisible(false);
        textField1.setVisible(false);
        button1.setVisible(false);
        label9.setVisible(true);
        label10.setVisible(true);
        label11.setVisible(true);
        label12.setVisible(true);
        label13.setVisible(true);
        textField7.setVisible(true);
        textField8.setVisible(true);
        textField9.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);
        button6.setVisible(true);
        button9.setVisible(true);
      }
      else {
        label1.setVisible(false);
        textField1.setVisible(false);
        button1.setVisible(false);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);
        textField2.setVisible(true);
        textField3.setVisible(true);
        textField4.setVisible(true);
        textField5.setVisible(true);
        textField6.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
      }
    } catch (Exception e) {
      System.err.println("Error in acessing member info.");
    }
  }

  private static void createConnection() {

    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "MySQL URL";
      conn = DriverManager.getConnection(url,"username", "password");
      System.out.println("URL: " + url);
      System.out.println("Connection: " + conn + "\n");
    }catch( Exception e ) {
      e.printStackTrace();
    }

  }

  private static ResultSet getMemberInfo(String memberID) {
    Statement stmt;
    ResultSet rs = null;

    try {
      stmt = conn.createStatement();
      String query = "SELECT * FROM member WHERE memberID = \"" + memberID + "\"";
      rs = stmt.executeQuery(query);
    } catch(Exception e) {
      System.err.println("Error in validating member ID:");
      e.printStackTrace();
    }
    return rs;
  }

  private static void bookSearch(ResultSet bookInfo) {
    try {
      if (bookInfo.next()) {
        label11.setVisible(false);
        label12.setVisible(false);
        label13.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);
        textField9.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        button9.setVisible(false);
        label14.setVisible(true);
        String title = bookInfo.getString("title");
        String libraryName = bookInfo.getString("library_name");
        String shelfNumber = bookInfo.getString("shelf_number");
        String totalCopies = bookInfo.getString("total_copies");
        resultsList.addElement("Title: " + title
                                    + ", Library: " + libraryName
                                    + ", Shelf #: " + shelfNumber
                                    + ", Copies: " + totalCopies);
        while (bookInfo.next()) {
          title = bookInfo.getString("title");
          libraryName = bookInfo.getString("library_name");
          shelfNumber = bookInfo.getString("shelf_number");
          totalCopies = bookInfo.getString("total_copies");
          resultsList.addElement("Title: " + title
                                      + ", Library: " + libraryName
                                      + ", Shelf #: " + shelfNumber
                                      + ", Copies: " + totalCopies);
        }
        searchResults.setVisible(true);
        button7.setVisible(true);
        button8.setVisible(true);
      }
      else {
        label11.setVisible(false);
        label12.setVisible(false);
        label13.setVisible(false);
        textField7.setVisible(false);
        textField8.setVisible(false);
        textField9.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        button9.setVisible(false);
        label14.setVisible(true);
        resultsList.addElement("No books found or available from search.");
        searchResults.setVisible(true);
        button7.setVisible(true);
        button8.setVisible(true);
      }
    } catch (Exception e) {
      System.err.println("Error while searching for book:");
      e.printStackTrace();
      resultsList.addElement("No books found or available from search.");
      label11.setVisible(false);
      label12.setVisible(false);
      label13.setVisible(false);
      textField7.setVisible(false);
      textField8.setVisible(false);
      textField9.setVisible(false);
      button4.setVisible(false);
      button5.setVisible(false);
      button6.setVisible(false);
      label14.setVisible(true);
      searchResults.setVisible(true);
      button7.setVisible(true);
      button8.setVisible(true);
    }
  }

  private static ResultSet getBookSearchInfoAuthor(String authorLastName) {
    Statement stmt;
    ResultSet rs = null;

    authorLastName = authorLastName.toLowerCase();
    authorLastName = authorLastName.substring(0, 1).toUpperCase() + authorLastName.substring(1);

    try {
      stmt = conn.createStatement();
      String query = "SELECT * FROM stored_on JOIN book ON book.ISBN = "
                      + "stored_on.ISBN JOIN written_by ON written_by.ISBN = book.ISBN "
                      + "JOIN author ON author.author_ID = written_by.author_ID "
                      + "WHERE author.last_name = \'" + authorLastName + "\'";
      rs = stmt.executeQuery(query);
    } catch(Exception e) {
      System.err.println("Error in searching for book by author name:");
      e.printStackTrace();
    }
    return rs;
  }

  private static ResultSet getBookSearchInfoISBN(String ISBN) {
    Statement stmt;
    ResultSet rs = null;

    try {
      stmt = conn.createStatement();
      String query = "SELECT * FROM stored_on JOIN book ON book.ISBN = stored_on.ISBN WHERE book.ISBN = \"" + ISBN + "\"";
      rs = stmt.executeQuery(query);
    } catch(Exception e) {
      System.err.println("Error in searching for book by ISBN:");
      e.printStackTrace();
    }
    return rs;
  }

  private static ResultSet getBookSearchInfoTitle(String title) {
    Statement stmt;
    ResultSet rs = null;

    try {
      stmt = conn.createStatement();
      String query = "SELECT * FROM stored_on JOIN book ON book.ISBN = stored_on.ISBN "
                        + "WHERE book.title LIKE \"%" + title + "%\"";
      rs = stmt.executeQuery(query);
    } catch(Exception e) {
      System.err.println("Error in searching for book by title:");
      e.printStackTrace();
    }
    return rs;
  }


  App() {

    //enter member ID screen
    label1 = new JLabel("Please input your member ID:");
    label1.setBounds(210, 50, 400, 50);

    textField1 = new JTextField("");
    textField1.setBounds(210, 90, 200, 25);

    button1 = new JButton("enter");
    button1.setBounds(412, 90, 100, 25);
    button1.addActionListener(this);

    //create new member screen
    label2 = new JLabel("Member ID not recognized.");
    label2.setBounds(210, 50, 200, 50);
    label2.setVisible(false);

    label3 = new JLabel("Please add your information to create a new member.");
    label3.setBounds(210, 75, 400, 50);
    label3.setVisible(false);

    label4 = new JLabel("Enter a member ID (Format ####):");
    label4.setBounds(210, 125, 300, 50);
    label4.setVisible(false);

    textField2 = new JTextField("");
    textField2.setBounds(210, 165, 200, 25);
    textField2.setVisible(false);

    label5 = new JLabel("Enter your first name:");
    label5.setBounds(210, 215, 300, 50);
    label5.setVisible(false);

    textField3 = new JTextField("");
    textField3.setBounds(210, 255, 200, 25);
    textField3.setVisible(false);

    label6 = new JLabel("Enter your last name:");
    label6.setBounds(210, 305, 300, 50);
    label6.setVisible(false);

    textField4 = new JTextField("");
    textField4.setBounds(210, 345, 200, 25);
    textField4.setVisible(false);

    label7 = new JLabel("Enter your date of birth (Format yyyy-mm-dd):");
    label7.setBounds(210, 395, 400, 50);
    label7.setVisible(false);

    textField5 = new JTextField("");
    textField5.setBounds(210, 435, 200, 25);
    textField5.setVisible(false);

    label8 = new JLabel("Enter your gender (Format M or F):");
    label8.setBounds(210, 485, 300, 50);
    label8.setVisible(false);

    textField6 = new JTextField("");
    textField6.setBounds(210, 525, 200, 25);
    textField6.setVisible(false);

    button2 = new JButton("submit");
    button2.setBounds(210, 605, 100, 25);
    button2.addActionListener(this);
    button2.setVisible(false);

    button3 = new JButton("cancel");
    button3.setBounds(320, 605, 100, 25);
    button3.addActionListener(this);
    button3.setVisible(false);

    //logged in as member screen
    label9 = new JLabel("");
    label9.setBounds(210, 50, 500, 25);
    label9.setVisible(false);

    label10 = new JLabel("");
    label10.setBounds(210, 75, 300, 25);
    label10.setVisible(false);

    label11 = new JLabel("Search for book by ISBN:");
    label11.setBounds(210, 150, 400, 25);
    label11.setVisible(false);

    textField7 = new JTextField("");
    textField7.setBounds(210, 175, 200, 25);
    textField7.setVisible(false);

    button4 = new JButton("search");
    button4.setBounds(420, 175, 100, 25);
    button4.addActionListener(this);
    button4.setVisible(false);

    label12 = new JLabel("Search for book by title:");
    label12.setBounds(210, 200, 400, 25);
    label12.setVisible(false);

    textField8 = new JTextField("");
    textField8.setBounds(210, 225, 200, 25);
    textField8.setVisible(false);

    button5 = new JButton("search");
    button5.setBounds(420, 225, 100, 25);
    button5.addActionListener(this);
    button5.setVisible(false);

    label13 = new JLabel("Search for book by last name of author:");
    label13.setBounds(210, 250, 400, 25);
    label13.setVisible(false);

    textField9 = new JTextField("");
    textField9.setBounds(210, 275, 200, 25);
    textField9.setVisible(false);

    button6 = new JButton("search");
    button6.setBounds(420, 275, 100, 25);
    button6.addActionListener(this);
    button6.setVisible(false);

    button9 = new JButton("quit");
    button9.setBounds(210, 325, 75, 25);
    button9.addActionListener(this);
    button9.setVisible(false);

    // search results page
    label14 = new JLabel("Books found by search:");
    label14.setBounds(210, 150, 400, 25);
    label14.setVisible(false);

    resultsList = new DefaultListModel<>();
    searchResults = new JList<>(resultsList);
    searchResults.setBounds(210, 180, 600, 200);
    searchResults.setVisible(false);

    button7 = new JButton("new search");
    button7.setBounds(210, 380, 120, 25);
    button7.addActionListener(this);
    button7.setVisible(false);

    button8 = new JButton("quit");
    button8.setBounds(340, 380, 75, 25);
    button8.addActionListener(this);
    button8.setVisible(false);


    frame = new JFrame("Library Search System");
    frame.add(label1);
    frame.add(label2);
    frame.add(label3);
    frame.add(label4);
    frame.add(label5);
    frame.add(label6);
    frame.add(label7);
    frame.add(label8);
    frame.add(label9);
    frame.add(label10);
    frame.add(label11);
    frame.add(label12);
    frame.add(label13);
    frame.add(label14);
    frame.add(textField1);
    frame.add(textField2);
    frame.add(textField3);
    frame.add(textField4);
    frame.add(textField5);
    frame.add(textField6);
    frame.add(textField7);
    frame.add(textField8);
    frame.add(textField9);
    frame.add(button1);
    frame.add(button2);
    frame.add(button3);
    frame.add(button4);
    frame.add(button5);
    frame.add(button6);
    frame.add(button7);
    frame.add(button8);
    frame.add(button9);
    frame.add(searchResults);
    frame.setSize(1200,900);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setVisible(true);
  }

}
