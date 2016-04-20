package bg.ittalents.controller.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.model.DAO.ISprintDAO;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.POJO.Sprint;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class CompleteServlet
 */
@WebServlet("/CompleteServlet")
public class CompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ImageServletFromId.isLogged(request, response);
		try {
			ISprintDAO.getDAO("db").updateSprintActivity(
					false,
					((Sprint) request.getSession().getAttribute("sprint"))
							.getId());
			request.getSession().setAttribute("activeSprint", null);
		} catch (WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer projectId=((Project)(request.getSession().getAttribute("project"))).getId();
		String temp="./SelectProject?projectId="+projectId;
		request.getRequestDispatcher(temp).forward(request,
				response);
	}

}
