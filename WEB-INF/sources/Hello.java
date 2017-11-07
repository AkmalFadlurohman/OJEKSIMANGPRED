import java.io.*;
import java.lang.ClassNotFoundException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hello extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Ojek Si Mang Pred</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Ojek Si Mang Pred</h1>");
		out.println("<p>Hello " + request.getParameter("name") + "</p><br />");

		/*try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PR_Ojek","root","");
			
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select username from user where user_id=1");
			/*if (resultSet.next()) {
			}
			
			while (resultSet.next()) {
				String username = resultSet.getString("username");
			 	out.println("<p>Hello " + username + "</p>");
			}
			connect.close();
		} catch (SQLException e) {
			out.println(e);
		} catch (ClassNotFoundException e) {
			out.println(e);
		}*/
		out.println("</body>");
		out.println("</html>");
	}
}
