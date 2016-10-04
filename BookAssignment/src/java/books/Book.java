package books;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class Book {
  public String bookName;
  public String author;
  public String isbn;
  public String price;
  public String description;
  public DatabaseAccess database;  
  private Statement stmt;
  static private String sqlString;	
    
  public Book() {
    database = new DatabaseAccess();
  }  
 
  public String nullFilter(String s) {
    if (s == null || s.trim().equals("null"))
      return "";
    return s.trim();
  }
  
  // protect ' and " in SQL values
  public String p(String s) {
    s = s.replace("'", "\\'");
    s = s.replace("\"", "\\\"");
    return s;
  }
        
  public synchronized void saveData() {   
      Statement stmt = database.getStatement();
    String sqlString = "INSERT INTO bookDB (bookName, " +
      "author, isbn, price, description) VALUES ('" + 
      p(bookName) + "', '" + p(author) + "', '" + 
      p(isbn) + "', '" + p(price) + "', '" + p(description) + "')";
          
    try {
      stmt.executeUpdate(sqlString);
    } 
    catch (Exception e) {
      System.out.println("Failed in saveData(): " + sqlString);
      System.out.println(e);
    }
    finally {
      database.releaseStatement();
    } 
  }

  public synchronized boolean retrieveData(String pBookName, String pAuthor) {
    boolean returnCode = true;
    stmt = database.getStatement();
    sqlString = "SELECT bookName, " +
      "author, isbn, price, description " +
  		"FROM bookDB WHERE bookName = '" + pBookName + "' and author = '" + 
  		pAuthor + "'";
    try {
      ResultSet rs = stmt.executeQuery(sqlString);
      rs.next();
      bookName = nullFilter(rs.getString(1));
      author = nullFilter(rs.getString(2));
      isbn = nullFilter(rs.getString(3));
      price = nullFilter(rs.getString(4));
      description = nullFilter(rs.getString(5)); 
    }
    catch (Exception e) {
      returnCode = false;
      System.out.println("Failed in retrieveData(): " + sqlString);
      System.out.println(e);
    }
    finally {
      database.releaseStatement();      
    }
    return returnCode;  
  }
	  
  public synchronized boolean isRecordAvailable(String pBookName, String pAuthor) {
    stmt = database.getStatement();
    boolean isAvailable = false;
    sqlString = "SELECT bookName FROM bookDB WHERE bookName = '" + pBookName + 
                "' and author = '" + pAuthor + "'";
    try {
    	ResultSet rs = stmt.executeQuery(sqlString);
    	isAvailable = rs.next();
    } 
    catch (Exception e) {
    	System.out.println("Failed in isRecordAvailable(): " + sqlString);
    	System.out.println(e);
    }
    finally {
      database.releaseStatement();
    }
    return isAvailable;
  }

  public synchronized void delete(String pBookName, String pAuthor) {
    stmt = database.getStatement();
    sqlString = "DELETE FROM bookDB WHERE bookName = '" + pBookName + 
                "' and author = '" + pAuthor + "'";
    try {
    	stmt.executeUpdate(sqlString);
    } 
    catch (Exception e) {
    	System.out.println("Failed in delete(): " + sqlString);
    	System.out.println(e);
    }
    finally {
      database.releaseStatement();
    }
  }
  
  // For debugging
  private void print() {
    System.out.println("Book Name: \t" + bookName);
    System.out.println("Author: \t" + author);
    System.out.println("ISBN: \t" + isbn);
    System.out.println("Price: \t" + price);
    System.out.println("Description: \t" + description);
  }
 
}
