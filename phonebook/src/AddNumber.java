import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddNumber extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		String[] arr = Contacts.getNames();
		out.println("<html>");
        	out.println("<head>");
        	out.println("<title>ADD NUMBER</title>");
        	out.println("</head>");
        	out.println("<body>");
        	out.println("<a href=\"/phonebook/main\">Back to main</a>");
        	if (arr == null){
        		out.println("<p>There aren't persons to add number</p>");
        	}
        	else {
			out.println("<form method=\"GET\" action= \"/phonebook/main/addNumber\">");
	        	out.println("Name: <select name=\"name\"><br>\n");
	        	for (String n : arr){
	        		out.println("<option value=\"" + n + "\">" + n + "</option>");
	        	}
	        	out.println("</select>");
	        	out.println("Number: <input type=\"text\" name=\"number\"><br>\n");
	        	out.println("<input type=\"submit\" value=\"PUSH\">\n");
	        	out.println("</body>");
	        	out.println("</html>");
        	}

	}
}
