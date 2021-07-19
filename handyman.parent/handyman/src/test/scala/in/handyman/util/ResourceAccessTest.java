package in.handyman.util;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

class ResourceAccessTest {

	@Test
	void testRdbmsConn() {
		String name="casey/schema_pim_kpi";
		Connection conn = ResourceAccess.rdbmsConn(name);
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet query = statement.executeQuery("SELECT COUNT(*) as count_ FROM _doc ");
			ResultSetMetaData rsmd = query.getMetaData();
			int nrCols = rsmd.getColumnCount();
			// get other column information like type
			while(query.next()){
				for(int i=1; i<=nrCols; i++){
			  		System.out.println(query.getObject(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
