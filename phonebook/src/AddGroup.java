import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddGroup extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                PrintWriter out = response.getWriter();
                String[] arr = Contacts.getNames();
		String[] grp = Contacts.getGroupNames();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ADD TO GROUP</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href=\"/phonebook/main\">Back to main</a>");
                if (arr == null){
                        out.println("<p>There aren't persons to add to group</p>");
                }
                else {
                        out.println("<form method=\"GET\" action= \"/phonebook/main/addGroup\">");
			out.println("Group: <select name=\"group\"><br>\n");
                        for (String r : grp){
                                out.println("<option value=\"" + r + "\">" + r + "</option>");
                        }
                        out.println("</select>");

                        out.println("Name: <select name=\"name\"><br>\n");
                        for (String n : arr){
                                out.println("<option value=\"" + n + "\">" + n + "</option>");
                        }
                        out.println("</select>");
                        out.println("<input type=\"submit\" value=\"PUSH\">\n");
                        out.println("</body>");
                        out.println("</html>");
                }

        }
}
