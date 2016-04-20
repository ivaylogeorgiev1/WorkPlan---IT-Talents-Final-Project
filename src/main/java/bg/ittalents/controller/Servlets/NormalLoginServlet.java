package bg.ittalents.controller.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.model.DAO.IOrganizationDAO;
import bg.ittalents.model.DAO.IProjectDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Organization;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class NormalLoginS
 */
@WebServlet("/NormalLoginS")
public class NormalLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NormalLoginServlet() {
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./home");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		

		User user = null;
		Organization org = null;
		Project project = null;
        	
		try {
			user = IUserDAO.getDAO("db").getUserByEmail(email);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null && user.getPassword().equals(password)) {
			try {
				org = IOrganizationDAO.getDAO("db").getOrgByID(user.getOrganizationId());

			} catch (WorkPlanDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				request.getSession().setAttribute("projects",
						IProjectDAO.getDAO("db").getAllProjectsByOrg(org.getId()));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().setAttribute("organization", org);
			request.getSession().setAttribute("user", user);

			if (user.getLastProjectId() != 0) {
				try {
					project = IProjectDAO.getDAO("db").getProjectById(user.getLastProjectId());
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("project", project);
				String temp = "./SelectProject?projectId=" + project.getId();
				response.sendRedirect(temp);

				// RequestDispatcher dispatcher =
				// request.getRequestDispatcher("./ProjectBoard");
				// dispatcher.forward(request, response);
				return;

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
			dispatcher.forward(request, response);
		}

		else {
			request.setAttribute("errorMessage", "Wrong password or username ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/normalLogin.jsp");
			dispatcher.forward(request, response);
			return;

		}

	}

}
