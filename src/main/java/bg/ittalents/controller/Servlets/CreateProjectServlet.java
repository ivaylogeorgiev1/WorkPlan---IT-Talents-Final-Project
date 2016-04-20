package bg.ittalents.controller.Servlets;

import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ImageServletFromId.isLogged(request, response)) {
			return;
		}

		if (request.getSession(false) == null) {
			response.sendRedirect("./");
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		try {
			request.setAttribute("employeesInOrg",
					IUserDAO.getDAO("db").getAllUsersForOrganization(user.getOrganizationId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./WEB-INF/views/jsp/createproject.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ImageServletFromId.isLogged(req, resp);

		if (req.getParameter("name").trim().equals("") || req.getParameter("key").trim().equals("")) {
			req.setAttribute("sprintErrorName", "Please enter a valid name for and key for the project");
			req.getRequestDispatcher("./CreateProjectCon").forward(req, resp);
			return;
		}

		User user = (User) req.getSession().getAttribute("user");

		Project project = new Project(req.getParameter("name"), user.getOrganizationId(), req.getParameter("key"),
				Integer.parseInt(req.getParameter("leader")));
		try {
			Integer projectID = IProjectDAO.getDAO("db").addProject(project);
			project.setId(projectID);
			req.getSession().setAttribute("project", project);
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			req.getSession().setAttribute("projects",
					IProjectDAO.getDAO("db").getAllProjectsByOrg(user.getOrganizationId()));
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = "./SelectProject?projectId=" + project.getId();
		resp.sendRedirect(temp);
	}
}
