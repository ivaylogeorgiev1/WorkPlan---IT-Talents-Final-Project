package bg.ittalents.model.DAO;

import bg.ittalents.model.POJO.Comment;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import javax.activation.UnsupportedDataTypeException;

public class CommentDAO extends AbstractDBConnDAO implements ICommentDAO {

	private static final String GET_ALL_COMMENTS_FOR_ORG = "SELECT * FROM comments c "
			+ "join activities ac on (c.activity_id=ac.activity_id)"
			+ "join projects pr on (pr.project_id=ac.project_id)"
			+ "where pr.organization_id=? ORDER BY c.created_on DESC;";
	private static final String SELECT_FROM_COMMENTS_WHERE_ACTIVITY_ID_ORDER_BY_CREATED_ON_DESC = "SELECT * FROM comments where activity_id=? ORDER BY created_on DESC;";
	private static final String INSERT_INTO_COMMENTS_VALUES_NULL_DEFAULT = "INSERT into comments values (null,default,?,?,?,?,?);";

	@Override
	public int addComment(Comment comment) throws WorkPlanDAOException, DBException {
		if (comment == null) {
			throw new WorkPlanDAOException("There is no comment to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_COMMENTS_VALUES_NULL_DEFAULT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getActivityID());
			ps.setInt(3, comment.getUserID());
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The comment cannot be add right now!Try again later!");
		}

	}

	@Override
	public Map<Comment, User> getAllCommentsForActivity(int activityID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(SELECT_FROM_COMMENTS_WHERE_ACTIVITY_ID_ORDER_BY_CREATED_ON_DESC);
			ps.setInt(1, activityID);
			Map<Comment, User> mapWithCommentOrderdByDate = new TreeMap<Comment, User>(
					(c1, c2) -> c2.getCreatedOn().compareTo(c1.getCreatedOn()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(6) == 0) {
					mapWithCommentOrderdByDate.put(new Comment(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
									rs.getInt(6), rs.getDouble(7)),
							IUserDAO.getDAO("db").getUserById(rs.getInt(5)));

				}

			}
			return mapWithCommentOrderdByDate;
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("Cannot get comments right now!Try again later!");
		}

	}
	@Override
	public Map<Comment, User> getAllCommentsForOrganization(int organizationID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(GET_ALL_COMMENTS_FOR_ORG);
			ps.setInt(1, organizationID);
			Map<Comment, User> mapWithCommentOrderdByDate = new TreeMap<Comment, User>(
					(c1, c2) -> c2.getCreatedOn().compareTo(c1.getCreatedOn()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(6) == 0) {
					mapWithCommentOrderdByDate.put(new Comment(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
									rs.getInt(6), rs.getDouble(7)),
							IUserDAO.getDAO("db").getUserById(rs.getInt(5)));

				}

			}
			return mapWithCommentOrderdByDate;
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("Cannot get comments right now!Try again later!");
		}

	}
	@Override
	public Map<Comment, User> getAllWorkLogsForOrganization(int organizationID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(GET_ALL_COMMENTS_FOR_ORG);
			ps.setInt(1, organizationID);
			Map<Comment, User> mapWithCommentOrderdByDate = new TreeMap<Comment, User>(
					(c1, c2) -> c2.getCreatedOn().compareTo(c1.getCreatedOn()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(6) == 1) {
					mapWithCommentOrderdByDate.put(new Comment(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
									rs.getInt(6), rs.getDouble(7)),
							IUserDAO.getDAO("db").getUserById(rs.getInt(5)));

				}

			}
			return mapWithCommentOrderdByDate;
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("Cannot get comments right now!Try again later!");
		}

	}
	
	@Override
	public Map<Comment, User> getAllWorkLogsForActivity(int activityID) throws DBException {
		try {
			PreparedStatement ps = getCon()
					.prepareStatement(SELECT_FROM_COMMENTS_WHERE_ACTIVITY_ID_ORDER_BY_CREATED_ON_DESC);
			ps.setInt(1, activityID);
			Map<Comment, User> mapWithCommentOrderdByDate = new TreeMap<Comment, User>(
					(c1, c2) -> c2.getCreatedOn().compareTo(c1.getCreatedOn()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(6) == 1) {
					mapWithCommentOrderdByDate.put(new Comment(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
									rs.getInt(6), rs.getDouble(7)),
							IUserDAO.getDAO("db").getUserById(rs.getInt(5)));

				}

			}
			return mapWithCommentOrderdByDate;
		} catch (SQLException | UnsupportedDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("Cannot get comments right now!Try again later!");
		}

	}

	@Override
	public int addWorkLog(Comment comment) throws WorkPlanDAOException, DBException {
		if (comment == null) {
			throw new WorkPlanDAOException("There is no worklog to add!");
		}
		try {
			PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_COMMENTS_VALUES_NULL_DEFAULT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getActivityID());
			ps.setInt(3, comment.getUserID());
			ps.setInt(4, 1);
			ps.setDouble(5, comment.getWorkHours());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("The worklog cannot be add right now!Try again later!");
		}

	}
}
