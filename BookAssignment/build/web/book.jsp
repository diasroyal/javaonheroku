<html>
<head>
<title>Book Database</title>
<script type="text/javascript" src="book.js"></script>
</head>
<body>
<h2 align="center"><em>Book Details</em></h2>

<form name="book_form" method="post" action="bookProcessing" onsubmit="return dataCheck(this)">
  <table align="center" style="width: 600px">
    <colgroup><col align="right"></col><col align="left"></col></colgroup>
    <tr><th align="right">Book Name</th><td><input type="text" name="tf_bookName"/></td></tr>
    <tr><th align="right">Author</th><td><input type="text" name="tf_author"/></td></tr>
    <tr><th align="right">ISBN</th><td><input type="text" name="tf_isbn"/></td></tr>
<tr><th align="right">Price</th><td><input type="text" name="tf_price"/></td></tr>
    <tr><th align="right">Description</th>
        <td><textarea name="tf_desc" cols="40" rows="5"></textarea></td>
    </tr>
    <tr>
      <td/>
      <td>
        <input type="submit" value="Submit"/>&nbsp;&nbsp;
        <input type="reset" value="Cancel"/>     
	    </td>
    </tr>
  </table>
  <br/>
</form>
<p/>

<%
   // Find absolute URL for the Web application root 
   // so this Web application will work even if it may be deployed at different URLs/ports
   String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
   absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));
%>
<div align="center"><a href="<%=absoluteURL%>/query">Search Survey Records</a></div>

<table border="0" align="center" width="80%">
<tr>
<td>
<%  String description = request.getParameter("description");
    String color = request.getParameter("color");
    if (color == null) color = "red";
    if (description != null) {
%>
       <p align="center"><font color="<%=color%>"><%=description%></font></p>
<%
    }
%>
</td>
</tr>
</table>
</body>
</html>
