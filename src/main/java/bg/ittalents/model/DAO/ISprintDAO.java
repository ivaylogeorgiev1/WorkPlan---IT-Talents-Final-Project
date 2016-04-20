package bg.ittalents.model.DAO;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import bg.ittalents.model.POJO.Sprint;

import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public interface ISprintDAO {

	public static SprintDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new SprintDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addSprint(Sprint sprint) throws WorkPlanDAOException,
			DBException;

	public int isThereAnActiveSprintInThisProject(int projectID)
			throws DBException;

	public Sprint getSprintById(int sprintID) throws DBException;

	public List<Sprint> getAllNotFinishedSprintByProjectID(int projectID)
			throws DBException;

	void updateSprintActivity(boolean isActive, int sprintID)
			throws WorkPlanDAOException;

	List<Sprint> getAllFinishedSprints(int projectID) throws DBException;

	

}
