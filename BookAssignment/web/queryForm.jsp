<html>
<head>
<title>Search Book Database</title>
</head>
<body>
<h2 align="center"><em>Book Data Retrieval</em></h2>

<table align="center" width="80%">
<tr><td>
<p>
This form allows administrators to search for Book data. You may use any combination 
of the following search criteria to get the related book records.
</p>
</td></tr>
</table>

<form name="search_form" method="post" action="queryFormProcessing">
<table width="57%"  align="center">
  <colgroup><col align="right"></col><col align="left"></col></colgroup>
  <tr>
    <th align="right">Book Name</th>
    <td><input type="text" name="tf_bookName"/></td>
  </tr>
  <tr>
    <th align="right">Author</th>
    <td><input type="text" name="tf_author"/></td>
  </tr>
  <tr>
    <th align="right">ISBN</th>
    <td><input type="text" name="tf_isbn"/></td>
  </tr>
  <tr>
    <th align="right">Description Key Phrase</th>
    <td><input name="tf_desc" type="text" size="40"/></td>
  </tr>
  <tr/>
  <tr>
    <td colspan="2" align="center"><input name="submit" type="submit" value="Submit"/>
      &nbsp;&nbsp;
      <input name="reset" type="reset" value="Reset"/></td>
    </td>
  <tr>
</table>
</form>
<%
   // Find absolute URL for the Web application root
   String absoluteURL = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
   absoluteURL = absoluteURL.substring(0, absoluteURL.lastIndexOf('/'));
%>
<div align="center"><a href="<%=absoluteURL%>/">Enter Book Info</a></div>
</body>
</html>
