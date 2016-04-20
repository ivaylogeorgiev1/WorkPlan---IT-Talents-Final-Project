package bg.ittalents.controller.Servlets;

import bg.ittalents.model.DAO.ICommentDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Comment;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/WorkLogServlet")
public class WorkLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkLogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ImageServletFromId.isLogged(request, response);
		
		
		try {
			Map<Comment, User> comments = ICommentDAO.getDAO("db")
					.getAllWorkLogsForActivity(
							((Activity) request.getSession().getAttribute(
									"activity")).getId());
			request.getSession().setAttribute("worklogs", comments);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("show", "worklog");
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/IssueAllPage.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		int userID = ((User) request.getSession().getAttribute("user")).getId();
		int activityID = ((Activity) (request.getSession()
				.getAttribute("activity"))).getId();
		
		Comment comment = new Comment(request.getParameter("commentContent"),
				activityID, userID);
		comment.setIsWorkLog(1);
		comment.setWorkHours(Double.parseDouble(request.getParameter("workHours")));
		try {
			ICommentDAO.getDAO("db").addWorkLog(comment);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
