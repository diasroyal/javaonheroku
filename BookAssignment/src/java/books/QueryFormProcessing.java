package books;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class QueryFormProcessing extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Book survey = new Book();
    try {
      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<html><head><title>Search Processing Result</title></head><body>");
      writer.println("<h2 align=\"center\"><em>Data Query Result</em></h2><br/>");
         
      String bookName = request.getParameter("tf_bookName");
      String author = request.getParameter("tf_author");
       String isbn = request.getParameter("tf_isbn");
      String keyPhrase = request.getParameter("tf_desc");

      String query_parameters = "tf_bookName=" + bookName + "&tf_author=" + 
        author + "&tf_isbn=" + isbn + "&tf_desc=" + keyPhrase;
   
      String selectClause = "select distinct bookName, author, isbn, price, description";
      String fromClause = " from bookDB";
      String orderClause = " order by bookName ";
      String whereClause = " where 100 = 100 ";  // true, identity for logical AND

      if ((bookName != null) && !bookName.trim().equals(""))
        whereClause += " and bookName =  '" + survey.p(bookName) + "'";
      if ((author != null) && !author.trim().equals(""))
        whereClause += " and author =  '" + survey.p(author) + "'";
      if ((isbn != null) && !isbn.trim().equals(""))
        whereClause += " and isbn =  '" + survey.p(isbn) + "'";
      if ((keyPhrase != null) && !keyPhrase.trim().equals(""))
        whereClause += " and description like '%" + survey.p(keyPhrase) + "%'";

      String sqlString = selectClause + fromClause + whereClause + orderClause;

      // Find absolute URL for the Web application root 
      // so this Web application will work even if it may be deployed at different URLs/ports
      String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
      absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));

      ResultSet rs = null;
      Statement stmt = survey.database.getStatement();
      try {       
        rs = stmt.executeQuery(sqlString);
        while (rs.next()) {       
          bookName = rs.getString(1);
          author = rs.getString(2);    
          isbn = rs.getString(3);
          String price=rs.getString(4);
	  String message = rs.getString(5);
          writer.println("<hr/>");
          writer.println("<b>" + bookName + ", written by:  " + author + "</b> <br/>");
          writer.println("<b>ISBN:</b> " + isbn + " <br/>");
          writer.println("<b>Price:</b> " + price + " <br/>");
          writer.println("<b>Message:</b> " + message + " <br/>");
          // d_lastName and d_firstName identify which record to be deleted
          // query_parameters holds the current query criteria
          writer.println("<a href=\"" + absoluteURL + "/delete?" + query_parameters +
                         "&d_bookName=" + bookName + "&d_isbn=" + isbn + "\">");
          writer.println("<font  color=\"red\"><em>Delete this record</em></font>");
          writer.println("</a><br/>");
        }
      } 
      catch (Exception e) {
        System.out.println("Failed in data retrieving: " + sqlString);
        System.out.println(e);
      }
      finally {
        survey.database.releaseStatement();
      }
   
      writer.println("<hr/><p/>");
      writer.println("<p align=\"CENTER\"><a href=\"" + absoluteURL + 
        "/query\"><font color=\"blue\"><em>Go Back for Further Query</em></font></a></p>");
      writer.println("</body></html>");
    }
    catch (Exception e){}   
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}
