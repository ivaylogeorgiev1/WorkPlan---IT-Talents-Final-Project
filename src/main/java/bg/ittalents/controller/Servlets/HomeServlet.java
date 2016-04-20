package bg.ittalents.controller.Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ittalents.model.DAO.ICommentDAO;
import bg.ittalents.model.POJO.Comment;
import bg.ittalents.model.POJO.Organization;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ImageServletFromId.isLogged(request, response);
		
		
		try {
			Map<Comment, User> workLogs = ICommentDAO.getDAO("db")
					.getAllWorkLogsForOrganization(
							((Organization) request.getSession().getAttribute(
									"organization")).getId());
			
			Map<Comment, User> comments = ICommentDAO.getDAO("db")
					.getAllCommentsForOrganization(
							((Organization) request.getSession().getAttribute(
									"organization")).getId());
			
			request.getSession().setAttribute("worklogs", workLogs);
			request.getSession().setAttribute("comments", comments);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
		dispatcher.forward(request, response);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
