package bg.ittalents.model.DAO;

import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.ActivityStatus;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.activation.UnsupportedDataTypeException;

public class ActivityDAO extends AbstractDBConnDAO implements IActivityDAO {
	private static final String SELECT_DATEDIFF_FINISHED_ON_DATE_CREATED_ON_DATE_FINISHED = "select datediff((finished_on),date(created_on)),date(finished_on) from activities where created_on > current_timestamp-interval 30 day AND status='Done' AND project_id=?;";
	private static final String SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_ISSUE_KEY_LIKE_AND_PROJECT_ID = "SELECT activity_id from activities where issue_key like ? AND project_id=?";
	private static final String SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE = "SELECT activity_id FROM  activities where (description like ? OR summary like ?) AND project_id=?;";
	private static final String SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_TYPE_AND_PROJECT_ID = "Select  activity_id from activities where type=? AND project_id=?;";
	private static final String SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_STATUS_ID = "Select  activity_id from activities where status=? AND project_id=?;";
	private static final String SELECT_ACTIVITY_ID_FROM_ACTIVITIES_A_JOIN = "SELECT activity_id  from activities a join  users u  on(a.assignee_id=u.user_id)  where full_name like ? AND project_id=?;";
	private static final String SELECT_C_ACTIVITY_ID_FROM_COMMENTS = "SELECT c.activity_id FROM comments c join activities a on(c.activity_id=a.activity_id) where text like ? AND a.project_id=?;";
	private static final String UPDATE_ACTIVITIES_STATUS = "UPDATE activities set status=? where activity_id=?;";
	private static final String SELECT_ACTIVITIES_BY_STATUS_AND_SPRINT_ID = "Select  activity_id from activities where status=? AND sprint_id=?;";
	private static final String UPDATE_ACTIVITIES_SET_SPRINT = "Update activities set sprint_id=? where activity_id=?;";
	private static final String SELECT_ACTIVITIES_BYSPRINT = "Select  activity_id from activities where sprint_id =?;";
	private static final String SELECT_ACTIVITIES_BY_PROJECT_ID = "SELECT * from activities where project_id=?";
	private static final String INSERT_INTO_ACTIVITIES = "INSERT into activities (summary,description,issue_key,estimate,reporter_id,assignee_id,`status`,`type`,sprint_id,connected_to_id,project_id,priority,connected_type) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ACTIVITY_BY_ID = "SELECT * from activities where activity_id=?";
	private static final String SELECT_ACTIVITY_BY_ASSIGNEE_ID = "SELECT * from activities where assignee_id=? and project_id=?";
	private static final String UPDATE_ACTIVITY = "UPDATE activities set summary=?, description=? , estimate=?, reporter_id=?,assignee_id=?, sprint_id=?,project_id=?,priority=?,connected_to_id=?,connected_type=? ,`type`=?      where activity_id=?;";

