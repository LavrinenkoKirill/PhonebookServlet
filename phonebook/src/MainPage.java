import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MainPage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String uri = request.getRequestURI();
	if (Contacts.contacts.size() == 0) Contacts.init();
        if (uri.equals("/phonebook/main/addPerson")){
        	Contacts.add(request.getParameter("name"), request.getParameter("number"));
        }
        else if (uri.equals("/phonebook/main/addNumber")){
        	Contacts.add(request.getParameter("name"), request.getParameter("number"));
        }
	else if (uri.equals("/phonebook/main/addGroup")){
               Contacts.addGroup(request.getParameter("group"), request.getParameter("name"));
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>PHONEBOOK</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 lang =\"ru\">Phonebook</h1>");
        out.println("<a href=\"/phonebook/create\">Add person</a>");
        out.println("<a href=\"/phonebook/add\">Add number</a>");
	out.println("<a href=\"/phonebook/addgroup\">Add to group</a> <br>");
	out.println("<a href=\"/phonebook/all\">All</a>");
	out.println("<a href=\"/phonebook/family\">Family</a>");
	out.println("<a href=\"/phonebook/friends\">Friends</a>");
        out.println("<a href=\"/phonebook/work\">Work</a>");
	out.println(Contacts.getString());
        out.println("</body>");
        out.println("</html>");
    }
}
