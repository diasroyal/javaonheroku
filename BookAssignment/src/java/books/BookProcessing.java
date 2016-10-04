package books;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class BookProcessing extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Book survey = new Book();
    try {
      String description = "";
      try {
        survey.bookName = request.getParameter("tf_bookName");
        survey.author = request.getParameter("tf_author");
        survey.isbn = request.getParameter("tf_isbn");
        survey.price = request.getParameter("tf_price");
	survey.description = request.getParameter("tf_desc");
      }
      catch (Exception e) {
        description = "Input error: " + e;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/survey?color=red&description=" + description);
        dispatcher.forward(request, response);
      }
      if (survey.isRecordAvailable(survey.bookName, survey.author)) {
        survey.delete(survey.bookName, survey.author);
        description += "Your previous record has been deleted.<br/>";
      }
      survey.saveData();
      description += "Thank you for submitting your book data. Your data has been securely saved in our book database.";
      description += "\n\n<div align=\"center\"> <h4><a href=\"javascript:window.close()\">Close Window</a></h4> </div>";
      RequestDispatcher dispatcher = request.getRequestDispatcher("/book?survey=blue&description=" + description);
      dispatcher.forward(request, response);
    }
    catch (Exception e) {}
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
}
