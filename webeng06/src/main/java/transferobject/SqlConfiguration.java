package transferobject;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class SqlConfiguration implements ServletContextListener {

    public static Connection sqlCon = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con = (Connection) sce.getServletContext().getAttribute("connection");
        this.setSqlCon(con);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public Connection getSqlCon() {
        return sqlCon;
    }

    public void setSqlCon(Connection _con) {
        sqlCon = _con;
    }
}
