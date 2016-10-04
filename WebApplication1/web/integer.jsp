<%-- 
    Document   : integer
    Created on : Mar 6, 2016, 11:36:08 PM
    Author     : diasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   

<body>

<% 
String s = request.getParameter("number");

int v = 0;
 
try { 
      v = Integer.parseInt(s);
     }
catch (Exception e) {
                           v = 0; 
                          } 

v = v+1; 
%>

<p>
The incremental value is <%=v%> </p>


<form method="post" action="integer.jsp"> 

<p>Click OK button </p>

<input type="submit" value="OK"/> 

<input type ="hidden" name="number" value ="<%=v%>">

</form>

</body>

</html>









</html>
