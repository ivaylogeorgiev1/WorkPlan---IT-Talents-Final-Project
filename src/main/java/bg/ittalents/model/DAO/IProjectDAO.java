package bg.ittalents.model.DAO;

import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public interface IProjectDAO {
	public static ProjectDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new ProjectDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addProject(Project project) throws WorkPlanDAOException,
			DBException;

	Project getProjectById(Integer id) throws WorkPlanDAOException, DBException;

	List<User> getAllUsersForProject(Integer projID) throws 
			DBException, UnsupportedDataTypeException;

	List<Project> getAllProjectsByOrg(Integer orgID) throws DBException, UnsupportedDataTypeException;

	Project incrementIssuecount(Project project) throws WorkPlanDAOException, DBException;
}
