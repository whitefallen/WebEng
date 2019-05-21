package presentation;

import transferobject.SqlConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet(urlPatterns ={ "/BootstrapDB" })
public class BootstrapDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BootstrapDB() {
        super();
        
    }
    
    public void init() throws ServletException {
        super.init();
        try
        {
        	Statement stmt = SqlConfiguration.sqlCon.createStatement();
        	stmt.execute("DROP TABLE IF EXISTS ARTIKEL");
        	stmt.execute("CREATE TABLE ARTIKEL(ID INT PRIMARY KEY, NAME VARCHAR(255), PRICE VARCHAR(255), AMOUNT VARCHAR(255) )");
        	stmt.execute("INSERT INTO ARTIKEL VALUES(1,'Kaffe',0.50,1)");
			stmt.execute("INSERT INTO ARTIKEL VALUES(2,'Pizza',150.0,5)");
			stmt.execute("INSERT INTO ARTIKEL VALUES(3,'Schokolade',1.0,10000)");
			stmt.execute("INSERT INTO ARTIKEL VALUES(4,'Kinderigel',50.0,1000)");
			stmt.execute("INSERT INTO ARTIKEL VALUES(5,'Coca-Cola',10.0,100)");
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Values from table 'ARTIKEL':\n");
		
		String query = "select ID,NAME"
        		+ " from ARTIKEL";

        try
        {
			Statement stmt = SqlConfiguration.sqlCon.createStatement();
            ResultSet rs = stmt.executeQuery(query);
           
            while (rs.next()) {
            	response.getWriter().append(rs.getString("ID") + "\t" + rs.getString("NAME") + "\n");
            }
            
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
