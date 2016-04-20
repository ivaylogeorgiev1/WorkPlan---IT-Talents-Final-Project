package bg.ittalents.model.DAO;

import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

public class ProjectDAO extends AbstractDBConnDAO implements IProjectDAO {

	private static final String INSERT_PROJECT = "INSERT into projects values(null,?,?,?,?,?);";
	private static final String SELECT_USERS_FOR_PROJECT = "Select id_user from users_projects where id_user_project =?;";
	private static final String SELECT_PROJECTS_FOR_ORGANIZATION = "select project_id from projects where organization_id=? order by name;";
	private static final String SELECT_PROJECT_BY_ID = "select * from projects where project_id=?;";
	private static final String UPDATE_ISSUE_COUNT = "UPDATE projects SET issue_count=? WHERE project_id = ?;";
	public int addProject(Project project) throws WorkPlanDAOException, DBException {
		if (project == null) {
			throw new WorkPlanDAOException("There is no project to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_PROJECT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, project.getName());
			ps.setInt(2, project.getOrganizationID());
			ps.setString(3, project.getKey());
			ps.setInt(4, project.getProjectLeaderID());
			ps.setInt(5, project.getIssueCount());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("The project cannot be add right now!Try again later!", e);
		}
	
	}
	
	@Override
	public Project getProjectById(Integer id) throws WorkPlanDAOException, DBException {
		if (id == null) {
			throw new WorkPlanDAOException("There is no id for project!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_PROJECT_BY_ID);
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			Project project = null;
			
			if(rs.next()){
				project=new Project(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5),rs.getInt(6));
				return project;
			}
			throw new DBException("No such project by id");
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No such project by id", e);
		}
	
	}
	
	@Override
	public List<User> getAllUsersForProject(Integer projID) throws DBException, UnsupportedDataTypeException {
		List<User> users = new ArrayList<User>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(SELECT_USERS_FOR_PROJECT);
			ps.setInt(1, projID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				users.add(IUserDAO.getDAO("db").getUserById(rs.getInt(1)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}
	@Override
	public List<Project> getAllProjectsByOrg (Integer orgID) throws DBException, UnsupportedDataTypeException {
		List<Project> projects = new ArrayList<Project>();

		PreparedStatement ps;
		
		try {
			ps = getCon().prepareStatement(SELECT_PROJECTS_FOR_ORGANIZATION);
			ps.setInt(1, orgID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				projects.add(IProjectDAO.getDAO("db").getProjectById(rs.getInt(1)));
			}

		} catch (SQLException | WorkPlanDAOException e) {
			e.printStackTrace();
		}

		return projects;
	}
	
	@Override
	public Project incrementIssuecount(Project project) throws WorkPlanDAOException, DBException {
		
		if (project == null) {
			throw new WorkPlanDAOException("There is no id for project to increment issue count!");
		}
		try {
			
			Project oldProject = IProjectDAO.getDAO("db").getProjectById(project.getId());
			oldProject.setIssueCount(oldProject.getIssueCount()+1);
			
			PreparedStatement ps = getCon().prepareStatement(UPDATE_ISSUE_COUNT);
			ps.setInt(1,oldProject.getIssueCount());
			ps.setInt(2,oldProject.getId());
			
			ps.executeUpdate();
			
			return oldProject;
		
		} catch (SQLException | UnsupportedDataTypeException e) {
			e.printStackTrace();
			throw new DBException("No such project by id", e);
		}
	
	}

}
