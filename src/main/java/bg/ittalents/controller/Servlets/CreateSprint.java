package bg.ittalents.controller.Servlets;

import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.Sprint;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateSprint
 */
@WebServlet("/CreateSprint")
public class CreateSprint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateSprint() {
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
		
		if (request.getParameter("sprintName").trim().equals("")) {
			request.setAttribute("sprintErrorName", "Please enter a valid name for the sprint");
			request.getRequestDispatcher("./ProjectBoard").forward(request, response);
			return;
		}

		Project project = (Project) request.getSession().getAttribute("project");
		Sprint sprint = new Sprint();

		sprint.setName(request.getParameter("sprintName"));

		sprint.setProjectId(project.getId());
		try {
			// adding the sprint and getting getting it back from the DB with ID
			Sprint sprintWithId = ISprintDAO.getDAO("db").getSprintById(ISprintDAO.getDAO("db").addSprint(sprint));
			request.getSession().setAttribute("listWithActivitiesInThisSprint", null);
			// jstl doesnt throw nullpointer in case of iterating over null
			request.getSession().setAttribute("sprint", sprintWithId);
			response.sendRedirect("./SprintInfo?id=" + sprintWithId.getId());
		} catch (WorkPlanDAOException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		;
	}
}
