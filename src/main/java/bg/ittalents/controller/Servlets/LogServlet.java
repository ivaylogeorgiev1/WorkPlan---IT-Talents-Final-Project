package bg.ittalents.controller.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.model.DAO.ILogDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Log;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
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

		try {
			List<Log> logs = ILogDAO.getDAO("db").getLogsByIssueId(((Activity) request.getSession().getAttribute(
					"activity")).getId());

			request.getSession().setAttribute("logs", logs);
		} catch (DBException | WorkPlanDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("show", "history");
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
		doGet(request, response);
	}

}
