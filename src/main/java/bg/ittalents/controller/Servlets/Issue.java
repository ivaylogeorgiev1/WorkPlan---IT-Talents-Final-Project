package bg.ittalents.controller.Servlets;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Issue
 */
@WebServlet("/Issue")
public class Issue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		
		if (request.getParameter("id")==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./home");
			dispatcher.forward(request, response);
		}
		try {
			Activity activity = IActivityDAO.getDAO("db").getActivityByID(Integer.parseInt(request.getParameter("id")));
			
			//cleaning up
			
			request.getSession().setAttribute("assignee",null);
			request.getSession().setAttribute("reporter",null);
			request.getSession().setAttribute("projecName",null);
			request.getSession().setAttribute("sprintName",null);
			request.getSession().setAttribute("connectedKey",null);
			request.getSession().setAttribute("priorityName",null);
			
			
			
			
			request.getSession().setAttribute("activity", activity);

			
			if (activity.getAssigneeID() > 0) {
				request.getSession().setAttribute("assignee",
						IUserDAO.getDAO("db").getUserById(activity.getAssigneeID()).getFullname());
			}

			request.getSession().setAttribute("reporter",
					IUserDAO.getDAO("db").getUserById(activity.getReportedID()).getFullname());
			

			request.getSession().setAttribute("projecName",
					IProjectDAO.getDAO("db").getProjectById(activity.getProjectID()).getName());
			
			if (activity.getSprintID() != null && activity.getSprintID() > 0) {
				request.getSession().setAttribute("sprintName",
						ISprintDAO.getDAO("db").getSprintById(activity.getSprintID()).getName());
			}
			
			if (activity.getConnectedToID()!= null && activity.getConnectedToID() > 0) {
				request.getSession().setAttribute("connectedKey",
						IActivityDAO.getDAO("db").getActivityByID(activity.getConnectedToID()).getIssueKey());
			}

			if (activity.getPrioriy() == 1) {
				request.getSession().setAttribute("priorityName", "Lowest");
			}
			if (activity.getPrioriy() == 2) {
				request.getSession().setAttribute("priorityName", "Low");
			}
			if (activity.getPrioriy() == 3) {
				request.getSession().setAttribute("priorityName", "Normal");
			}
			if (activity.getPrioriy() == 4) {
				request.getSession().setAttribute("priorityName", "High");
			}
			if (activity.getPrioriy() == 5) {
				request.getSession().setAttribute("priorityName", "Highest");
			}

		} catch (WorkPlanDAOException | DBException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("show", "all");
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/IssueAllPage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
