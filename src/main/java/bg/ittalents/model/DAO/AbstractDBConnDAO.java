
package bg.ittalents.model.DAO;

import java.sql.Connection;

public abstract class AbstractDBConnDAO {
	private static final Connection con = DBConnection.getInstance()
			.getCon();

	public static Connection getCon() {
		return con;
	}

}
