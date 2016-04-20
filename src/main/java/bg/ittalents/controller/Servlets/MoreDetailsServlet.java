package bg.ittalents.controller.Servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bg.ittalents.model.DAO.INFO;
import bg.ittalents.model.DAO.IOrganizationDAO;
import bg.ittalents.model.DAO.IUserDAO;
import bg.ittalents.model.POJO.Organization;
import bg.ittalents.model.POJO.User;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;

/**
 * Servlet implementation class MoreDetails
 */
@WebServlet("/MoreDetailsS")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class MoreDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreDetailsServlet() {
		super();
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
		ImageServletFromId.isLogged(request, response);
		
		User user = (User) request.getSession().getAttribute("user");
		String orgName = (request.getParameter("orgName"));
		Integer avatarID = Integer.parseInt(request.getParameter("avatarID"));
		System.out.println(orgName);
		System.out.println(avatarID);

		if (user != null && user.getAvatarPath() != null && user.getOrganizationId() != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
			dispatcher.forward(request, response);
			return;

		}

		if (user.getAdmin() == 1) {

			try {
				user.setId(IUserDAO.getDAO("db").addUser(user));
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (avatarID != 0) {

				user.setAvatarPath("avatars" + File.separator + avatarID + ".jpg");
				
				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (avatarID == 0) {
				File fileSaveDir = new File(
						INFO.IMAGES_PATH + File.separator + "customAvatars" + File.separator + user.getId());

				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdirs();
				}

				for (Part part : request.getParts()) {
					part.write(fileSaveDir + File.separator + "avatar.jpg");
				}

				user.setAvatarPath("customAvatars" + File.separator + user.getId() + File.separator + "avatar.jpg");
				

				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Organization org = new Organization();
			org.setAdminID(user.getId());
			org.setName(orgName);

			try {
				org.setId(IOrganizationDAO.getDAO("db").addOrganization(org));
			} catch (WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			user.setOrganizationId(org.getId());

			try {
				IUserDAO.getDAO("db").updateOrgId(user, org.getId());
			} catch (DBException | WorkPlanDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().setAttribute("organization", org);

			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
			dispatcher.forward(request, response);

		}
		if (user.getAdmin() == 0) {

			if (avatarID != 0) {

				user.setAvatarPath("avatars" + File.separator + avatarID + ".jpg");
				
				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (avatarID == 0) {
				File fileSaveDir = new File(
						INFO.IMAGES_PATH + File.separator + "customAvatars" + File.separator + user.getId());

				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdirs();
				}

				for (Part part : request.getParts()) {
					part.write(fileSaveDir + File.separator + "avatar.jpg");
				}

				user.setAvatarPath("customAvatars" + File.separator + user.getId() + File.separator + "avatar.jpg");
				

				try {
					IUserDAO.getDAO("db").updateUser(user);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WorkPlanDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/views/jsp/homeTrue.jsp");
			dispatcher.forward(request, response);

		}

	}

}