	@Override
	public int addActivity(Activity activity) throws WorkPlanDAOException, DBException {
		if (activity == null) {
			throw new WorkPlanDAOException("There is no activity to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_ACTIVITIES,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, activity.getSummary());
			ps.setString(2, activity.getDescription());
			ps.setString(3, activity.getIssueKey());
			ps.setDouble(4, activity.getEstimate());
			ps.setInt(5, activity.getReportedID());

			if (activity.getAssigneeID() == 0) {
				ps.setNull(6, Types.INTEGER);
			} else {
				ps.setInt(6, activity.getAssigneeID());
			}

			ps.setString(7, activity.getStatus());
			ps.setString(8, activity.getType());

			if (activity.getSprintID() == null || activity.getSprintID() == 0) {
				ps.setNull(9, Types.INTEGER);
			} else {
				ps.setInt(9, activity.getSprintID());
			}

			if (activity.getConnectedToID() == null || activity.getConnectedToID() == 0) {
				ps.setNull(10, Types.INTEGER);
			} else {
				ps.setInt(10, activity.getConnectedToID());
			}

			ps.setInt(11, activity.getProjectID());
			ps.setInt(12, activity.getPrioriy());
			ps.setString(13, activity.getConnectedType());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The activity cannot be add right now!Try again later!");
		}

	}

	@Override
	public Activity getActivityByID(int activityID) throws WorkPlanDAOException, DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITY_BY_ID);
			ps.setInt(1, activityID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				return new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5),
						rs.getTimestamp(6), rs.getTimestamp(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getString(16));

			}
			throw new WorkPlanDAOException("No such an activity!");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activity right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByProject(int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITIES_BY_PROJECT_ID);
			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivities = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivities.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
			return listWithActivities;
		} catch (SQLException | UnsupportedDataTypeException | WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByAssigneeID(int assigneeID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_ACTIVITY_BY_ASSIGNEE_ID);
			ps.setInt(1, assigneeID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException | WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activities right now!Try again later!");
		}
	}

	public List<Activity> getAllActivitiesBySprintID(Integer sprintID) throws DBException, WorkPlanDAOException {
		List<Activity> activitiesInSprint = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_ACTIVITIES_BYSPRINT);
			ps.setInt(1, sprintID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				activitiesInSprint.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return activitiesInSprint;
	}

	public void setSprint(Integer activityID, Integer sprintID) throws WorkPlanDAOException, DBException {
		if (sprintID == null) {
			throw new WorkPlanDAOException("There is no sprint to add to activity!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_ACTIVITIES_SET_SPRINT);
			ps.setInt(1, sprintID);
			ps.setInt(2, activityID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The sprint cannot be add right now to the activity!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesNotInSprintAndNotDone(Integer projectID) {
		List<Activity> activitiesNotInSprint = new ArrayList<Activity>();
		// changed
		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							"SELECT * from activities a left outer join sprints s on(a.sprint_id=s.sprint_id) where (a.sprint_id is null OR s.finished_on is not null) AND a.status!='Done' And a.project_id=?;");

			ps.setInt(1, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				activitiesNotInSprint.add(getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException | WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return activitiesNotInSprint;
	}
@Override
	public List<Activity> getAllActivitiesWithStatus(
			// changed
					ActivityStatus activityStatus, int sprintID) throws DBException,
					WorkPlanDAOException {
				List<Activity> getAllActivitiesWithStatusInSprint = new ArrayList<Activity>();

				PreparedStatement ps;
				try {
					ps = getCon().prepareStatement(
							SELECT_ACTIVITIES_BY_STATUS_AND_SPRINT_ID);
					ps.setString(1, activityStatus.toString());
					ps.setInt(2, sprintID);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						getAllActivitiesWithStatusInSprint.add(getActivityByID(rs
								.getInt(1)));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return getAllActivitiesWithStatusInSprint;
			}

		

	@Override
	public void updateStatus(ActivityStatus status, int activityID) throws DBException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(UPDATE_ACTIVITIES_STATUS);
			ps.setString(1, status.toString());
			ps.setInt(2, activityID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("cannot update activity right now !Try again later!");
		}

	}

	@Override
	public List<Activity> getAllActivitiesContainingComment(String comment, int projectID)
			throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesContainingComment = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(
					SELECT_C_ACTIVITY_ID_FROM_COMMENTS);

			ps.setString(1, "%" + comment + "%");
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesContainingComment.add(getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesContainingComment;
	}

	@Override
	public List<Activity> getActivitiesByAssigneeName(String assignee, int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_ACTIVITY_ID_FROM_ACTIVITIES_A_JOIN);
			ps.setString(1, "%" + assignee + "%");
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db").getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException | WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException("Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getAllActivitiesWithStatusInProject(ActivityStatus activityStatus, int projectID)
			throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesWithStatusInProject = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_STATUS_ID);
			ps.setString(1, activityStatus.toString());
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesWithStatusInProject.add(getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesWithStatusInProject;
	}

	@Override
	public List<Activity> getAllActivitiesWithTypeInWholeProject(String type, int projectID)
			throws DBException, WorkPlanDAOException {
		List<Activity> getAllActivitiesWithTypeInProject = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_TYPE_AND_PROJECT_ID);
			ps.setString(1, type);
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesWithTypeInProject.add(getActivityByID(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException(" cannot get all activities with status right now!Try again later!");
		}

		return getAllActivitiesWithTypeInProject;
	}

	@Override
	public void updateActivity(Activity oldAct) throws DBException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(UPDATE_ACTIVITY);

			ps.setString(1, oldAct.getSummary());
			ps.setString(2, oldAct.getDescription());
			ps.setDouble(3, oldAct.getEstimate());
			ps.setInt(4, oldAct.getReportedID());

			if (oldAct.getAssigneeID() == 0) {
				ps.setNull(5, Types.INTEGER);
			} else {
				ps.setInt(5, oldAct.getAssigneeID());
			}

			if (oldAct.getSprintID() == null || oldAct.getSprintID() == 0) {
				ps.setNull(6, Types.INTEGER);
			} else {
				ps.setInt(6, oldAct.getSprintID());
			}

			ps.setInt(7, oldAct.getProjectID());
			ps.setInt(8, oldAct.getPrioriy());

			if (oldAct.getConnectedToID() == null || oldAct.getConnectedToID() == 0) {
				ps.setNull(9, Types.INTEGER);
			} else {
				ps.setInt(9, oldAct.getConnectedToID());
			}

			ps.setString(10, oldAct.getConnectedType());
			ps.setString(11, oldAct.getType());
			ps.setInt(12, oldAct.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("cannot update activity right now !Try again later!");
		}

	}
	
	@Override
	public List<Activity> getAllActivitiesContainingDescriptionAndSummary(
			String summaryOrDesc, int projectID) throws DBException,
			WorkPlanDAOException {
		List<Activity> getAllActivitiesContainingDescOrSummary = new ArrayList<Activity>();

		PreparedStatement ps;
		try {
			ps = getCon()
					.prepareStatement(
							SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE);

			ps.setString(1, "%" + summaryOrDesc + "%");
			ps.setString(2, "%" + summaryOrDesc + "%");
			ps.setInt(3, projectID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getAllActivitiesContainingDescOrSummary.add(getActivityByID(rs
						.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getAllActivitiesContainingDescOrSummary;
	}

	@Override
	public List<Activity> getActivitiesByAssigneeIDInProject(int assigneeID,
			int projectID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_ACTIVITY_BY_ASSIGNEE_ID);
			ps.setInt(1, assigneeID);
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db")
						.getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException
				| WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activities right now!Try again later!");
		}
	}

	@Override
	public List<Activity> getActivitiesByIssueKey(String key, int projectID)
			throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(
							SELECT_ACTIVITY_ID_FROM_ACTIVITIES_WHERE_ISSUE_KEY_LIKE_AND_PROJECT_ID);
			ps.setString(1, "%" + key + "%");
			ps.setInt(2, projectID);
			ResultSet rs = ps.executeQuery();
			List<Activity> listWithActivitiesOfUser = new LinkedList<Activity>();
			while (rs.next()) {

				listWithActivitiesOfUser.add(IActivityDAO.getDAO("db")
						.getActivityByID(rs.getInt(1)));
			}
			return listWithActivitiesOfUser;
		} catch (SQLException | UnsupportedDataTypeException
				| WorkPlanDAOException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot get Activities right now!Try again later!");
		}
	}
	
	
	
	@Override
	 public Map<LocalDate, SimpleEntry<Integer, Double>> getAllDoneActivitiesInPast30DaysWithDetail(
	   int projectID) throws DBException {
	  try {
	   PreparedStatement ps = getCon()
	     .prepareStatement(
	       "select datediff((finished_on),date(created_on)),date(finished_on) from activities where finished_on > current_timestamp-interval 10 day AND status='Done' AND project_id=?;");
	   ps.setInt(1, projectID);
	   ResultSet rs = ps.executeQuery();
	   Map<LocalDate, SimpleEntry<Integer, Double>> dateCountIssuesTimeToCreate = new TreeMap<LocalDate, SimpleEntry<Integer, Double>>();
	   while (rs.next()) {
	    Double daysWorked = rs.getDouble(1);
	    LocalDate day = rs.getDate(2).toLocalDate();

	    if (!dateCountIssuesTimeToCreate.containsKey(day)) {
	     dateCountIssuesTimeToCreate.put(day,
	       new SimpleEntry<Integer, Double>(1, daysWorked));
	    } else {
	     dateCountIssuesTimeToCreate.put(day,
	       new SimpleEntry<Integer, Double>(
	         dateCountIssuesTimeToCreate.get(day)
	           .getKey() + 1,
	         (daysWorked + dateCountIssuesTimeToCreate
	           .get(day).getValue())));
	    }
	   }
	   LocalDate now = LocalDate.now();
	   for (int i = 0; i < 10; i++) {

	    if (!dateCountIssuesTimeToCreate.containsKey(now.minusDays(i))) {
	     dateCountIssuesTimeToCreate.put(now.minusDays(i),
	       new SimpleEntry<Integer, Double>(0, 0.0));
	    }
	   }

	   System.out.println(dateCountIssuesTimeToCreate);
	   return dateCountIssuesTimeToCreate;
	  } catch (SQLException e) {
	   e.printStackTrace();
	   throw new DBException(
	     "Cannot get Activities right now!Try again later!");
	  }
	 }

}
