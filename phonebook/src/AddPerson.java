import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddPerson extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		out.println("<html>");
        	out.println("<head>");
        	out.println("<title>ADD NEW PERSON</title>");
        	out.println("</head>");
        	out.println("<body>");
        	out.println("<h1>Adding new person</h1>");
        	out.println("<a href=\"/phonebook/main\">Back to main</a><br>");
        	out.println("<form method=\"GET\" action= \"/phonebook/main/addPerson\">");
        	out.println("Name: <input type=\"text\" name=\"name\"><br>\n");
        	out.println("Number: <input type=\"text\" name=\"number\"><br>\n");
        	out.println("<input type=\"submit\" value=\"PUSH\">\n");
        	out.println("</body>");
        	out.println("</html>");
	}
}
