import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Work extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Work</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href=\"/phonebook/main\">Back to main</a>");
                out.println(Contacts.getWorkString());
                out.println("</body>");
                out.println("</html>");


        }
}

