package presentation;

import transferobject.SqlConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection con = (Connection) sce.getServletContext().getAttribute("connection");
        SqlConfiguration.sqlCon = con;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
