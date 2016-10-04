import javax.servlet.*;

import javax.servlet.http.*;

import java.io.*;

public class Assignment extends HttpServlet {

public void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws IOException, ServletException 
{

String s=request.getParameter("number");

int v=0;

try 
{
 v = Integer.parseInt(s);
}
catch (Exception e) { v = 0; } 

v=v+1; 

response.setContentType("text/html");

PrintWriter out = response.getWriter();

out.println("<html>");

out.println("<body>");

out.println("<h2>The incremental value is, " + v + " </h2>");

out.println("<form method=\"post\" action=\"Assignment\">");

out.println("<input type =\"hidden\" name=\"number\" value=\"" + v + "\"/>");

out.println("<p>Please click ok to increment the value </p>");

out.println("<input type=\"submit\" value=\"OK\" />");

out.println("</form>");
out.println("</body>");

out.println("</html>");

}
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws IOException, ServletException {
doPost(request, response); 
}

}
