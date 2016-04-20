package bg.ittalents.model.DAO;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.activation.UnsupportedDataTypeException;

import bg.ittalents.model.POJO.Log;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public class LogDAO extends AbstractDBConnDAO implements ILogDAO {
	private static final String SELECT_LOG_ID_FROM_LOGS_WHERE_ACTIVITY_ID_ORDER_BY_CREATED_ON_DESC = "select log_id from logs where activity_id=? ORDER BY created_on DESC;";
	private static final String SELECT_FROM_LOGS_WHERE_LOG_ID = "select * from logs where log_id=?;";
	private static final String INSERT_INTO_LOGS = "INSERT INTO logs (activity_id, user_id, `action`,issue_key,user_name) VALUES (?, ?, ?,?,?)";

	@Override
	public int addLog(Log log) throws WorkPlanDAOException, DBException {
		if (log == null) {
			throw new WorkPlanDAOException("There is no organization to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_LOGS, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt(1, log.getActivityId());
			ps.setInt(2, log.getUserId());
			ps.setString(3, log.getAction());
			ps.setString(4, log.getIssueKey());
			ps.setString(5, log.getUserFullName());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The Log cannot be add right now!Try again later!");
		}

	}

	@Override
	public Log getLogById(Integer id) throws WorkPlanDAOException, DBException {
		if (id == null || id == 0) {
			throw new WorkPlanDAOException("There is no id for log !");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_FROM_LOGS_WHERE_LOG_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Log log = null;

			if (rs.next()) {
				log = new Log(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5),
						rs.getString(6), rs.getString(7));

				return log;
			}
			throw new DBException("No log with this ID");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No logs for this issue", e);
		}
	}

	@Override
	public List<Log> getLogsByIssueId(Integer id) throws WorkPlanDAOException, DBException {
		if (id == null || id == 0) {
			throw new WorkPlanDAOException("There is no id for issue !");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_LOG_ID_FROM_LOGS_WHERE_ACTIVITY_ID_ORDER_BY_CREATED_ON_DESC);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			List<Log> logList = new ArrayList<Log>();

			while (rs.next()) {

				try {
					logList.add(ILogDAO.getDAO("db").getLogById(rs.getInt(1)));
				} catch (UnsupportedDataTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return logList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No logs for this issue", e);
		}

	}

}
