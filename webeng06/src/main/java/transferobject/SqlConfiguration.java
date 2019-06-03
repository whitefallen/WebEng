package transferobject;

import java.sql.Connection;

// Den ServletContextListener als eigene Klasse die dann die Connection in dem Transferobjekt setzt!
public class SqlConfiguration {

    public static Connection sqlCon = null;

    public Connection getSqlCon() {
        return sqlCon;
    }

    public void setSqlCon(Connection _con) {
        sqlCon = _con;
    }
}
