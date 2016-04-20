package bg.ittalents.model.DAO;

import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import bg.ittalents.model.POJO.Comment;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

public interface ICommentDAO {

	public static CommentDAO getDAO(String storage) throws UnsupportedDataTypeException {
		if (storage.equalsIgnoreCase("db")) {
			return new CommentDAO();
		}
		throw new UnsupportedDataTypeException();
	}

	public int addComment(Comment comment) throws WorkPlanDAOException, DBException;

	Map<Comment, User> getAllCommentsForActivity(int activityID) throws DBException;

	int addWorkLog(Comment comment) throws WorkPlanDAOException, DBException;

	Map<Comment, User> getAllWorkLogsForActivity(int activityID) throws DBException;

	Map<Comment, User> getAllWorkLogsForOrganization(int organizationID) throws DBException;

	Map<Comment, User> getAllCommentsForOrganization(int organizationID) throws DBException;
}
