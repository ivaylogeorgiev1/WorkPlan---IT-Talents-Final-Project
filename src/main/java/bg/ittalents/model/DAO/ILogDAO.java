package bg.ittalents.model.DAO;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import bg.ittalents.model.POJO.Log;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public interface ILogDAO {
	
	public static LogDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new LogDAO();
		}
		throw new UnsupportedDataTypeException();
	}
	
	int addLog(Log log) throws WorkPlanDAOException, DBException;

	Log getLogById(Integer id) throws WorkPlanDAOException, DBException;

	List<Log> getLogsByIssueId(Integer id) throws WorkPlanDAOException, DBException;

}
