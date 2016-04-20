package bg.ittalents.controller.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class RestDescription
 */
@WebServlet("/RestDescriptionSummary")
public class RestDescriptionSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			String input = new BufferedReader(new InputStreamReader(
					request.getInputStream())).readLine();
			System.out.println(input);
			Set<Activity> listWithSerachedActivities = new TreeSet<Activity>((
					a1, a2) -> a1.getId() - a2.getId());
			listWithSerachedActivities.addAll(IActivityDAO.getDAO("db")
					.getAllActivitiesContainingDescriptionAndSummary(
							input,
							((Project) (request.getSession()
									.getAttribute("project"))).getId()));
			response.getWriter().append(
					new Gson().toJson(listWithSerachedActivities));
			System.out.println(new Gson().toJson(listWithSerachedActivities));
		} catch (DBException | WorkPlanDAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
