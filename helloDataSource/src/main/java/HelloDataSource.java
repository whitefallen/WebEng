import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet(urlPatterns ={ "/HelloDataSource" })
public class HelloDataSource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloDataSource() {
        super();
        
    }
    
    public void init() throws ServletException {
        super.init();
        Connection con = (Connection) getServletContext().getAttribute("connection");
        try
        {
        	
        	Statement stmt = con.createStatement();
        	stmt.execute("DROP TABLE IF EXISTS TEST");
        	stmt.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        	stmt.execute("INSERT INTO TEST VALUES(1,'HelloDataSourceDB')");
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
	
		response.getWriter().append("Hello HelloDataSource\n"); 
		response.getWriter().append("Values from table 'TEST':\n");
		
		String query = "select ID,NAME"
        		+ " from TEST";

        try
        {
        	// get connection dbstarter-contextListener (settings in web.xml)
        	Connection con = (Connection) getServletContext().getAttribute("connection");
        	       	
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
           
            while (rs.next()) {
            	response.getWriter().append(rs.getString("ID") + "\t" + rs.getString("NAME") + "\n");
            	//System.out.println(rs.getString("NAME"));
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
