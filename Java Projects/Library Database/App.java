import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {

  public static void main(String args[]){

    Connection conn = createConnection();

    if (conn == null) {
      System.err.println("Failed to establish connection!");
    }

    ArrayList<libDataEntry> entries = readXML("Libdata.xml");

    System.out.println("Entries from XML file: \n");

    for (int i = 0; i < entries.size(); i++) {
       System.out.println(entries.get(i));
    }

    System.out.println("Database modification log: \n");

    insertEntries(entries, conn);

    runFinalQueries(conn);

  }

  private static void runFinalQueries(Connection con) {

    Statement stmt;
    ResultSet rs;

    try {
      stmt = con.createStatement();
      rs = stmt.executeQuery("SELECT * FROM borrowed_by");
      System.out.println("Contents of borrowed_by: \n");
      System.out.println("memberID          ISBN                 checkout_date       checkin_date");
      while (rs.next()) {
        String outputLine = rs.getString("memberID") + "        "
                                          + "     " + rs.getString("ISBN")
                                          + "        " + rs.getString("checkout_date")
                                          + "        " + rs.getString("checkin_date");
        System.out.println (outputLine);
      }
    } catch(Exception e) {
      System.err.println("Failed to display contents of borrowed_by");
      e.printStackTrace();
    }

    try {
      stmt = con.createStatement();
      String query = "SELECT m.memberID, m.last_name, m.first_name, b.title FROM book b "
               + "JOIN borrowed_by bb ON bb.ISBN = b.ISBN "
               + "JOIN member m ON m.memberID = bb.memberID"
               + " WHERE bb.checkin_date IS NULL";
      //System.out.println("query: " + query);
      rs = stmt.executeQuery(query);

      System.out.println("\nMembers with books checked out: \n");

      String previous = null;
      String memberID = null;
      String lastName = null;
      String firstName = null;
      ArrayList<String> titles = new ArrayList<>();

      while (rs.next()) {

        if (previous == null) {
            memberID = rs.getString("memberID");
            lastName = rs.getString("last_name");
            firstName = rs.getString("first_name");
            titles.add(rs.getString("title"));
            previous = memberID;
          }
          else if (previous.equals(rs.getString("memberID"))) {
            titles.add(rs.getString("title"));
          }
          else {
            System.out.println("Member ID: " + memberID + "\n"
                                + "Last Name: " + lastName + "\n"
                                + "First Name: " + firstName + "\n"
                                + "Has Books Checked Out: " + titles + "\n");
            memberID = rs.getString("memberID");
            lastName = rs.getString("last_name");
            firstName = rs.getString("first_name");
            titles = new ArrayList<>();
            titles.add(rs.getString("title"));
            previous = memberID;
          }
      }

      System.out.println("Member ID: " + memberID + "\n"
                          + "Last Name: " + lastName + "\n"
                          + "First Name: " + firstName + "\n"
                          + "Has Books Checked Out: " + titles + "\n");

    } catch(Exception e) {
      System.err.println("Failed to query members with books checked out");
      e.printStackTrace();
    }

  }

  private static String convertToSQLDate(String oldDate) {
    Date newDate = null;
    String sqlDate = null;

    try {
      newDate = new SimpleDateFormat("MM/dd/yyyy").parse(oldDate);
      sqlDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
    } catch(Exception e) {
      System.err.println("Failed to parse date");
      e.printStackTrace();
    }
    return sqlDate;
  }

  private static void insertEntries(ArrayList<libDataEntry> entries, Connection con) {

    for (libDataEntry entry: entries) {

      Statement stmt;
      ResultSet rs;
      int updateResult;

      if (!entry.Checkout_date.equals("N/A") && entry.Checkin_date.equals("N/A")) {

        try {
          stmt = con.createStatement();
          String query = "SELECT title FROM book WHERE ISBN=\"" + entry.ISBN + "\"";
          // System.out.println("query: " + query);
          rs = stmt.executeQuery(query);
          if (rs.next() == false) {
            System.out.println ("No book exists in a library with ISBN = " + entry.ISBN);
            System.out.println("Error in trying to create new checkout record\n");
            continue;
          }
         }catch(Exception e){
           System.out.print(e);
           System.out.println("\nFailed when verifying if book to be checked out is in a library\n");
           continue;
          }
          try {
            stmt = con.createStatement();
            String query = "INSERT INTO borrowed_by VALUES (\"" + entry.MemberID + "\",\""
                                                                    + entry.ISBN + "\",\""
                                                                    + entry.Checkout_date + "\","
                                                                    + "NULL)";
            // System.out.println("query: " + query);
            updateResult = stmt.executeUpdate(query);
          }catch(Exception e){
            System.out.print(e);
            System.out.println("\nFailed when trying to insert new checkout record into borrowed_by\n");
            continue;
           }

           System.out.println("Inserted new record into borrowed_by with data:\nmemberID = " + entry.MemberID
                                                                    + "\nISBN = " + entry.ISBN
                                                                    + "\ncheckout_date = " + entry.Checkout_date
                                                                    + "\ncheckin_date = NULL\n");
        }
        else if (!entry.Checkin_date.equals("N/A") && entry.Checkout_date.equals("N/A")) {
          try{
            stmt = con.createStatement();
            String query = "SELECT ISBN FROM borrowed_by WHERE memberID=\"" + entry.MemberID
                                                            + "\" AND ISBN=\"" + entry.ISBN
                                                            +"\" AND checkin_date IS NULL";
            // System.out.println("query: " + query);
            rs = stmt.executeQuery(query);
            if (rs.next() == false) {
              System.out.println ("No corresponding checkout record exists for checkin record with data\n"
                                    + "memberID = " + entry.MemberID + "\n"
                                    + "ISBN = " + entry.ISBN);
              System.out.println("Error in trying to update a record with new checkin date\n");
              continue;
            }
          }catch(Exception e){
              System.out.print(e);
              System.out.println("\nFailed when trying to check borrowed_by for corresponding checkout record\n");
              continue;
          }
          try {
            String query = "UPDATE borrowed_by SET checkin_date=\"" + entry.Checkin_date +
                                                "\" WHERE memberID=\"" + entry.MemberID
                                                + "\" AND ISBN=\"" + entry.ISBN
                                                + "\" AND checkin_date IS NULL";
            // System.out.println("query: " + query);
            updateResult = stmt.executeUpdate(query);
          }catch(Exception e){
              System.out.print(e);
              System.out.println("\nFailed when trying to update borrowed_by record with new checkin_date\n");
              continue;
            }
          System.out.println("Updated record in borrowed_by with data:\nmemberID = " + entry.MemberID
                                  + "\nISBN = " + entry.ISBN
                                  + "\ncheckin_date = NULL\n"
                                  + "With new checkin_date = " + entry.Checkin_date + "\n");
      }
    }
  }

  private static Connection createConnection() {

    Connection con = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "MySQL URL";
      con = DriverManager.getConnection(url,"username", "password");
      System.out.println("URL: " + url);
      System.out.println("Connection: " + con + "\n");
    }catch( Exception e ) {
      e.printStackTrace();
    }

    return con;

  }

  private static ArrayList<libDataEntry> readXML(String fileName) {

    ArrayList<libDataEntry> entries = new ArrayList<>();

    try {
      File file = new File(fileName);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeLst = doc.getElementsByTagName("Borrowed_by");

      for (int s = 0; s < nodeLst.getLength(); s++) {

        Node fstNode = nodeLst.item(s);

        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

          libDataEntry entry = new libDataEntry();

          Element sectionNode = (Element) fstNode;

          NodeList memberIdElementList = sectionNode.getElementsByTagName("MemberID");
          Element memberIdElmnt = (Element) memberIdElementList.item(0);
          NodeList memberIdNodeList = memberIdElmnt.getChildNodes();
          //System.out.println("MemberID : "  + ((Node) memberIdNodeList.item(0)).getNodeValue().trim());
          entry.MemberID = ((Node) memberIdNodeList.item(0)).getNodeValue().trim();

          NodeList secnoElementList = sectionNode.getElementsByTagName("ISBN");
          Element secnoElmnt = (Element) secnoElementList.item(0);
          NodeList secno = secnoElmnt.getChildNodes();
          //System.out.println("ISBN : "  + ((Node) secno.item(0)).getNodeValue().trim());
          entry.ISBN = ((Node) secno.item(0)).getNodeValue().trim();

          NodeList codateElementList = sectionNode.getElementsByTagName("Checkout_date");
          Element codElmnt = (Element) codateElementList.item(0);
          NodeList cod = codElmnt.getChildNodes();
          //System.out.println("Checkout_date : "  + ((Node) cod.item(0)).getNodeValue().trim());
          entry.Checkout_date = ((Node) cod.item(0)).getNodeValue().trim();
          if (!entry.Checkout_date.equals("N/A")) {
            entry.Checkout_date = convertToSQLDate(entry.Checkout_date);
          }

          NodeList cidateElementList = sectionNode.getElementsByTagName("Checkin_date");
          Element cidElmnt = (Element) cidateElementList.item(0);
          NodeList cid = cidElmnt.getChildNodes();
          //System.out.println("Checkin_date : "  + ((Node) cid.item(0)).getNodeValue().trim());
          entry.Checkin_date = ((Node) cid.item(0)).getNodeValue().trim();
          if (!entry.Checkin_date.equals("N/A")) {
            entry.Checkin_date = convertToSQLDate(entry.Checkin_date);
          }

          //System.out.println();
          entries.add(entry);

        }

      }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return entries;

  }

  static class libDataEntry {
    public String MemberID;
    public String ISBN;
    public String Checkout_date;
    public String Checkin_date;

    public String toString() {
      return "MemberID: " + this.MemberID + "\n"
              + "ISBN: " + this.ISBN + "\n"
              + "Checkout_date: " + this.Checkout_date + "\n"
              + "Checkin_date: " + this.Checkin_date + "\n";
    }
  }

}
