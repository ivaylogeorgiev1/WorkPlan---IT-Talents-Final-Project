package bg.ittalents.model.DAO;

import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.ActivityStatus;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

public interface IActivityDAO {

	public static ActivityDAO getDAO(String storage)
			throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new ActivityDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public abstract int addActivity(Activity activity)
			throws WorkPlanDAOException, DBException;

	public abstract List<Activity> getActivitiesByProject(int projectID)
			throws DBException;

	Activity getActivityByID(int activityID) throws WorkPlanDAOException,
			DBException;

	List<Activity> getActivitiesByAssigneeID(int userID) throws DBException;

	public List<Activity> getAllActivitiesBySprintID(Integer sprintID)
			throws DBException, WorkPlanDAOException;

	public void setSprint(Integer activityID, Integer sprintID)
			throws WorkPlanDAOException, DBException;

	public List<Activity> getActivitiesNotInSprintAndNotDone(Integer projectID);

	public List<Activity> getAllActivitiesWithStatus(ActivityStatus activityType,
			int sprintID) throws DBException, WorkPlanDAOException;

	void updateStatus(ActivityStatus status, int activityID) throws DBException;

	List<Activity> getAllActivitiesWithTypeInWholeProject(String type, int projectID)
			throws DBException, WorkPlanDAOException;

	List<Activity> getAllActivitiesWithStatusInProject(ActivityStatus activityStatus, int projectID)
			throws DBException, WorkPlanDAOException;

	List<Activity> getActivitiesByAssigneeName(String assignee, int projectID) throws DBException;

	List<Activity> getAllActivitiesContainingComment(String comment, int projectID)
			throws DBException, WorkPlanDAOException;

	void updateActivity(Activity oldAct) throws DBException;

	List<Activity> getActivitiesByIssueKey(String key, int projectID) throws DBException;

	List<Activity> getActivitiesByAssigneeIDInProject(int assigneeID, int projectID) throws DBException;

	List<Activity> getAllActivitiesContainingDescriptionAndSummary(String summaryOrDesc, int projectID)
			throws DBException, WorkPlanDAOException;

	Map<LocalDate, SimpleEntry<Integer, Double>> getAllDoneActivitiesInPast30DaysWithDetail(int projectID)
			throws DBException;

	
}