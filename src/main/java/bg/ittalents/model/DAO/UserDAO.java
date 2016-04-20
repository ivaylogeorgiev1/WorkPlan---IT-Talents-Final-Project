package bg.ittalents.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public class UserDAO extends AbstractDBConnDAO implements IUserDAO {

	private static final String SELECT_ID_USER_FROM_USERS_PROJECTS_WHERE_ID_USER_PROJECT = "Select  id_user from users_projects where id_user_project =?;";
	private static final String SELECT_FROM_USERS_WHERE_ORGANIZATION_ID = "Select * from users where organization_id =?;";
	private static final String INSERT_USER_INTO_DB = "INSERT into users values(null,?,?,?,?,null,?,?,?);";
	private static final String UPDATE_USER_INTO_DB = "UPDATE users SET username = ?, password = ?, avatar_path=?, admin=?, full_name=?,email=?,last_project_id=? WHERE user_id = ?;";
	private static final String UPDATE_ONLY_ORG_ID = "UPDATE users SET organization_id=? WHERE user_id= ?;";
	private static final String SELECT_USER_BY_ID = "SELECT * from users where user_id=?;";
	private static final String SELECT_FROM_USERS_BY_EMAIL = "Select * from users where email=?;";
	private static final String SELECT_USER_BY_USERNAME_AND_CORRECT_PASSWORD = "SELECT * from users u join organizations_users ou on(u.user_id=ou.user_id) where (u.username = ? AND u.password = ? AND ou.organization_name_id=?";
	private static final String SELECT_USER_BY_USERNAME = "Select * from users where username=?;";
	@Override
	public int addUser(User user) throws DBException, WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					INSERT_USER_INTO_DB,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatarPath());
			ps.setInt(5, user.getAdmin());
			ps.setString(6,user.getFullname());
			ps.setInt(7, user.getLastProjectId());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be add right now!Try again later!", e);
		}

	}

	@Override
	public int updateUser(User user) throws DBException, WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to update!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(
					UPDATE_USER_INTO_DB);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getAvatarPath());
			ps.setInt(4, user.getAdmin());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getEmail());	
			ps.setInt(7, user.getLastProjectId());
			ps.setInt(8, user.getId());
			
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be Updated  right now!Try again later!", e);
		}

	}

	@Override
	public int updateOrgId(User user, Integer orgId) throws DBException,
			WorkPlanDAOException {
		if (user == null) {
			throw new WorkPlanDAOException("There is no user to update!");
		}
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(UPDATE_ONLY_ORG_ID);
			ps.setInt(1, orgId);
			ps.setInt(2, user.getId());

			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"The user cannot be Updated  right now!Try again later!", e);
		}

	}

	@Override
	public User getUserById(int userID) throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(7),
					rs.getInt(6),rs.getString(8),rs.getInt(9));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot get User right now!Try again later!",
					e);
		}
	}

	@Override
	public User getUserByEmail(String email) throws DBException {

		try {

			PreparedStatement ps = getCon().prepareStatement(
					SELECT_FROM_USERS_BY_EMAIL);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(7),
						rs.getInt(6),rs.getString(8),rs.getInt(9));

			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException(
					"Cannot check for user right now!Try again later", e);

		}

	}

	public boolean isThereSuchAUser(String username, String email)
			throws DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_USER_BY_USERNAME);
			PreparedStatement ps2 = getCon().prepareStatement(
					SELECT_FROM_USERS_BY_EMAIL);
			ps.setString(1, username);
			ps2.setString(1, email);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			if (rs.next() || rs2.next()) {
				System.out.println("true");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot check for user right now!Try again later", e);
		}
		System.out.println("false");
		return false;
	}

	@Override
	public boolean isThereSuchAUser(String email) throws DBException {
		try {

			PreparedStatement ps2 = getCon().prepareStatement(
					SELECT_FROM_USERS_BY_EMAIL);
			ps2.setString(1, email);

			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(
					"Cannot check for user right now!Try again later", e);
		}
		return false;
	}


	@Override
	public User loginUser(String username, String password, String organization)
			throws WorkPlanDAOException, DBException {
		try {
			PreparedStatement ps = getCon().prepareStatement(
					SELECT_USER_BY_USERNAME_AND_CORRECT_PASSWORD);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, organization);
			ResultSet rs = ps.executeQuery();

			if (rs.next() == false) {
				throw new WorkPlanDAOException("No such user");
			}
			return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(6),
					rs.getInt(7),rs.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Cannot log right now!", e);
		}

	}

	@Override
	public void insertIntoAttachments(int userID, int activityID, String path) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<User> getAllUsersForOrganization(Integer orgId)
			throws SQLException {
		List<User> users = new ArrayList<User>();

		PreparedStatement ps = getCon().prepareStatement(
				SELECT_FROM_USERS_WHERE_ORGANIZATION_ID);
		ps.setInt(1, orgId);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(7), rs
							.getInt(6),rs.getString(8),rs.getInt(9)));
		}

		return users;

	}

	@Override
	public List<User> getAllUsersForProject(Integer projID)
			throws SQLException, DBException {
		List<User> users = new ArrayList<User>();

		PreparedStatement ps = getCon()
				.prepareStatement(
						SELECT_ID_USER_FROM_USERS_PROJECTS_WHERE_ID_USER_PROJECT);
		ps.setInt(1, projID);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			users.add(getUserById(rs.getInt(1)));
		}

		return users;

	}
}
